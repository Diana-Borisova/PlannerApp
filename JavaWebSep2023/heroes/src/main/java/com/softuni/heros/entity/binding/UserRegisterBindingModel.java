package com.softuni.heros.entity.binding;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterBindingModel {
    @NotNull(message = "Username cannot be empty")
    @Size(min=2, message = "Username length must be more than two characters!")
    private String username;

    @NotNull(message = "Password cannot be empty")
    @Size(min=2, message = "Password length must be more than two characters!")
    private String password;

    @NotNull(message = "Password cannot be empty")
    @Size(min=2, message = "Password length must be more than two characters!")
    private String confirmPassword;

    @NotNull(message = "Email cannot be empty.")
    @Email(message = "Email cannot be empty.")
    private String email;

    @NotNull(message = "Country cannot be empty")
    private String country;
}
