package com.example.gira.model.entity;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProgressEnum progress;

    @Column(nullable = false)
    private LocalDate dueDae;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClassificationEnum classificationName;

    @ManyToOne
    private User user;

    @ManyToOne
    private Classification classification;

    public Task() {
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
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

    public ProgressEnum getProgress() {
        return progress;
    }

    public void setProgress(ProgressEnum progress) {
        this.progress = progress;
    }

    public LocalDate getDueDae() {
        return dueDae;
    }

    public void setDueDae(LocalDate dueDae) {
        this.dueDae = dueDae;
    }

    public ClassificationEnum getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(ClassificationEnum classificationName) {
        this.classificationName = classificationName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
