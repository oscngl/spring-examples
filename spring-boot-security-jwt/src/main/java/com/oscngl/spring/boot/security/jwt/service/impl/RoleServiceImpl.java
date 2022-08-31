package com.oscngl.spring.boot.security.jwt.service.impl;

import com.oscngl.spring.boot.security.jwt.model.Role;
import com.oscngl.spring.boot.security.jwt.repository.RoleRepository;
import com.oscngl.spring.boot.security.jwt.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name).orElse(null);
    }

}
