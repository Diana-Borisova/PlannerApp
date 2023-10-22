package com.example.coffeeshop.service;


import com.example.coffeeshop.model.dto.UserLoginDto;
import com.example.coffeeshop.model.dto.UserOrderCountDto;
import com.example.coffeeshop.model.dto.UserRegistrationDto;
import com.example.coffeeshop.model.entity.User;
import com.example.coffeeshop.repository.UserRepository;
import com.example.coffeeshop.util.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        loggedUser.setUsername(user.get().getUsername());

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
        user.setFirstName(userRegistrationDto.getFirstName());
        user.setLastName(userRegistrationDto.getLastName());
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(userRegistrationDto.getPassword());

        this.userRepository.save(user);
        return true;
    }

    public List<UserOrderCountDto> getEmployeeWithOrders() {
        return this.userRepository.findAllByOrderBySizeDesc().
                stream().map(user ->
                {
                    UserOrderCountDto userOrderCountDto = new UserOrderCountDto();
                    userOrderCountDto.setUsername(user.getUsername());
                    userOrderCountDto.setOrdersCount(user.getOrders().size());
                    return userOrderCountDto;
                })
                .sorted((user1, user2) -> Integer.compare(user2.getOrdersCount(), user1.getOrdersCount())) // Sort in descending order
                .collect(Collectors.toList());
    }
}