package com.example.likebook.repository;

import com.example.likebook.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
   // @Query("select p, u from Post p join p.creator u on p.id = :id")
    Optional<List<Post>> findAllByCreator_Id(Long id);

    //@Query("select p, u from Post p join p.creator u on p.id != :id")
    Optional<List<Post>> findAllByCreator_IdNot(Long id);
}
