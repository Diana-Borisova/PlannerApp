package com.likebookapp.model.binding;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginBindingModel {

    @NotNull(message = "Username cannot be empty!")
    @Size(min= 3, max= 20, message = "Username length must be between 3 and 20 characters!")
    private String username;

    @NotNull(message = "Password cannot be empty!")
    @Size(min= 3, max= 20,message = "Password length must be between 3 and 20 characters!")
    private String password;
}
