package com.oscngl.spring.boot.redis.service;

import com.oscngl.spring.boot.redis.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user);
    List<User> getUsers();
    User getUserById(Long id);
    User updateUserById(Long id, User newUser);
    void deleteUserById(Long id);

}
