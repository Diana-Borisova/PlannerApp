package com.example.battleships.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class UserLoginDto {
    //
    //
    @Size(min= 3, max = 10, message = "Username length must be between 3 and 10 characters!")
    @NotNull(message = "Username length must be between 3 and 10 characters!")
    private String username;

    @Size(min= 3, message = "Password length must be more than 3 characters!")
    @NotNull( message = "Password length must be more than 3 characters!")
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
