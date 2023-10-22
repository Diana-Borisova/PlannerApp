package com.example.shoppinglist.model.dto;


import com.example.shoppinglist.model.entity.Category;
import com.example.shoppinglist.model.entity.CategoryEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AddProductDto {
    @NotNull(message = "Name length must be between 3 and 20 characters!")
    @Size(min= 3, max = 50, message = "Name length must be between 3 and 20 characters!")
    private String name;

    @NotNull(message = "Description length must be more then 5 characters!")
    @Size(min= 5, message = "Description length must be more then 5 characters!")
    private String description;

    @Positive(message = "Price must be positive number!")
    @NotNull(message = "Price must be positive number!")
    private Double price;

    @FutureOrPresent(message = "The date cannot be in the past!")
    private LocalDateTime neededBefore;

    @NotNull()
    @Enumerated(EnumType.STRING)
    private CategoryEnum categoryEnum;

    public AddProductDto() {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getNeededBefore() {
        return neededBefore;
    }

    public void setNeededBefore(LocalDateTime neededBefore) {
        this.neededBefore = neededBefore;
    }


    public CategoryEnum getCategoryEnum() {
        return categoryEnum;
    }

    public void setCategoryEnum(CategoryEnum categoryEnum) {
        this.categoryEnum = categoryEnum;
    }
}
