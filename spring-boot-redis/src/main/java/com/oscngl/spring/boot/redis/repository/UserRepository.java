package com.oscngl.spring.boot.redis.repository;

import com.oscngl.spring.boot.redis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
