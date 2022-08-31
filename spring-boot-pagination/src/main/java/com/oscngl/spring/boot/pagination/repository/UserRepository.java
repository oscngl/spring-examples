package com.oscngl.spring.boot.pagination.repository;

import com.oscngl.spring.boot.pagination.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
