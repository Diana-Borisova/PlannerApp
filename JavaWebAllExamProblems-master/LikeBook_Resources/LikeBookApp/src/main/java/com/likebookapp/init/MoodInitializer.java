package com.likebookapp.init;

import com.likebookapp.service.MoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MoodInitializer implements CommandLineRunner {

    private final MoodService moodService;



    @Autowired
    public MoodInitializer(MoodService moodService) {
        this.moodService = moodService;
    }


    @Override
    public void run(String... args) throws Exception {
       moodService.initMoods();
    }
}
