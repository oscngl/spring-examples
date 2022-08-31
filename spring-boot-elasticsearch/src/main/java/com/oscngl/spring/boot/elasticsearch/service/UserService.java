package com.oscngl.spring.boot.elasticsearch.service;

import com.oscngl.spring.boot.elasticsearch.model.User;

public interface UserService {

    User createUser(User user);
    Iterable<User> getUsers();
    User getUserById(String id);
    Iterable<User> getUsersByFirstName(String firstName);
    User updateUserById(String id, User newUser);
    void deleteUserById(String id);

}
