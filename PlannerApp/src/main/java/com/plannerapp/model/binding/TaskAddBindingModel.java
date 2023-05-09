package com.plannerapp.model.binding;

import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.PriorityEnum;
import com.plannerapp.model.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskAddBindingModel {

    @NotNull
    @Size(min= 2, max= 50, message = "Description length must be between 3 and 20 characters!")
    private String description;

    @NotNull
    @FutureOrPresent(message = "Due Date must be in future!")
    private LocalDate dueDate;

    @NotNull(message = "You must select a priority!")
    @Enumerated(EnumType.STRING)
    private PriorityEnum priority;
}
