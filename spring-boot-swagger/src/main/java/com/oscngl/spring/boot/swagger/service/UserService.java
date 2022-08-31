package com.oscngl.spring.boot.swagger.service;

import com.oscngl.spring.boot.swagger.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();
    User getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id, User newUser);
    void deleteUserById(Long id);

}
