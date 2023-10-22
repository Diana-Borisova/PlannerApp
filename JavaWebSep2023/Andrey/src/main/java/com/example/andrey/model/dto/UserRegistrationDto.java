package com.example.andrey.model.dto;


import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class UserRegistrationDto {
    @Size(min= 3, message = "Username must be more than two characters!")
    @NotNull
    private String username;

    @Size(min= 3, message = "Password must be more than two characters!")
    @NotNull
    private String password;

    @Size(min= 3)
    @NotNull
    private String confirmPassword;

    @NotBlank(message = "Must be valid email.")
    @Email
    private String email;


    @Positive( message = "Budget must be more or equal to 0!")
    private BigDecimal userBudget;

    public UserRegistrationDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getUserBudget() {
        return userBudget;
    }

    public void setUserBudget(BigDecimal userBudget) {
        this.userBudget = userBudget;
    }

}
