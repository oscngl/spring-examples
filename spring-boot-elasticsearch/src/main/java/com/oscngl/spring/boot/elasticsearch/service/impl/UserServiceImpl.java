package com.oscngl.spring.boot.elasticsearch.service.impl;

import com.oscngl.spring.boot.elasticsearch.model.User;
import com.oscngl.spring.boot.elasticsearch.repository.es.UserEsRepository;
import com.oscngl.spring.boot.elasticsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserEsRepository userEsRepository;

    @Override
    public User createUser(User user) {
        return userEsRepository.save(user);
    }

    @Override
    public Iterable<User> getUsers() {
        return userEsRepository.findAll();
    }

    @Override
    public User getUserById(String id) {
        return userEsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    @Override
    public Iterable<User> getUsersByFirstName(String firstName) {
        return userEsRepository.findByFirstNameUsingCustomQuery(firstName);
    }

    @Override
    public User updateUserById(String id, User newUser) {
        User oldUser = userEsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        oldUser.setFirstName(newUser.getFirstName());
        oldUser.setLastName(newUser.getLastName());
        return userEsRepository.save(oldUser);
    }

    @Override
    public void deleteUserById(String id) {
        userEsRepository.deleteById(id);
    }

}
