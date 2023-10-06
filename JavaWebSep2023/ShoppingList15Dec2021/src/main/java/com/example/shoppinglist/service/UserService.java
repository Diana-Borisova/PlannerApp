package com.example.shoppinglist.service;


import com.example.shoppinglist.model.dto.UserLoginDto;
import com.example.shoppinglist.model.dto.UserRegistrationDto;
import com.example.shoppinglist.model.entity.CategoryEnum;
import com.example.shoppinglist.model.entity.Product;
import com.example.shoppinglist.model.entity.User;
import com.example.shoppinglist.repository.ProductRepository;
import com.example.shoppinglist.repository.UserRepository;
import com.example.shoppinglist.util.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final ProductRepository productRepository;

    public UserService(UserRepository userRepository, LoggedUser loggedUser, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.productRepository = productRepository;
    }

    public boolean login(UserLoginDto userLoginDto) {
        Optional<User> user = this.userRepository.findByUsernameAndPassword(userLoginDto.getUsername(),
                userLoginDto.getPassword());
        if(user.isEmpty()){
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
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(userRegistrationDto.getPassword());
        this.userRepository.save(user);
        return true;
    }


//    public List<Product> getUserContents() {
//        return this.productRepository.findAllByCategory(CategoryEnum.Food);
//    }
}
