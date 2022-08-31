package com.oscngl.spring.boot.pagination.service;

import com.oscngl.spring.boot.pagination.model.User;
import org.springframework.data.domain.Page;

public interface UserService {

    Page<User> getUsers(Integer pageNumber, Integer pageSize);

}
