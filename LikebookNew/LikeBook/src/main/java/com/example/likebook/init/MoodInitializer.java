package com.example.likebook.init;



import com.example.likebook.model.entity.Mood;
import com.example.likebook.model.entity.MoodEnum;
import com.example.likebook.repository.MoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MoodInitializer implements CommandLineRunner {

    private final MoodRepository moodRepository;
    @Autowired
    public MoodInitializer(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        if (this.moodRepository.count() != 0) {
            return;
        }

        Arrays.stream(MoodEnum.values())
                .forEach(name -> {

                   Mood mood = new Mood();
                    mood.setName(name);
                    this.moodRepository.save(mood);
                });
    }

}
