package com.likebookapp.repository;

import com.likebookapp.model.entity.Post;
import com.likebookapp.model.entity.User;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Registered
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUserId(Long id);

    List<Post> findAllByUserIdNot(Long id);
}
