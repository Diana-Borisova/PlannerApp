package com.reseller.model.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegistrationDto {
    @Size(min= 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    @NotNull
    private String username;



    @Size(min= 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    @NotNull
    private String password;

    @Size(min= 3, max = 20, message = "Password length must be between 3 and 20 characters")
    @NotNull
    private String confirmPassword;

    @NotBlank(message = "Email cannot be empty!")
    @Email
    private String email;

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


}
