package com.oscngl.spring.boot.security.jwt.service;

import com.oscngl.spring.boot.security.jwt.model.Role;

public interface RoleService {

    Role createRole(Role role);
    Role getRoleByName(String name);

}
