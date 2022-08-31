package com.oscngl.spring.boot.testing.service;

import com.oscngl.spring.boot.testing.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();
    User getUserById(Long id);
    User getUserByEmail(String email);
    User createUser(User user);
    User updateUser(Long id, User newUser);
    void deleteUserById(Long id);
    boolean checkIfBirthDateIsValid(User user);

}
