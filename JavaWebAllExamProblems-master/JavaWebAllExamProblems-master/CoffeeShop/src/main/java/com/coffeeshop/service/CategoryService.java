package com.coffeeshop.service;

import com.coffeeshop.helper.LoggedUser;
import com.coffeeshop.model.dto.UserLoginDto;
import com.coffeeshop.model.dto.UserRegistrationDto;
import com.coffeeshop.model.entity.Category;
import com.coffeeshop.model.entity.CategoryEnum;
import com.coffeeshop.model.entity.User;
import com.coffeeshop.repository.CategoryRepository;
import com.coffeeshop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    private LoggedUser loggedUser;
    public CategoryService(CategoryRepository categoryRepository, LoggedUser loggedUser) {
        this.categoryRepository = categoryRepository;
        this.loggedUser = loggedUser;
    }
    public Category findByName(String name){
        return this.categoryRepository.findByName(name).orElseThrow();
    }

}
