package com.example.shoppinglist.repository;

import com.example.shoppinglist.model.entity.Category;
import com.example.shoppinglist.model.entity.CategoryEnum;
import com.example.shoppinglist.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Set<Product> findAllByCategory_CategoryEnum(CategoryEnum categoryEnum);
    Set<Product> findAllByCategory(Category category);
}
