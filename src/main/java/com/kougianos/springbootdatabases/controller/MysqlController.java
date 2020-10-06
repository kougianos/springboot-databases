package com.kougianos.springbootdatabases.controller;

import com.kougianos.springbootdatabases.dto.User;
import com.kougianos.springbootdatabases.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "mysql")
public class MysqlController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/add")
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

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @DeleteMapping(path = "/delete")
    public @ResponseBody
    String deleteUserById(@RequestParam Integer id) {

        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return "User with id " + id + " not found";
        }
        return "Successfully deleted user with id " + id;
    }
}
