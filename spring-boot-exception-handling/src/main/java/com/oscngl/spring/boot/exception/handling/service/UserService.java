package com.oscngl.spring.boot.exception.handling.service;

import com.oscngl.spring.boot.exception.handling.model.User;

public interface UserService {

    User getUserById(Long id);
    User getUserByEmail(String email);
    User createUser(User user);
    User updateUser(Long id, User newUser);
    void deleteUser(Long id);

}
