package com.plannerapp.service;


import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import com.plannerapp.model.services.TaskServiceModel;
import com.plannerapp.model.services.TaskViewModel;
import com.plannerapp.repo.TaskRepository;
import com.plannerapp.repo.UserRepository;
import com.plannerapp.util.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final PriorityService priorityService;
    private final ModelMapper modelMapper;
    private final UserService userService;

    private final LoggedUser loggedUser;
    private final UserRepository userRepository;


    public TaskService(TaskRepository taskRepository, PriorityService priorityService, ModelMapper modelMapper, UserService userService, LoggedUser loggedUser, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.priorityService = priorityService;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
    }

    public void addTask(TaskServiceModel taskServiceModel) {
        Task task = modelMapper.map(taskServiceModel, Task.class);
        task.setDescription(taskServiceModel.getDescription());
        task.setDueDate(taskServiceModel.getDueDate());
        task.setPriority(this.priorityService.findByName(taskServiceModel.getPriority()));
        this.taskRepository.save(task);
        Set<Task> allAvailableTasks = this.taskRepository.findTasksByUserEmpty().stream().collect(Collectors.toSet());
        allAvailableTasks.add(task);

    }


    public void addTaskToMe(Long id) {
        Optional<Task> task = this.taskRepository.findById(id);
        if (task.isPresent()) {
            User user = this.userService.findById(loggedUser.getId());
            Set<TaskServiceModel> otherUsersTasks = this.taskRepository.findAllByUserIdNot(id)
                    .stream()
                    .map(task1 -> modelMapper.map(task1, TaskServiceModel.class))
                    .collect(Collectors.toSet());
            if (otherUsersTasks.size() > 0 && this.taskRepository.findById(id).isPresent()) {
                otherUsersTasks.remove(modelMapper.map(task, TaskServiceModel.class));
            }
            Set<TaskServiceModel> assignedTasks = this.taskRepository.findAllByUserId(id)
                    .stream()
                    .map(task1 -> modelMapper.map(task1, TaskServiceModel.class))
                    .collect(Collectors.toSet());

            otherUsersTasks.remove(modelMapper.map(task, TaskServiceModel.class));
            this.taskRepository.deleteById(id);
            assignedTasks.add(modelMapper.map(task, TaskServiceModel.class));
            userService.addTaskToUser(loggedUser.getId(), task.get());
            userService.save(user);

        }
    }

    public Set<TaskServiceModel> removeTask(Long id) {
        Task task = taskRepository.findById(id).orElse(null);

        User user = userService.findById(loggedUser.getId());
        Set<TaskServiceModel> myTasks = new HashSet<>(getAssignedTasks(loggedUser.getId()));

       userService.removeTaskFromUser(loggedUser.getId(), task);
        myTasks.remove(modelMapper.map(task,TaskServiceModel.class));
        taskRepository.delete(task);

        return myTasks;
    }


    public Set<TaskServiceModel> getAssignedTasks(Long id) {
        return this.taskRepository.findAllByUserId(id)
                .stream()
                .map(task -> modelMapper.map(task, TaskServiceModel.class))
                .collect(Collectors.toSet());

    }



    public Set<Task> getAllAvailableTasks() {
        return this.taskRepository.findTasksByUserEmpty();
    }

    public void returnTaskToAllAvailableTasks(Long id) {
        Optional<Task> task = taskRepository.findById(id);

        User user = userService.findById(loggedUser.getId());
        Set<TaskServiceModel> myTasks = new HashSet<>(getAssignedTasks(loggedUser.getId()));
        myTasks.remove(modelMapper.map(task,TaskServiceModel.class));
        userService.removeTaskFromUser(loggedUser.getId(), task.get());

        Set<TaskServiceModel> returnedTask = this.taskRepository.findById(id)
                .stream()
                .map(task1 -> modelMapper.map(task1, TaskServiceModel.class))
                .collect(Collectors.toSet());
        returnedTask.clear();
        userRepository.saveAndFlush(user);

    }


}