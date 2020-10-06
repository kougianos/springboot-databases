package com.kougianos.springbootdatabases.repository;

import com.kougianos.springbootdatabases.dto.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
