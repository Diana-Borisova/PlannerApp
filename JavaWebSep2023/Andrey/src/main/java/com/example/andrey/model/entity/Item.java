package com.example.andrey.model.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;


    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GenderEnum genderEnum;

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;

    public Item() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public GenderEnum getGenderEnum() {
        return genderEnum;
    }

    public void setGenderEnum(GenderEnum genderEnum) {
        this.genderEnum = genderEnum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory(Category byName) {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public CategoryEnum categoryEnum (Category category){

        return this.category.getName();
    }

    public Category getCategory() {
        return category;
    }
}
