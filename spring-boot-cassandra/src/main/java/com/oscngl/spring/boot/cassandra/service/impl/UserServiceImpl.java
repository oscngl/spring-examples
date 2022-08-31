package com.oscngl.spring.boot.cassandra.service.impl;

import com.oscngl.spring.boot.cassandra.model.User;
import com.oscngl.spring.boot.cassandra.repository.UserRepository;
import com.oscngl.spring.boot.cassandra.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    @Override
    public User createUser(User user) {
        Optional<User> userByEmail = userRepository.findByEmail(user.getEmail());
        if(userByEmail.isPresent()) {
            throw new RuntimeException("User already exists with email: " + user.getEmail());
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(String id, User newUser) {
        User oldUser = getUserById(id);
        if(!oldUser.getEmail().equals(newUser.getEmail())) {
            Optional<User> userByEmail = userRepository.findByEmail(newUser.getEmail());
            if(userByEmail.isPresent()) {
                throw new RuntimeException("User already exists with email: " + newUser.getEmail());
            }
        }
        oldUser.setFirstName(newUser.getFirstName());
        oldUser.setLastName(newUser.getLastName());
        oldUser.setEmail(newUser.getEmail());
        return userRepository.save(oldUser);
    }

    @Override
    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }

}
