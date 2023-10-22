package com.example.likebook.repository;

import com.example.likebook.model.entity.Mood;

import com.example.likebook.model.entity.MoodEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MoodRepository extends JpaRepository<Mood, Long> {

    Optional<Mood> findByName(MoodEnum name);
}
