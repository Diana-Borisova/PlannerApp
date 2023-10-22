package com.softuni.heros.repository;

import com.softuni.heros.entity.Hero;
import com.softuni.heros.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HeroRepository extends JpaRepository<Hero,String> {
Optional<Hero> findById(String id);

List<Hero> findAllByOrderByLevelDesc();
}
