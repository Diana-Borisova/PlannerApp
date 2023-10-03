package com.softuni.spotifyplaylist.service;

import com.softuni.spotifyplaylist.model.entity.Style;
import com.softuni.spotifyplaylist.model.entity.StyleEnum;
import com.softuni.spotifyplaylist.repository.StyleRepository;
import org.springframework.stereotype.Service;

@Service
public class StyleService {
    private final StyleRepository styleRepository;

    public StyleService(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    public Style findByName(StyleEnum styleEnum){
        return this.styleRepository.findByName(styleEnum).orElseThrow();
    }
}
