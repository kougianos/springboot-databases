package com.kougianos.springbootdatabases.controller;

import com.kougianos.springbootdatabases.dto.User;
import com.kougianos.springbootdatabases.repository.UserRepository;
import com.kougianos.springbootdatabases.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "mysql")
public class MysqlController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceImpl userService;

    @PostMapping(path = "/add")
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody
    ResponseEntity<String> createUser(@RequestBody User user) {

        try {
            Integer id = userService.createUser(user);
            return new ResponseEntity<>("Successfully inserted user with id " + id, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to insert user: " + e.getMessage(), HttpStatus.CONFLICT);
        }

    }

    @GetMapping(path = "/all")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<Iterable<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(path = "/findById")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<Optional<User>> findById(@RequestParam Integer id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/findByUsername")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<Iterable<User>> findByUsername(@RequestParam String username) {
        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
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
