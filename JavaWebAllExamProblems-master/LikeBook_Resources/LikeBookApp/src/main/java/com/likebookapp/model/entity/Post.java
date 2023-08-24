package com.likebookapp.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
@Setter
@Entity
@Table(name = "posts")
public class Post extends BaseEntity{

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    private Mood mood;

    @ManyToOne
    private User creator;

    @ManyToMany(mappedBy = "likedPosts", fetch = FetchType.EAGER)
    private Set<User> userLikes;

    public Post() {
        this.userLikes = new HashSet<>();
    }
}
