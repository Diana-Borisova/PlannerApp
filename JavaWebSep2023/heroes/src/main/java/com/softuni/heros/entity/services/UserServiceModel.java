package com.softuni.heros.entity.services;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserServiceModel {

    private String id;

    private String username;


    private String password;


    private String email;

    private String country;
}
