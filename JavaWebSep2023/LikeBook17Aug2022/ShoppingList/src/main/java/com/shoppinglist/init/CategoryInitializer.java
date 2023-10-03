package com.shoppinglist.init;


import com.shoppinglist.model.entity.Category;
import com.shoppinglist.model.entity.CategoryEnum;
import com.shoppinglist.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CategoryInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public CategoryInitializer(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
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
