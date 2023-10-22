package com.softuni.heros.service;

import com.softuni.heros.entity.Hero;
import com.softuni.heros.entity.services.HeroServiceModel;

import java.util.List;
import java.util.Optional;


public interface HeroService {


    void createHero(HeroServiceModel heroServiceModel);

    Optional<Hero> findById(String id);

    void deleteItemById(String id);

    List<HeroServiceModel> findAll();

    void deleteHeroById(String id);

    List<Hero> findAllByOrderByLevelDesc();
}
