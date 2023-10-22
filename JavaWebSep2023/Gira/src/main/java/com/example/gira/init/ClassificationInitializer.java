package com.example.gira.init;



import com.example.gira.model.entity.Classification;
import com.example.gira.model.entity.ClassificationEnum;
import com.example.gira.repository.ClassificationRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ClassificationInitializer implements CommandLineRunner {

    private final ClassificationRepository classificationRepository;

    public ClassificationInitializer(ClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.classificationRepository.count() != 0) {
            return;
        }

        Arrays.stream(ClassificationEnum.values())
                .forEach(name -> {
                    Classification classification = new Classification();
                    classification.setClassificationName(name);
                    this.classificationRepository.save(classification);
                });
    }
}
