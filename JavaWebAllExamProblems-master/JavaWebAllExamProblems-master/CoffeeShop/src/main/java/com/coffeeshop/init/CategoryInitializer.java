package com.coffeeshop.init;
import com.coffeeshop.model.entity.Category;
import com.coffeeshop.model.entity.CategoryEnum;
import com.coffeeshop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CategoryInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    @Autowired
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
                    if (name.equals(CategoryEnum.DRINK)){
                        category.setName("Drink");
                        category.setNeededTime(1);
                    } else if (name.equals(CategoryEnum.COFFEE)) {
                        category.setName("Coffee");
                        category.setNeededTime(2);
                    } else if (name.equals(CategoryEnum.CAKE)) {
                        category.setName("Cake");
                        category.setNeededTime(10);
                   } else if (name.equals(CategoryEnum.OTHER)) {
                        category.setName("Other");
                        category.setNeededTime(5);

                    }

                    this.categoryRepository.save(category);
                });
    }
}
