package com.plannerapp.model.entity;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
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
