package com.coffeeshop.model.entity;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "needed_time", nullable = false)
    private int neededTime;

    public Category() {
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

    public void setNeededTime(int neededTime) {
        this.neededTime = neededTime;
    }

    public int getNeededTime() {
        return neededTime;
    }
}
