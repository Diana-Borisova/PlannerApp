package com.likebookapp.repository;


import com.likebookapp.model.entity.Mood;
import com.likebookapp.model.entity.MoodEnum;
import com.likebookapp.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

  @Query("select t, u from Post t join t.creator u on u.id = :id")
  List<Post> findAllByUserId(@Param("id") Long id);

  @Query("select t, u from Post t join t.creator u on u.id != :id")
  Set<Post> findAllByUserIdNot(@Param("id") Long id);
  Optional<Post> findById(Long id);


  Post findPostByMood_Name (MoodEnum mood);
}
