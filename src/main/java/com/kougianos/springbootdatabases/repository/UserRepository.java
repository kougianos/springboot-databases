package com.kougianos.springbootdatabases.repository;

import com.kougianos.springbootdatabases.dto.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findByUsernameContaining(String username);

}
