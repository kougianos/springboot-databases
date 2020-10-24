package com.kougianos.springbootdatabases.service;

import com.kougianos.springbootdatabases.dto.User;

import java.util.List;

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
    List<User> getAllUsers();

    /**
     * Get user with specific id
     *
     * @return User
     */
    User getUser(Integer id);

    /**
     * Update an existing user
     *
     * @param id id of user to be updated
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
    boolean deleteAllUsers();
}
