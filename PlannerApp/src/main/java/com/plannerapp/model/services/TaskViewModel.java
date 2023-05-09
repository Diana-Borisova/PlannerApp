package com.plannerapp.model.services;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


public class TaskViewModel {

    List<TaskServiceModel> assignedTasks;
    List<TaskServiceModel> otherUsersTasks;

    public List<TaskServiceModel> getAssignedTasks() {
        return assignedTasks;
    }

    public void setAssignedTasks(List<TaskServiceModel> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }

    public List<TaskServiceModel> getOtherUsersTasks() {
        return otherUsersTasks;
    }

    public void setOtherUsersTasks(List<TaskServiceModel> otherUsersTasks) {
        this.otherUsersTasks = otherUsersTasks;
    }

    public TaskViewModel() {
        this.assignedTasks = new ArrayList<>();
        this.otherUsersTasks = new ArrayList<>();
    }
}
