package com.coffeeshop.service;

import com.coffeeshop.helper.LoggedUser;
import com.coffeeshop.model.dto.OrderDto;
import com.coffeeshop.model.dto.UserLoginDto;
import com.coffeeshop.model.dto.UserRegistrationDto;
import com.coffeeshop.model.dto.UsersOrdersCountDto;
import com.coffeeshop.model.entity.User;
import com.coffeeshop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;
    private LoggedUser loggedUser;
    public UserService(UserRepository userRepository, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    public  boolean register(UserRegistrationDto userRegistrationDto){

        if(!userRegistrationDto.getPassword().equals(userRegistrationDto.getConfirmPassword())){
            return false;
        }
        Optional<User> byEmail = this.userRepository.findByEmail(userRegistrationDto.getEmail());
        if(byEmail.isPresent()){
            return  false;
        }
        Optional<User> byUsername = this.userRepository.findByUsername(userRegistrationDto.getUsername());
        if(byUsername.isPresent()){
            return  false;
        }
        User user = new User();
        user.setUsername(userRegistrationDto.getUsername());
        user.setEmail(userRegistrationDto.getEmail());
        user.setFirstName(userRegistrationDto.getFirstName());
        user.setLastName(userRegistrationDto.getLastName());
        user.setPassword(userRegistrationDto.getPassword());
        this.userRepository.save(user);
        return true;
    }

    public boolean login(UserLoginDto userLoginDto) {
        Optional<User> user = this.userRepository.findByUsernameAndPassword(userLoginDto.getUsername(),
                userLoginDto.getPassword());
        if(!user.isPresent()){
            return false;
        }
        loggedUser.setId(user.get().getId());

        return true;
    }
    public List<UsersOrdersCountDto> findAllByOrdersSizeDesc() {
        return userRepository
                .findAllByOrderSizeDesc()
                .stream()
                .map(user -> {
                    UsersOrdersCountDto usersOrdersCountDto = new UsersOrdersCountDto();
                   usersOrdersCountDto.setUsername(user.getUsername());
                    usersOrdersCountDto.setOrdersCount(user.getOrders().size());
                    return usersOrdersCountDto;
                })
                .collect(Collectors.toList());
    }

}
