package com.softuni.spotifyplaylist.init;

import com.softuni.spotifyplaylist.model.entity.Style;
import com.softuni.spotifyplaylist.model.entity.StyleEnum;
import com.softuni.spotifyplaylist.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class StyleInitializer implements CommandLineRunner {

    private final StyleRepository styleRepository;

    @Autowired
    public StyleInitializer(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        if (this.styleRepository.count() != 0) {
            return;
        }

        Arrays.stream(StyleEnum.values())
                .forEach(name -> {
                    Style style = new Style();
                    style.setName(name);
                    this.styleRepository.save(style);
                });
    }
}
