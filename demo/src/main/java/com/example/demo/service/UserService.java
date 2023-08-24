package com.example.demo.service;



import com.example.demo.entity.User;
import com.example.demo.entity.enums.RoleEnum;
import com.example.demo.entity.service.UserServiceModel;
import com.example.demo.entity.view.UserRoleViewModel;

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


}
