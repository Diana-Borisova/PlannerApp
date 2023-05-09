package com.plannerapp.service;


import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import com.plannerapp.model.services.TaskServiceModel;
import com.plannerapp.model.services.UserServiceModel;
import com.plannerapp.repo.TaskRepository;
import com.plannerapp.repo.UserRepository;
import com.plannerapp.util.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService  {
    private final UserRepository userRepository;

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;


    public UserService(UserRepository userRepository, TaskRepository taskRepository, ModelMapper modelMapper, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;

        this.loggedUser = loggedUser;
    }

    public void registerUser(UserServiceModel userServiceModel) {
        this.userRepository.save(modelMapper.map(userServiceModel, User.class));
    }

    public UserServiceModel findByUsername(String username){
        return this.modelMapper.map(this.userRepository.findByUsername(username).orElse(new User()), UserServiceModel.class);
    }

    public Optional<User> findUserById(Long id) {
        return this.userRepository.findById(id);
    }

    public User findById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public List<Task> getAllUserTasks() {
        return this.taskRepository.findAllByUserId(loggedUser.getId());
    }

    public void addTaskToUser(Long userId, Task task) {

        User user= this.userRepository.findById(userId).orElse(null);
        if (user != null){
            //if (user.getAssignedTasks().stream().noneMatch(t -> t.getId().equals(task.getId()))) {
                user.addTaskToLoggedUser(task);
                modelMapper.map(this.userRepository.save(user), UserServiceModel.class);

            }
    //}
    }

    public void removeTaskFromUser(Long userId, Task task) {

        User user = this.userRepository.findById(userId).orElse(null);
        if (user != null) {

                user.getAssignedTasks().removeIf(t -> t.getId().equals(task.getId()));

                taskRepository.deleteById(task.getId());
            userRepository.saveAndFlush(user);

            }
        }



    public void save(User user) {
        this.userRepository.save(user);
    }
}
