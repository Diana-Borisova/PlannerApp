package com.example.battleships.repository;

import com.example.battleships.model.entity.Category;
import com.example.battleships.model.entity.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {



    Category findByName(CategoryEnum categoryEnum);
}
