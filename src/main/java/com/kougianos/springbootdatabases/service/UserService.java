package com.kougianos.springbootdatabases.service;

import com.kougianos.springbootdatabases.dto.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    /**
     * Create a new user
     *
     * @param user user to create
     * @return Integer id of user created
     */
    Integer createUser(User user);

    /**
     * Get all users
     *
     * @return List of all users
     */
    Iterable<User> getAllUsers();

    /**
     * Get user with specific id
     *
     * @return User
     */
    Optional<User> getUserById(Integer id);

    /**
     * Get user by username
     *
     * @return User
     */
    List<User> getUserByUsername(String username);

    /**
     * Update an existing user
     *
     * @param id   id of user to be updated
     * @param user user to create
     * @return Integer id of user updated
     */
    Integer updateUser(Integer id, User user);

    /**
     * Delete an existing user
     *
     * @param id id of user to be deleted
     * @return Integer id of user deleted
     */
    Integer deleteUser(Integer id);

    /**
     * Delete all existing users
     *
     * @return boolean true if operation was successful, false otherwise
     */
    void deleteAllUsers();
}
