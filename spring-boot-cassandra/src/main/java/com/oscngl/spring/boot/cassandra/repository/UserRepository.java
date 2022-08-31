package com.oscngl.spring.boot.cassandra.repository;

import com.oscngl.spring.boot.cassandra.model.User;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CassandraRepository<User, String> {

    @AllowFiltering
    Optional<User> findByEmail(String email);

}
