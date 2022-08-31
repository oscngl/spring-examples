package com.oscngl.spring.boot.kubernetes.service;

import com.oscngl.spring.boot.kubernetes.request.UserRequest;
import com.oscngl.spring.boot.kubernetes.response.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getUsers();
    UserResponse getUserById(Long id);
    UserResponse getUserByEmail(String email);
    UserResponse createUser(UserRequest userRequest);
    UserResponse updateUser(Long id, UserRequest userRequest);
    void deleteUser(Long id);

}
