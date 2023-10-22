package com.example.gira.repository;

import com.example.gira.model.entity.Classification;
import com.example.gira.model.entity.ClassificationEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificationRepository extends JpaRepository<Classification, Long> {

    Classification findByName(ClassificationEnum name);



}
