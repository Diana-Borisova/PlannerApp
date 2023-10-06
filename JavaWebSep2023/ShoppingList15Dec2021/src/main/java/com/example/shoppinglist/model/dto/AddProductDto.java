package com.example.shoppinglist.model.dto;


import com.example.shoppinglist.model.entity.Category;
import com.example.shoppinglist.model.entity.CategoryEnum;
import jakarta.persistence.Column;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class AddProductDto {

    @NotNull
    @Size(min= 2, max = 20, message = "Name length must be between 2 and 20 characters!")
    private String name;

    @NotNull
    @Size(min= 5, message = "Description length must be more then 5 characters!")
    private String description;

    @Positive(message = "Price must be positive number!")
    @NotNull
    private double price;

    @DateTimeFormat
    @FutureOrPresent(message = "The date cannot be in the past!")
    private LocalDateTime neededBefore;

    @NotNull
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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
