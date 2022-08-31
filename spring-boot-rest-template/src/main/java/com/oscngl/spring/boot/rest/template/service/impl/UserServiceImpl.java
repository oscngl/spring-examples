package com.oscngl.spring.boot.rest.template.service.impl;

import com.oscngl.spring.boot.rest.template.exception.EntityAlreadyExistsException;
import com.oscngl.spring.boot.rest.template.exception.EntityNotFoundException;
import com.oscngl.spring.boot.rest.template.mapper.UserMapper;
import com.oscngl.spring.boot.rest.template.model.User;
import com.oscngl.spring.boot.rest.template.repository.UserRepository;
import com.oscngl.spring.boot.rest.template.request.UserRequest;
import com.oscngl.spring.boot.rest.template.response.UserResponse;
import com.oscngl.spring.boot.rest.template.service.UserService;
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
    public UserResponse updateUser(Long id, UserRequest newUser) {
        User oldUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        if(!oldUser.getEmail().equals(newUser.getEmail())) {
            Optional<User> userByEmail = userRepository.findByEmail(newUser.getEmail());
            if(userByEmail.isPresent()) {
                throw new EntityAlreadyExistsException("User already exists with email: " + newUser.getEmail());
            }
        }
        oldUser.setFirstName(newUser.getFirstName());
        oldUser.setLastName(newUser.getLastName());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setPassword(newUser.getPassword());
        return UserMapper.INSTANCE.userToResponse(userRepository.save(oldUser));
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        userRepository.deleteById(id);
    }

}
