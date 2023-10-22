package com.softuni.heros.service.serviceImpl;

import com.softuni.heros.entity.Hero;
import com.softuni.heros.entity.services.HeroServiceModel;
import com.softuni.heros.repository.HeroRepository;
import com.softuni.heros.service.HeroService;
import com.softuni.heros.utils.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HeroServiceImpl implements HeroService {
    private final ModelMapper modelMapper;
    private final HeroRepository heroRepository;
    private final LoggedUser loggedUser;

    public HeroServiceImpl(ModelMapper modelMapper, HeroRepository heroRepository, LoggedUser loggedUser) {
        this.modelMapper = modelMapper;
        this.heroRepository = heroRepository;
        this.loggedUser = loggedUser;
    }


    @Override
    public void createHero(HeroServiceModel heroServiceModel) {
        Hero hero = modelMapper.map(heroServiceModel, Hero.class);
        hero.setName(heroServiceModel.getName());
        hero.setLevel(heroServiceModel.getLevel());
        hero.setClassEnum(heroServiceModel.getClassEnum());

        this.heroRepository.save(hero);
    }

    @Override
    public Optional<Hero> findById(String id) {
        return this.heroRepository.findById(id);
    }

    @Override
    public void deleteItemById(String id) {
        this.heroRepository.deleteById(id);
    }

    @Override
    public List<HeroServiceModel> findAll() {
        return  heroRepository.findAll()
                .stream()
                .map(hero -> modelMapper.map(hero,HeroServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteHeroById(String id) {
        this.heroRepository.deleteById(id);
    }

    @Override
    public List<Hero> findAllByOrderByLevelDesc() {
        return this.heroRepository.findAllByOrderByLevelDesc()
                .stream()
                .map(hero -> modelMapper.map(hero,Hero.class))
                .collect(Collectors.toList());
    }

}
