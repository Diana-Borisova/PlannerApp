package com.example.gira.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "classifications")
public class Classification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private ClassificationEnum name;

    @Column(columnDefinition = "TEXT")
    private String description;


    public Classification() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClassificationEnum getName() {
        return name;
    }

    public void setClassificationName(ClassificationEnum name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
