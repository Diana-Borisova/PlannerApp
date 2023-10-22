package com.softuni.heros.entity.binding;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginBindingModel {


    @NotNull(message = "Username cannot be empty")
    @Size(min=2, message = "Username length must be more than two characters!")
    private String username;

    @NotNull(message = "Password cannot be empty!")
    @Size(min=2, message = "Password length must be more than two characters!")
    private String password;
}
