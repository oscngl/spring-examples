package com.oscngl.spring.boot.graphql.service;

import com.oscngl.spring.boot.graphql.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();
    User getUserById(Long id);
    User createUser(User user);

}
