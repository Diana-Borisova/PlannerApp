package com.coffeeshop.model.dto;

import com.coffeeshop.model.entity.Order;
import com.coffeeshop.model.entity.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDto {

    @Positive
    private Long id;
    @Size(min= 3, max = 20)
    @NotNull
    private String name;

    @Positive
    @NotNull
    private BigDecimal price;


    @NotNull
    private String category;

    @Positive
    private int neededTime;

    @NotNull
    private String employee;


    public OrderDto(Order order) {
        this.id = order.getId();
        this.category = order.getCategory().getName();
        this.neededTime = order.getCategory().getNeededTime();
        this.name = order.getName();
        this.price = order.getPrice();
        this.employee = order.getEmployee().getUsername();
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getNeededTime() {
        return neededTime;
    }

    public void setNeededTime(int neededTime) {
        this.neededTime = neededTime;
    }

    public Long getId() {
        return id;
    }

    public String getEmployee() {
        return employee;
    }
}
