package com.softuni.heros.service;

import com.softuni.heros.entity.User;
import com.softuni.heros.entity.services.UserServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserService {
    UserServiceModel findByEmailAndPassword(String email, String password);

    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndEmail( String username, String email);

    UserServiceModel findByUsernameAndPassword( String username, String password);

}
