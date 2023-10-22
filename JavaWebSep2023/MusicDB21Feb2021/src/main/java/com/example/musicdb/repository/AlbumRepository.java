package com.example.musicdb.repository;

import com.example.musicdb.model.entity.Album;
import com.example.musicdb.model.entity.Artist;
import com.example.musicdb.model.entity.GenreEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

        Album findAlbumByGenreEnum(GenreEnum name);

         @Query("SELECT SUM(a.copies) FROM Album a")
         Integer findTotalSumOfCopies();

//    @Query("SELECT a.copies FROM Album a WHERE a.id = :albumId")
//    List<Integer> getAllCopiesByAlbumId(@Param("albumId") Long albumId);
   // List<Album> getAllByCopiesOrderByArtist();


}
