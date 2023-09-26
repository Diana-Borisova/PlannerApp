package com.coffeeshop.model.dto;

import com.coffeeshop.model.entity.Category;
import com.coffeeshop.model.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AddOrderDto {

    @Size(min= 3, max = 20)
    @NotNull
    private String name;

    @Positive
    @NotNull
    private BigDecimal price;

    @PastOrPresent
    @NotNull
    private LocalDateTime orderTime;

    @NotNull
    private String category;

    @Size(min= 5)
    @NotNull
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
