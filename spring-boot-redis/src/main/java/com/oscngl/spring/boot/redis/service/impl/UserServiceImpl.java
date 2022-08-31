package com.oscngl.spring.boot.redis.service.impl;

import com.oscngl.spring.boot.redis.model.User;
import com.oscngl.spring.boot.redis.repository.UserRepository;
import com.oscngl.spring.boot.redis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    @Cacheable(cacheNames = "users", key = "#id")
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    @Override
    @CachePut(cacheNames = "users", key = "#id")
    public User updateUserById(Long id, User newUser) {
        User oldUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        oldUser.setFirstName(newUser.getFirstName());
        oldUser.setLastName(newUser.getLastName());
        return userRepository.save(oldUser);
    }

    @Override
    @CacheEvict(cacheNames = "users", allEntries = true)
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

}
