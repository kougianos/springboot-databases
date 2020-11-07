package com.kougianos.springbootdatabases.service;

import com.kougianos.springbootdatabases.dto.User;
import com.kougianos.springbootdatabases.exception.EmailAlreadyExistsException;
import com.kougianos.springbootdatabases.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public Integer createUser(User user) {

        // Check id
        if (user.getId() != null) {
            throw new DataIntegrityViolationException("Id is automatically set and not required at request.");
        }

        List<User> u = userRepository.findByEmail(user.getEmail());


        // Check email
        if (u.isEmpty()) {
            return userRepository.save(user).getId();
        } else {
            throw new EmailAlreadyExistsException();
        }

    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public List<User> getUserByUsername(String username) {
        return userRepository.findByUsernameContaining(username);
    }

    public Integer updateUser(Integer id, User user) {
        Optional<User> n = userRepository.findById(id);

        if (n.isPresent()) {

            if (user.getUsername() != null) {
                n.get().setUsername(user.getUsername());
            }

            if (user.getPassword() != null) {
                n.get().setPassword(user.getPassword());
            }

            if (user.getEmail() != null) {
                n.get().setEmail(user.getEmail());
            }
            return userRepository.save(n.get()).getId();

        }
        return 0;

    }

    public Integer deleteUser(Integer id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
        return id;
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
        userRepository.resetIdCounter();
    }
}
