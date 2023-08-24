package com.likebookapp.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
@Table(name = "moods")
public class Mood extends BaseEntity{
    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private MoodEnum name;

    @Column(columnDefinition = "TEXT")
    private String description;
}
