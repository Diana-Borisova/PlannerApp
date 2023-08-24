package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
@Setter
@Entity
@Table(name = "tasks")
public class Task extends BaseEntity{

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    @FutureOrPresent
    private LocalDate dueDate;

    @ManyToMany(mappedBy = "assignedTasks", fetch = FetchType.EAGER)
   private Set<User> user;

    @ManyToOne
    private Priority priority;

    public Task() {
        this.user =new HashSet<>();
    }
}
