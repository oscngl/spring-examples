package com.oscngl.spring.boot.security.jwt.service;

import com.oscngl.spring.boot.security.jwt.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getUsers();
    User getUserByEmail(String email);
    User createUser(User user);

}
