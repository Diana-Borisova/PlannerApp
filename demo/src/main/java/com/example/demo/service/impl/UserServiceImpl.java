package com.example.demo.service.impl;

import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.Constants;
import com.example.demo.model.entity.User;
import com.example.demo.model.entity.enums.RoleEnum;
import com.example.demo.model.entity.view.UserRoleViewModel;
import com.example.demo.model.service.UserServiceModel;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.service.CloudinaryService;
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
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;
   // private final ReservationService reservationService;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, CloudinaryService cloudinaryService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.cloudinaryService = cloudinaryService;
        this.modelMapper = modelMapper;
        //this.reservationService = reservationService;
    }

    @Override
    public void populateInitialUsers() {
        if (userRepository.count() == 0) {
            User admin = new User().
                    setFirstName("Admin").
                    setLastName("Admin").
                    setEmail("admin@abv.bg").
                    setPassword(passwordEncoder.encode("topsecret")).
                    setProfilePicture(Constants.DEFAULT_PROFILE_PICTURE).
                    setRoles(List.of(
                            userRoleRepository.getUserRoleByName(RoleEnum.ADMIN).orElseThrow(() -> new EntityNotFoundException("UserRole")),
                            userRoleRepository.getUserRoleByName(RoleEnum.USER).orElseThrow(() -> new EntityNotFoundException("UserRole"))
                    ));
            userRepository.save(admin);
        }
    }

    @Override
    public Long registerUser(UserServiceModel userServiceModel) throws IOException {
        User user = modelMapper.map(userServiceModel, User.class);
        if (userServiceModel.getProfilePicture()!=null && !userServiceModel.getProfilePicture().isEmpty()) {
            user.setProfilePicture(cloudinaryService.
                    uploadImage(userServiceModel.
                            getProfilePicture()));
        } else {
            user.setProfilePicture(Constants.DEFAULT_PROFILE_PICTURE);
        }
        user.setPassword(passwordEncoder.
                encode(userServiceModel.getPassword()));


        if (userServiceModel.isHotelOwner()) {
            user.setRoles(List.of(userRoleRepository.getUserRoleByName(RoleEnum.USER).
                            orElseThrow(() -> new EntityNotFoundException("UserRole")),
                    userRoleRepository.getUserRoleByName(RoleEnum.HOTEL_OWNER).
                            orElseThrow(() -> new EntityNotFoundException("UserRole"))));
        } else {
            user.setRoles(List.of(userRoleRepository.
                    getUserRoleByName(RoleEnum.USER).
                    orElseThrow(() -> new EntityNotFoundException("UserRole"))));
        }
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

//    @Override
//    public List<ReservationServiceModel> getUserReservationsByEmail(String email) {
//        return reservationService.getReservationsByUser(userRepository.findUserByEmail(email).
//                orElseThrow(() -> new EntityNotFoundException("User")));
//    }


    public void updateUser(UserServiceModel userServiceModel) throws IOException {
        User user = userRepository.findById(userServiceModel.getId()).
                orElseThrow(() -> new EntityNotFoundException("User"));
        user.
                setEmail(userServiceModel.getEmail()).
                setFirstName(userServiceModel.getFirstName()).
                setLastName(userServiceModel.getLastName()).
                setPhoneNumber(userServiceModel.getPhoneNumber());
        if (userServiceModel.getProfilePicture() != null) {
            if (!"".equals(userServiceModel.getProfilePicture().getOriginalFilename())) {
                cloudinaryService.deleteByUrl(user.getProfilePicture());
                user.setProfilePicture(cloudinaryService.uploadImage(userServiceModel.getProfilePicture()));
            }
        }
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

    @Override
    public String getUserProfilePicture(String email) {
        return userRepository.
                findUserByEmail(email).
                orElseThrow(() -> new EntityNotFoundException("User")).
                getProfilePicture();
    }
}
