package com.likebookapp.vallidation.CheckUserExistence;


import com.likebookapp.model.binding.UserLoginBindingModel;
import com.likebookapp.model.binding.UserServiceModel;
import com.likebookapp.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UserLoginValidator implements ConstraintValidator<CheckUserExistence, UserLoginBindingModel> {

    private final UserService userService;

    @Autowired
    public UserLoginValidator(UserService userService) {
        this.userService = userService;

    }

    @Override
    public void initialize(CheckUserExistence constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserLoginBindingModel userLoginModel, ConstraintValidatorContext constraintValidatorContext) {
        UserServiceModel user = this.userService.findByUsername(userLoginModel.getUsername());
        return user.getId() != null && user.getPassword().equals(userLoginModel.getPassword());
    }
}
