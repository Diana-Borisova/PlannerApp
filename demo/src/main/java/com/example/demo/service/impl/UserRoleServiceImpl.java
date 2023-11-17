package com.example.demo.service.impl;


import com.example.demo.model.entity.UserRole;
import com.example.demo.model.entity.enums.RoleEnum;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void populateRoles() {
        if (userRoleRepository.count() == 0) {
            Arrays.stream(RoleEnum.values()).forEach(ur->{
                UserRole userRole = new UserRole();
                userRole.setName(ur);
                userRoleRepository.save(userRole);
            });
        }
    }
}
