package com.oscngl.spring.boot.graphql.mapper;

import com.oscngl.spring.boot.graphql.model.User;
import com.oscngl.spring.boot.graphql.request.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User requestToUser(UserRequest userRequest);

}
