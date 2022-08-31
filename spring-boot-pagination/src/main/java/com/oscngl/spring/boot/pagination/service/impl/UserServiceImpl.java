package com.oscngl.spring.boot.pagination.service.impl;

import com.oscngl.spring.boot.pagination.model.User;
import com.oscngl.spring.boot.pagination.repository.UserRepository;
import com.oscngl.spring.boot.pagination.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Page<User> getUsers(Integer pageNumber, Integer pageSize) {
        return userRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

}
