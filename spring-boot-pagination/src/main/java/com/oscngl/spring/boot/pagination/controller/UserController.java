package com.oscngl.spring.boot.pagination.controller;

import com.oscngl.spring.boot.pagination.model.User;
import com.oscngl.spring.boot.pagination.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Page<User>> getUsers(Integer pageNumber, Integer pageSize) {
        return new ResponseEntity<>(userService.getUsers(pageNumber, pageSize), HttpStatus.OK);
    }

}
