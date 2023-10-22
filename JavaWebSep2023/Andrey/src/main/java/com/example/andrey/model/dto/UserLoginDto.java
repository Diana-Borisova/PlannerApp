package com.example.andrey.model.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserLoginDto {
    //, message = "Username length must be between 3 and 20 characters!"
    //, message = "Password length must be between 3 and 20 characters!"
    @Size(min= 3, message = "Username must be more than two characters!")
    @NotNull
    private String username;

    @Size(min= 3, message = "Password must be more than two characters!")
    @NotNull
    private String password;

    public UserLoginDto() {
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


}
