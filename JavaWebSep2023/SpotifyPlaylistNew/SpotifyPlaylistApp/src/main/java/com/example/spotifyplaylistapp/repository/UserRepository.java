package com.example.spotifyplaylistapp.repository;

import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.StyleEnum;
import com.example.spotifyplaylistapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);
}
