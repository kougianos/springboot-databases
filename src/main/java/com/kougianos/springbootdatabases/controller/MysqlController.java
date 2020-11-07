package com.kougianos.springbootdatabases.controller;

import com.kougianos.springbootdatabases.dto.User;
import com.kougianos.springbootdatabases.repository.UserRepository;
import com.kougianos.springbootdatabases.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    ResponseEntity<String> deleteUserById(@RequestParam Integer id) {

        if (userService.deleteUser(id) > 0) {
            return new ResponseEntity<>("Successfully delete user with id " + id, HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);

    }

    @DeleteMapping(path = "/deleteAll")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<String> deleteAllUsers() {

        userService.deleteAllUsers();
        return new ResponseEntity<>("Successfully deleted all users and reset id counter", HttpStatus.OK);

    }

    @PutMapping(path = "/update")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<String> updateUserById(@RequestParam Integer id,
                                          @RequestParam() User user) {

        if (userService.updateUser(id, user) > 0) {
            return new ResponseEntity<>("Successfully updated user with id " + id, HttpStatus.OK);
        }

        return new ResponseEntity<>("Failed to update user with id " + id, HttpStatus.CONFLICT);

    }
}
