package com.likebookapp.service;


import com.likebookapp.model.binding.UserServiceModel;
import com.likebookapp.model.entity.User;
import com.likebookapp.repository.MoodRepository;
import com.likebookapp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

      private final UserRepository userRepository;
      private final ModelMapper modelMapper;

      public UserService(UserRepository userRepository, ModelMapper modelMapper) {
            this.userRepository = userRepository;
            this.modelMapper = modelMapper;
      }

    public UserServiceModel findByUsername(String username) {
          return this.modelMapper.map(this.userRepository.findByUsername(username).orElse(new User()), UserServiceModel.class);
    }

    public void registerUser(UserServiceModel userServiceModel) {
        this.userRepository.save(modelMapper.map(userServiceModel, User.class));
    }
}
