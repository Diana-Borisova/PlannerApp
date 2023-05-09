package com.plannerapp.service;


import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.PriorityEnum;
import com.plannerapp.repo.PriorityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class PriorityService  {

    private final PriorityRepository priorityRepository;

    private final ModelMapper modelMapper;


    public PriorityService(PriorityRepository priorityRepository, ModelMapper modelMapper) {
        this.priorityRepository = priorityRepository;
        this.modelMapper = modelMapper;
    }

    public void initPriorities() {
        if (this.priorityRepository.count() != 0) {
            return;
        }

        Arrays.stream(PriorityEnum.values())
                .forEach(priorityEnum -> {
                    Priority priority = new Priority();
                    if (priorityEnum.equals(PriorityEnum.LOW)){
                        priority.setName(PriorityEnum.LOW);
                        priority.setDescription("Should be fixed if time permits but can be postponed.");
                    } else if (priorityEnum.equals(PriorityEnum.IMPORTANT)) {
                        priority.setName(PriorityEnum.IMPORTANT);
                        priority.setDescription("A core functionality that your product is explicitly supposed to perform is compromised.");

                    } else {
                        priority.setName(PriorityEnum.URGENT);
                        priority.setDescription("An urgent problem that blocks the system use until the issue is resolved.");
                    }

                    this.priorityRepository.save(priority);
                });

    }

    public Priority findByName(PriorityEnum priority) {
        return this.priorityRepository.findPriorityByName(priority).orElseThrow();
    }
}
