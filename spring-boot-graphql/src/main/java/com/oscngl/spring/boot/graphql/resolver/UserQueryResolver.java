package com.oscngl.spring.boot.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.oscngl.spring.boot.graphql.model.User;
import com.oscngl.spring.boot.graphql.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserQueryResolver implements GraphQLQueryResolver {

    private final UserService userService;

    public List<User> getUsers() {
        return userService.getUsers();
    }

    public User getUserById(Long id) {
        return userService.getUserById(id);
    }

}
