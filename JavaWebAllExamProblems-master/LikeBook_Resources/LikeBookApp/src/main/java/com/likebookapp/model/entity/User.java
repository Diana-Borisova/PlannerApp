package com.likebookapp.model.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter

@AllArgsConstructor

@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_posts",
            joinColumns = @JoinColumn (name ="user_id" ),
            inverseJoinColumns = @JoinColumn (name = "post_id"))
    private Set<Post> likedPosts;

    public User() {
        this.likedPosts= new HashSet<>();
    }
}
