package com.example.battleships.init;


import com.example.battleships.model.entity.Category;
import com.example.battleships.model.entity.CategoryEnum;
import com.example.battleships.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CategoryInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryInitializer(CategoryRepository categoryRepository) {
        this.categoryRepository =categoryRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        if (this.categoryRepository.count() != 0) {
            return;
        }

        Arrays.stream(CategoryEnum.values())
                .forEach(name -> {

                   Category category = new Category();
                    category.setName(name);
                    this.categoryRepository.save(category);
                });
    }

}
