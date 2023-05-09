package com.plannerapp.repo;


import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

  @Query("select t, u from Task t join t.user u on u.id = :id")
  List<Task> findAllByUserId(@Param("id") Long id);

  @Query("select t, u from Task t join t.user u on u.id != :id")
  Set<Task> findAllByUserIdNot(@Param("id") Long id);
  Optional<Task> findById(Long id);

  Set<Task> findTasksByUserEmpty();

}
