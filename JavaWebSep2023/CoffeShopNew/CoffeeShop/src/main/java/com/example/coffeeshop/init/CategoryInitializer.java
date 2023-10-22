package com.example.coffeeshop.init;


import com.example.coffeeshop.model.entity.Category;
import com.example.coffeeshop.model.entity.CategoryEnum;
import com.example.coffeeshop.repository.CategoryRepository;
import org.apache.catalina.webresources.AbstractResource;
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
                    switch (name){
                        case Cake -> {
                            category.setNeededTime(10);
                            break;
                        }
                        case Coffee -> {
                            category.setNeededTime(2);
                            break;
                        }
                        case Other -> {
                            category.setNeededTime(5);
                            break;
                        }
                        case Drink -> {
                            category.setNeededTime(1);
                            break;
                        }
                    }
                    this.categoryRepository.save(category);
                });
    }

}
