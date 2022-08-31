package com.oscngl.spring.boot.rest.template.service;

import com.oscngl.spring.boot.rest.template.request.UserRequest;
import com.oscngl.spring.boot.rest.template.response.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getUsers();
    UserResponse getUserById(Long id);
    UserResponse getUserByEmail(String email);
    UserResponse createUser(UserRequest userRequest);
    UserResponse updateUser(Long id, UserRequest newUser);
    void deleteUserById(Long id);

}
