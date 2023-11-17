package com.example.demo.service;



import com.example.demo.model.entity.User;
import com.example.demo.model.entity.enums.RoleEnum;

import com.example.demo.model.entity.view.UserRoleViewModel;
import com.example.demo.model.service.UserServiceModel;

import java.io.IOException;
import java.util.List;

public interface UserService {
    void populateInitialUsers();

    Long registerUser(UserServiceModel userServiceModel) throws IOException;

    boolean usernameExists(String email);

    User getUserByEmail(String username);



    void updateUser(UserServiceModel userServiceModel) throws IOException;

    User getUserById(Long id);

    List<UserRoleViewModel> getAllUsers();

    void setUserRoles(Long userId, List<RoleEnum> roles);

    String getUserProfilePicture(String username);
}
