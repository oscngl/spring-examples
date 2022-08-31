package com.oscngl.spring.boot.testing.service.impl;

import com.oscngl.spring.boot.testing.exception.EntityAlreadyExistsException;
import com.oscngl.spring.boot.testing.exception.EntityNotFoundException;
import com.oscngl.spring.boot.testing.model.User;
import com.oscngl.spring.boot.testing.repository.UserRepository;
import com.oscngl.spring.boot.testing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Clock clock;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
    }

    @Override
    public User createUser(User user) {
        Optional<User> userByEmail = userRepository.findByEmail(user.getEmail());
        if(userByEmail.isPresent()) {
            throw new EntityAlreadyExistsException("User already exists with email: " + user.getEmail());
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User newUser) {
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
        return userRepository.save(oldUser);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        userRepository.deleteById(id);
    }

    @Override
    public boolean checkIfBirthDateIsValid(User user) {
        return user.getBirthDate().isBefore(LocalDateTime.now(clock));
    }

}
