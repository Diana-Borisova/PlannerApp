package com.example.andrey.repository;

import com.example.andrey.model.entity.Category;
import com.example.andrey.model.entity.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(CategoryEnum name);



}
