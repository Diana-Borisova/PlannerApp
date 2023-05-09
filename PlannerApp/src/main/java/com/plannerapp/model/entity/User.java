package com.plannerapp.model.entity;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_tasks",
            joinColumns = @JoinColumn (name ="user_id" ),
            inverseJoinColumns = @JoinColumn (name = "task_id"))
    private List<Task> assignedTasks;

    public User() {
        this.assignedTasks = new ArrayList<>();
    }



    public void addTaskToLoggedUser(Task task) {
        this.assignedTasks.add(task);
    }

    public void removeTaskFromLoggedUser(Task task) {
        this.assignedTasks.remove(task);
    }

    public List<Task> getAssignedTasks(){
        return assignedTasks;
    }
}
