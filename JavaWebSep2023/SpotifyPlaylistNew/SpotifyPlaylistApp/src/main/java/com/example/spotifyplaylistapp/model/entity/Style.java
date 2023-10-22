package com.example.spotifyplaylistapp.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "styles")
public class Style {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StyleEnum name;

    @Column(nullable = true)
    private String description;

    public Style() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StyleEnum getName() {
        return name;
    }

    public void setName(StyleEnum name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
