package com.likebookapp.service;


import com.likebookapp.model.entity.Mood;
import com.likebookapp.model.entity.MoodEnum;
import com.likebookapp.repository.MoodRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class MoodService  {

      private final MoodRepository moodRepository;
      private final ModelMapper modelMapper;

      public MoodService(MoodRepository moodRepository, ModelMapper modelMapper) {
            this.moodRepository = moodRepository;
            this.modelMapper = modelMapper;
      }

      public void initMoods() {
            if (this.moodRepository.count() != 0) {
                  return;
            }

            Arrays.stream(MoodEnum.values())
                    .forEach(moodEnum -> {
                          Mood mood = new Mood();
                          if (moodEnum.equals(MoodEnum.Sad)){
                               mood.setName(MoodEnum.Sad);

                          } else if (moodEnum.equals(MoodEnum.Happy)) {
                                mood.setName(MoodEnum.Happy);

                          } else {
                                mood.setName(MoodEnum.Inspired);
                          }

                          this.moodRepository.save(mood);
                    });

      }

      public Optional<Mood> findByName(MoodEnum mood) {
            return this.moodRepository.findMoodByName(mood);
      }
}
