package com.oscngl.spring.boot.kubernetes.service.impl;

import com.oscngl.spring.boot.kubernetes.exception.EntityAlreadyExistsException;
import com.oscngl.spring.boot.kubernetes.exception.EntityNotFoundException;
import com.oscngl.spring.boot.kubernetes.mapper.UserMapper;
import com.oscngl.spring.boot.kubernetes.model.User;
import com.oscngl.spring.boot.kubernetes.repository.UserRepository;
import com.oscngl.spring.boot.kubernetes.request.UserRequest;
import com.oscngl.spring.boot.kubernetes.response.UserResponse;
import com.oscngl.spring.boot.kubernetes.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();
        return UserMapper.INSTANCE.usersToResponses(users);
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        return UserMapper.INSTANCE.userToResponse(user);
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
        return UserMapper.INSTANCE.userToResponse(user);
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        Optional<User> userByEmail = userRepository.findByEmail(userRequest.getEmail());
        if(userByEmail.isPresent()) {
            throw new EntityAlreadyExistsException("User already exists with email: " + userRequest.getEmail());
        }
        return UserMapper.INSTANCE.userToResponse(userRepository.save(UserMapper.INSTANCE.requestToUser(userRequest)));
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        if(!user.getEmail().equals(userRequest.getEmail())) {
            Optional<User> userByEmail = userRepository.findByEmail(userRequest.getEmail());
            if(userByEmail.isPresent()) {
                throw new EntityAlreadyExistsException("User already exists with email: " + userRequest.getEmail());
            }
        }
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        return UserMapper.INSTANCE.userToResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        userRepository.deleteById(id);
    }

}
