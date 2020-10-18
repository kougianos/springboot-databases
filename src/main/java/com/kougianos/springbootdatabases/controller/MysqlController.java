package com.kougianos.springbootdatabases.controller;

import com.kougianos.springbootdatabases.dto.User;
import com.kougianos.springbootdatabases.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "mysql")
public class MysqlController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/add")
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody
    String addNewUser
            (@RequestParam String username,
             @RequestParam String password,
             @RequestParam String email) {

        User n = new User();
        n.setUsername(username);
        n.setPassword(password);
        n.setEmail(email);

        Integer id = userRepository.save(n).getId();
        return "Successfully inserted user with id " + id;

    }

    @PostMapping(path = "/addObject")
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody
    String addNewUserObject
            (@RequestBody User user) {

        try {
            Integer id = userRepository.save(user).getId();
            return "Successfully inserted user with id " + id;
        } catch (DataIntegrityViolationException e) {
            return "Failed to insert user, duplicate key " + user.getId();
        }


    }

    @GetMapping(path = "/all")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/findByUsername")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    Iterable<User> findByUsername(@RequestParam String username) {

        return userRepository.findByUsernameContaining(username);

    }

    @DeleteMapping(path = "/delete")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    String deleteUserById(@RequestParam Integer id) {

        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return "User with id " + id + " not found";
        }
        return "Successfully deleted user with id " + id;
    }

    @DeleteMapping(path = "/deleteAll")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    String deleteAllUsers() {

        userRepository.deleteAll();
        userRepository.resetIdCounter();
        return "Successfully deleted all users and reset id counter";

    }

    @PutMapping(path = "/update")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    String updateUserById(@RequestParam Integer id,
                          @RequestParam(required = false) String username,
                          @RequestParam(required = false) String password,
                          @RequestParam(required = false) String email) {

        Optional<User> n = userRepository.findById(id);

        if (n.isPresent()) {

            if (username != null) {
                n.get().setUsername(username);
            }

            if (password != null) {
                n.get().setPassword(password);
            }

            if (email != null) {
                n.get().setEmail(email);
            }

            userRepository.save(n.get());

        } else {
            return "User with id " + id + " not found";
        }

        return "Successfully updated user with id " + id;

    }
}
