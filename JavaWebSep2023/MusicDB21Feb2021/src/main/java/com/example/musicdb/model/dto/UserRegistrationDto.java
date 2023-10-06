package com.example.musicdb.model.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRegistrationDto {
    @Size(min= 3, max = 20, message = "Username length must be between 3 and 20 characters")
    @NotNull
    private String username;

    @Size(min= 3, max = 20, message = "Full name length must be between 3 and 20 characters")
    @NotNull
    private String fullName;

    @Size(min= 5, max = 20, message = "Password length must be between 5 and 20 characters")
    @NotNull
    private String password;

    @Size(min= 5, max = 20, message = "Password length must be between 5 and 20 characters")
    @NotNull
    private String confirmPassword;

    @NotBlank(message = "Must be valid email.")
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
