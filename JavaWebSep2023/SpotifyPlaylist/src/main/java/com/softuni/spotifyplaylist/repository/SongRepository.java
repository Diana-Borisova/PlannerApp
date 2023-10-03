package com.softuni.spotifyplaylist.repository;

import com.softuni.spotifyplaylist.model.dto.SongDto;
import com.softuni.spotifyplaylist.model.entity.Song;
import com.softuni.spotifyplaylist.model.entity.Style;
import com.softuni.spotifyplaylist.model.entity.StyleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {


    Set<Song> findByStyle(Style style);
    @Query("select s, u from Song s join s.users u on u.id = :id")
    Set<Song> findAllByUserId(@Param("id") Long id);

    Song findSongById(Long songId);
}
