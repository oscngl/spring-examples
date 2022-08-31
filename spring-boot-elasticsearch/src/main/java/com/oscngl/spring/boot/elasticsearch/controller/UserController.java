package com.oscngl.spring.boot.elasticsearch.controller;

import com.oscngl.spring.boot.elasticsearch.model.User;
import com.oscngl.spring.boot.elasticsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<User>> getUsers(){
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/first-name/{firstName}")
    public ResponseEntity<Iterable<User>> getUsersByFirstName(@PathVariable String firstName) {
        return new ResponseEntity<>(userService.getUsersByFirstName(firstName), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable String id, @RequestBody User newUser){
        return new ResponseEntity<>(userService.updateUserById(id, newUser), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable String id){
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
