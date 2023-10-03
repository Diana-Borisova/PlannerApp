package com.likebookapp.service;

import com.likebookapp.model.dto.UserLoginDto;
import com.likebookapp.model.dto.UserRegistrationDto;
import com.likebookapp.model.entity.Post;
import com.likebookapp.model.entity.User;
import com.likebookapp.repository.PostRepository;
import com.likebookapp.repository.UserRepository;
import com.likebookapp.util.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService  {
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final PostRepository postRepository;

    public UserService(UserRepository userRepository, LoggedUser loggedUser, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.postRepository = postRepository;
    }

    public boolean login(UserLoginDto userLoginDto) {
        Optional<User> user = this.userRepository.findByUsernameAndPassword(userLoginDto.getUsername(),
                userLoginDto.getPassword());
        if(user.isEmpty()){
            return false;
        }
        loggedUser.setId(user.get().getId());
        loggedUser.setUsername(user.get().getUsername());

        return true;
    }

    public boolean register(UserRegistrationDto userRegistrationDto) {

        if (!userRegistrationDto.getPassword().equals(userRegistrationDto.getConfirmPassword())) {
            return false;
        }
        Optional<User> byEmail = this.userRepository.findByEmail(userRegistrationDto.getEmail());
        if (byEmail.isPresent()) {
            return false;
        }

        Optional<User> byUsername = this.userRepository.findByUsername(userRegistrationDto.getUsername());
        if (byUsername.isPresent()) {
            return false;
        }
        User user = new User();
        user.setUsername(userRegistrationDto.getUsername());
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(userRegistrationDto.getPassword());
        this.userRepository.save(user);
        return true;
    }

    public List<Post> getUserContents() {
        return this.postRepository.findAllByUserId(loggedUser.getId());
    }

    public List<Post> getOtherUsersContents() {
        return this.postRepository.findAllByUserIdNot(loggedUser.getId());
    }
}
