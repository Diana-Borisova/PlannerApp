package com.example.demo.service.impl;


import com.example.demo.entity.User;
import com.example.demo.entity.enums.RoleEnum;
import com.example.demo.entity.service.UserServiceModel;
import com.example.demo.entity.view.UserRoleViewModel;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;


    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;

        this.modelMapper = modelMapper;

    }

    @Override
    public void populateInitialUsers() {
        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@abv.bg");
            admin.setPassword(passwordEncoder.encode("topsecret"));
            admin.setRoles(List.of(
                            userRoleRepository.getUserRoleByName(RoleEnum.ADMIN).orElseThrow(() -> new EntityNotFoundException("UserRole")),
                            userRoleRepository.getUserRoleByName(RoleEnum.USER).orElseThrow(() -> new EntityNotFoundException("UserRole"))
                    ));
            userRepository.save(admin);
        }
    }

    @Override
    public Long registerUser(UserServiceModel userServiceModel) throws IOException {
        User user = modelMapper.map(userServiceModel, User.class);
      user.setUsername(userServiceModel.getUsername());
      user.setEmail(userServiceModel.getEmail());
        user.setPassword(passwordEncoder.
                encode(userServiceModel.getPassword()));

            user.setRoles(List.of(userRoleRepository.
                    getUserRoleByName(RoleEnum.USER).
                    orElseThrow(() -> new EntityNotFoundException("UserRole"))));
        return userRepository.save(user).getId();
    }

    @Override
    public boolean usernameExists(String email) {
        return userRepository.findUserByEmail(email).isPresent();
    }


    @Override
    public User getUserByEmail(String username) {
        return userRepository.findUserByEmail(username).
                orElseThrow(() -> new EntityNotFoundException("User"));
    }



    @Override
    public void updateUser(UserServiceModel userServiceModel) throws IOException {
        User user = userRepository.findById(userServiceModel.getId()).
                orElseThrow(() -> new EntityNotFoundException("User"));
        user.setUsername(userServiceModel.getUsername());
        user.setEmail(userServiceModel.getEmail());
        user.setPassword(user.getPassword());

        userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("User"));
    }

    @Override
    public List<UserRoleViewModel> getAllUsers() {
        return userRepository.
                findAll().
                stream().
                map(u -> {
                    UserRoleViewModel user = modelMapper.map(u, UserRoleViewModel.class);
                    user.setRoles(u.getRoles().
                            stream().
                            map(r -> r.getName().toString()).
                            collect(Collectors.toList()));
                    return user;
                }).
                collect(Collectors.toList());
    }

    @Override
    public void setUserRoles(Long userId, List<RoleEnum> roles) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User"));
        user.setRoles(roles.
                stream().
                map(re -> userRoleRepository.getUserRoleByName(re).orElseThrow(() -> new EntityNotFoundException("Role")))
                .collect(Collectors.toList()));
        userRepository.save(user);
    }


}
