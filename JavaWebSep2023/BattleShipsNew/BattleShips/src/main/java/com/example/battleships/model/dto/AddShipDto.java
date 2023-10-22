package com.example.battleships.model.dto;



import com.example.battleships.model.entity.CategoryEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AddShipDto {
    @NotNull(message = "The name must be between 2 and 10 characters.")
    @Size(min= 2, max = 10, message = "The name must be between 2 and 10 characters.")
    private String name;

    @NotNull(message = "The health must be positive.")
    @Positive
    private Long health;

    @NotNull(message = "The health must be positive.")
    @Positive
    private Long power;

    @NotNull(message = "The health must be positive.")
    @PastOrPresent(message = "Created date cannot be in the future.")
    private LocalDate created;

    @NotNull(message = "You must select the category.")
    @Enumerated(EnumType.ORDINAL)
    private CategoryEnum categoryEnum;

    public AddShipDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getHealth() {
        return health;
    }

    public void setHealth(Long health) {
        this.health = health;
    }

    public Long getPower() {
        return power;
    }

    public void setPower(Long power) {
        this.power = power;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public CategoryEnum getCategoryEnum() {
        return categoryEnum;
    }

    public void setCategoryEnum(CategoryEnum categoryEnum) {
        this.categoryEnum = categoryEnum;
    }
}
