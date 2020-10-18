package com.kougianos.springbootdatabases.repository;

import com.kougianos.springbootdatabases.dto.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findByUsernameContaining(String username);
    User findByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE hibernate_sequence SET next_val = 1", nativeQuery = true)
    void resetIdCounter();

}
