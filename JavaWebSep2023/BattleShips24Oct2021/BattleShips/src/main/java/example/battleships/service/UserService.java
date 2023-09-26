package example.battleships.service;

import example.battleships.model.dtos.UserRegistrationDto;
import example.battleships.model.entity.User;
import example.battleships.repository.UserRepository;
import example.battleships.model.dtos.UserLoginDto;
import example.battleships.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private LoggedUser loggedUser;
    public UserService(UserRepository userRepository, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    public  boolean register(UserRegistrationDto userRegistrationDto){

        if(!userRegistrationDto.getPassword().equals(userRegistrationDto.getConfirmPassword())){
            return false;
        }
        Optional<User> byEmail = this.userRepository.findByEmail(userRegistrationDto.getEmail());
        if(byEmail.isPresent()){
            return  false;
        }
        Optional<User> byUsername = this.userRepository.findByUsername(userRegistrationDto.getUsername());
        if(byUsername.isPresent()){
            return  false;
        }
        User user = new User();
        user.setUsername(userRegistrationDto.getUsername());
        user.setEmail(userRegistrationDto.getEmail());
        user.setFullName(userRegistrationDto.getFullName());
        user.setPassword(userRegistrationDto.getPassword());
        this.userRepository.save(user);
        return true;
    }

    public boolean login(UserLoginDto userLoginDto) {
       Optional<User> user = this.userRepository.findByUsernameAndPassword(userLoginDto.getUsername(),
               userLoginDto.getPassword());
       if(!user.isPresent()){
            return false;
        }
        loggedUser.setId(user.get().getId());
        loggedUser.setUsername(user.get().getUsername());

       return true;
    }
}
