package com.example.gira.model.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserLoginDto {
    //, message = "Username length must be between 3 and 20 characters!"
    //, message = "Password length must be between 3 and 20 characters!"
    @Email(message = "Email cannot be empty!")
    @NotNull
    private String email;

    @Size(min= 3, max = 20, message = "Password length must be between 3 and 20 characters (inclusive 3 and 20).")
    @NotNull
    private String password;

    public UserLoginDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
