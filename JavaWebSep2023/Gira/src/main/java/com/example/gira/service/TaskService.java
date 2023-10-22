package com.example.gira.service;


import com.example.gira.model.dto.AddTaskDto;
import com.example.gira.model.entity.Classification;
import com.example.gira.model.entity.ProgressEnum;
import com.example.gira.model.entity.Task;
import com.example.gira.model.entity.User;
import com.example.gira.repository.ClassificationRepository;
import com.example.gira.repository.TaskRepository;
import com.example.gira.repository.UserRepository;
import com.example.gira.util.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final ClassificationRepository classificationRepository;
    private final TaskRepository taskRepository;



    public TaskService(UserRepository userRepository, LoggedUser loggedUser, ClassificationRepository classificationRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;

        this.classificationRepository = classificationRepository;
        this.taskRepository = taskRepository;
    }

    public boolean create(AddTaskDto addTaskDto) {
        Optional<User> user = this.userRepository.findById(this.loggedUser.getId());
        Classification classification = this.classificationRepository.findByName(addTaskDto.getClassificationName());
        Task task = new Task();
       task.setDescription(addTaskDto.getDescription());
       task.setName(addTaskDto.getName());
       task.setProgress(ProgressEnum.OPEN);
       task.setClassification(this.classificationRepository.findByName(addTaskDto.getClassificationName()));
       task.setClassificationName(classification.getName());
       task.setDueDae(addTaskDto.getDueDae());
       task.setUser(user.get());

        this.taskRepository.save(task);
        return true;
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(this.taskRepository.findAll());
   }

    public void changeProgress(Long id){
        Task task = this.taskRepository.findById(id).orElseThrow();
        String taskProgress = task.getProgress().name();
        switch (taskProgress){
            case "OPEN":
                task.setProgress(ProgressEnum.IN_PROGRESS);
                this.taskRepository.save(task);
                break;
            case "IN_PROGRESS":
                task.setProgress(ProgressEnum.COMPLETED);
                this.taskRepository.save(task);
                break;
            case "COMPLETED":
                this.taskRepository.deleteById(id);
                break;


        }

    }



//    public int getCountOfAllCopies(){
//
//        return this.albumRepository.findTotalSumOfCopies();
//    }
//
//
//    public List<Album> getAllAlbums(){
//        return new ArrayList<>(this.albumRepository.findAll());
//    }
//
//    public void removeAlbum(Long albumId) {
//        this.albumRepository.deleteById(albumId);
//    }
}
