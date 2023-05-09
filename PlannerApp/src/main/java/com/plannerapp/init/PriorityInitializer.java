package com.plannerapp.init;


import com.plannerapp.repo.PriorityRepository;
import com.plannerapp.service.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class PriorityInitializer implements CommandLineRunner {

    private final PriorityService priorityService;

    @Autowired
    public PriorityInitializer(PriorityService priorityService) {
      this.priorityService = priorityService;
    }


    @Override
    public void run(String... args) throws Exception {
       priorityService.initPriorities();
    }
}
