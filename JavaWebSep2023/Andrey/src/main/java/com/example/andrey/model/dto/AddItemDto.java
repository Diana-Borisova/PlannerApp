package com.example.andrey.model.dto;

import com.example.andrey.model.entity.Category;
import com.example.andrey.model.entity.CategoryEnum;
import com.example.andrey.model.entity.GenderEnum;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AddItemDto {

    @NotNull
    @Size(min= 3, max = 20, message = "Incorrect name!")
    private String name;

    @NotNull
    @Size(min= 5, message = "Incorrect description!")
    private String description;

    @NotNull
    private CategoryEnum categoryEnum;

    @NotNull
    private GenderEnum genderEnum;

    @NotNull
    @Positive(message = "Incorrect price!")
    private BigDecimal price;




    public AddItemDto() {
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

    public CategoryEnum getCategoryEnum() {
        return categoryEnum;
    }

    public void setCategoryEnum(CategoryEnum categoryEnum) {
        this.categoryEnum = categoryEnum;
    }

    public GenderEnum getGenderEnum() {
        return genderEnum;
    }

    public void setGenderEnum(GenderEnum genderEnum) {
        this.genderEnum = genderEnum;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
