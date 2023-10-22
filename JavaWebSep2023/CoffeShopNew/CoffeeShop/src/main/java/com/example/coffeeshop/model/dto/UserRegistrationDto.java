package com.example.coffeeshop.model.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRegistrationDto {
    @Size(min= 5, max = 20, message = "Username length must be between 5 and 20 characters!")
    @NotNull(message = "Username length must be between 5 and 20 characters!")
    private String username;


    private String firstName;

    @Size(min= 5, max = 20, message = "Last name length must be between 5 and 20 characters!")
    @NotNull(message = "Last name length must be between 5 and 20 characters!")
    private String lastName;

    @Size(min= 3, message = "Password length must be more than 3 characters!")
    @NotNull(message = "Password length must be more than 3 characters!")
    private String password;

    @Size(min= 3, message = "Password length must be more than 3 characters!")
    @NotNull(message = "Password length must be more than 3 characters!")
    private String confirmPassword;

    @NotBlank(message = "Enter valid email address.")
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


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
