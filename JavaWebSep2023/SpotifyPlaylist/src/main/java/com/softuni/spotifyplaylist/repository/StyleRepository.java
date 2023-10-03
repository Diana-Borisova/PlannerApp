package com.softuni.spotifyplaylist.repository;

import com.softuni.spotifyplaylist.model.entity.Style;
import com.softuni.spotifyplaylist.model.entity.StyleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StyleRepository extends JpaRepository<Style, Long> {

    Optional<Style> findByName(StyleEnum styleEnum);

}
