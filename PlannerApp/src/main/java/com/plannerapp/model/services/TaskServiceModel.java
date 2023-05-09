package com.plannerapp.model.services;

import com.plannerapp.model.entity.PriorityEnum;
import com.plannerapp.model.entity.Task;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.function.Function;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskServiceModel  {

   private Long id;

   private String description;

   private LocalDate dueDate;

   private PriorityEnum priority;
}
