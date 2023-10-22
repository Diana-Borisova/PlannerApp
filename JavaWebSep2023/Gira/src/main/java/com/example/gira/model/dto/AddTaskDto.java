package com.example.gira.model.dto;


import com.example.gira.model.entity.ClassificationEnum;
import com.example.gira.model.entity.ProgressEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AddTaskDto {

    @NotNull
    @Size(min= 3, max = 20, message = "Name length must be between 3 and 20 characters!")
    private String name;

    @NotNull
    @Size(min= 5, message = "Description min length must be more than 5 characters!")
    private String description;



    @FutureOrPresent(message = "The date cannot be in the past!")
    private LocalDate dueDae;
    @NotNull(message = "Classification cannot be null!")
    @Enumerated(EnumType.STRING)
    private ClassificationEnum classificationName;

    public AddTaskDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDae() {
        return dueDae;
    }

    public void setDueDae(LocalDate dueDae) {
        this.dueDae = dueDae;
    }

    public ClassificationEnum getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(ClassificationEnum classificationName) {
        this.classificationName = classificationName;
    }
}
