package com.example.musicdb.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String careerInformation ;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private ArtistEnum name;

    public Artist() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCareerInformation() {
        return careerInformation;
    }

    public void setCareerInformation(String careerInformation) {
        this.careerInformation = careerInformation;
    }

    public ArtistEnum getName() {
        return name;
    }

    public void setName(ArtistEnum name) {
        this.name = name;
    }
}
