package com.oscngl.spring.boot.mongodb.repository;

import com.oscngl.spring.boot.mongodb.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
