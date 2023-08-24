package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserRole> roles;
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

    public List<UserRole> getRoles() {
        return roles;
    }

    public User setRoles(List<UserRole> roles) {
        this.roles = roles;
        return this;
    }

    public void addRole(UserRole userRole) {
        if (this.roles == null) {
            this.roles = List.of(userRole);
        } else {
            this.roles.add(userRole);
        }
    }
}
