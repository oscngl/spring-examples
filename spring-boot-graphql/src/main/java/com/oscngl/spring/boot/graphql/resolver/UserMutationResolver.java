package com.oscngl.spring.boot.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.oscngl.spring.boot.graphql.mapper.UserMapper;
import com.oscngl.spring.boot.graphql.model.User;
import com.oscngl.spring.boot.graphql.request.UserRequest;
import com.oscngl.spring.boot.graphql.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMutationResolver implements GraphQLMutationResolver {

    private final UserService userService;

    public User createUser(UserRequest userRequest) {
        return userService.createUser(UserMapper.INSTANCE.requestToUser(userRequest));
    }

}
