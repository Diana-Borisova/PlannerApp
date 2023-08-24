package com.example.demo.entity;



import com.example.demo.entity.enums.RoleEnum;
import jakarta.persistence.*;
@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    public UserRole() {
    }

    public RoleEnum getName() {
        return name;
    }

    public UserRole setName(RoleEnum name) {
        this.name = name;
        return this;
    }
}
