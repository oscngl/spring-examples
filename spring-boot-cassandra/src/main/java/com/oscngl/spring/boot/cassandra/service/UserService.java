package com.oscngl.spring.boot.cassandra.service;

import com.oscngl.spring.boot.cassandra.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();
    User getUserById(String id);
    User getUserByEmail(String email);
    User createUser(User user);
    User updateUser(String id, User newUser);
    void deleteUserById(String id);

}
