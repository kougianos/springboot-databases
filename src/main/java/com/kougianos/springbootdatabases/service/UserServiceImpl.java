package com.kougianos.springbootdatabases.service;

import com.kougianos.springbootdatabases.dto.User;
import com.kougianos.springbootdatabases.exception.EmailAlreadyExistsException;
import com.kougianos.springbootdatabases.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    public Integer createUser(User user) {

        // Check id
        if (user.getId() != null) {
            throw new DataIntegrityViolationException("Id is automatically set and not required at request.");
        }

        List<User> u = userRepository.findByEmail(user.getEmail());

        System.out.println(u);

        // Check email
        if (u.size() == 0) {
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
        return null;
    }

    public Integer deleteUser(Integer id) {
        return null;
    }

    public boolean deleteAllUsers() {
        return false;
    }
}
