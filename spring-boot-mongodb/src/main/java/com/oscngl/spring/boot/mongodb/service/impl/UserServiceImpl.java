package com.oscngl.spring.boot.mongodb.service.impl;

import com.oscngl.spring.boot.mongodb.model.User;
import com.oscngl.spring.boot.mongodb.repository.UserRepository;
import com.oscngl.spring.boot.mongodb.service.MongoSequenceGeneratorService;
import com.oscngl.spring.boot.mongodb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MongoSequenceGeneratorService sequenceGeneratorService;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
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
        user.setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User newUser) {
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
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

}
