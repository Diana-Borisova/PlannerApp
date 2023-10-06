package com.example.musicdb.model.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserLoginDto {
    //, message = "Username length must be between 3 and 20 characters!"
    //, message = "Password length must be between 3 and 20 characters!"
    @Size(min= 3, max = 20, message = "Username length must be between 3 and 20 characters")
    @NotNull
    private String username;

    @Size(min= 5, max = 20, message = "Password length must be between 5 and 20 characters")
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
