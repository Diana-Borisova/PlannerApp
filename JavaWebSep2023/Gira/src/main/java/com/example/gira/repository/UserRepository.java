package com.example.gira.repository;


import com.example.gira.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByUsername(String username);
}
