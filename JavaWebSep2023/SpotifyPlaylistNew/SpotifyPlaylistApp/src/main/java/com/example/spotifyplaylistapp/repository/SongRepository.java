package com.example.spotifyplaylistapp.repository;

import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.StyleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    Set<Song> findByStyleName(StyleEnum styleEnum);
}
