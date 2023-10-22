package com.example.andrey.repository;


import com.example.andrey.model.entity.CategoryEnum;
import com.example.andrey.model.entity.GenderEnum;
import com.example.andrey.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {


//         @Query("SELECT SUM(a.copies) FROM Album a")
//         Integer findTotalSumOfCopies();

//    @Query("SELECT a.copies FROM Album a WHERE a.id = :albumId")
//    List<Integer> getAllCopiesByAlbumId(@Param("albumId") Long albumId);
   // List<Album> getAllByCopiesOrderByArtist();
    CategoryEnum findByCategory_Id(Long id);

}
