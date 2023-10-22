package com.reseller.service;


import com.reseller.model.dto.UserLoginDto;
import com.reseller.model.dto.UserRegistrationDto;
import com.reseller.model.entity.User;
import com.reseller.repository.UserRepository;
import com.reseller.util.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;


    public UserService(UserRepository userRepository, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;

    }

    public boolean login(UserLoginDto userLoginDto) {
        Optional<User> user = this.userRepository.findByUsernameAndPassword(userLoginDto.getUsername(),
                userLoginDto.getPassword());
        if (user.isEmpty()) {
            return false;
        }
        loggedUser.setId(user.get().getId());
        loggedUser.setUsername(userLoginDto.getUsername());

        return true;
    }

    public boolean register(UserRegistrationDto userRegistrationDto) {

        if (!userRegistrationDto.getPassword().equals(userRegistrationDto.getConfirmPassword())) {
            return false;
        }
        Optional<User> byEmail = this.userRepository.findByEmail(userRegistrationDto.getEmail());
        if (byEmail.isPresent()) {
            return false;
        }

        Optional<User> byUsername = this.userRepository.findByUsername(userRegistrationDto.getUsername());
        if (byUsername.isPresent()) {
            return false;
        }
        User user = new User();
        user.setUsername(userRegistrationDto.getUsername());
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(userRegistrationDto.getPassword());
        this.userRepository.save(user);
        return true;
    }
}