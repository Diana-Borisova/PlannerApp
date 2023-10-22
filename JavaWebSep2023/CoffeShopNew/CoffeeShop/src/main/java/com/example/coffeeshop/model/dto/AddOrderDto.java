package com.example.coffeeshop.model.dto;

import com.example.coffeeshop.model.entity.CategoryEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AddOrderDto {
    @NotNull(message = "Name must be between 3 and 20 characters.")
    @Size(min= 3, max = 20, message = "Name must be between 3 and 20 characters.")
    private String name;

    @NotNull(message = "The health must be positive.")
    @Positive
    private BigDecimal price;


    @NotNull(message = "Order time must be positive.")
    @PastOrPresent(message = "Order time cannot be in the future.")
    private LocalDateTime orderTime;

    @NotNull(message = "You must select the category.")
    @Enumerated(EnumType.STRING)
    private CategoryEnum categoryEnum;

    @NotNull(message = "Description size must minimum 5 chars.")
    @Size(min= 5, message = "Description size must minimum 5 chars.")
    private String description;
    public AddOrderDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryEnum getCategoryEnum() {
        return categoryEnum;
    }

    public void setCategoryEnum(CategoryEnum categoryEnum) {
        this.categoryEnum = categoryEnum;
    }
}
