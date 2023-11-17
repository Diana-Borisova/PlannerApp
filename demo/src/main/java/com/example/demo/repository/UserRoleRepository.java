package com.example.demo.repository;


import com.example.demo.model.entity.UserRole;
import com.example.demo.model.entity.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> getUserRoleByName(RoleEnum name);
}
