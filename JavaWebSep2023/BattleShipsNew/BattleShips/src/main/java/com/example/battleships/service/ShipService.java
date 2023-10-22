package com.example.battleships.service;


import com.example.battleships.model.dto.AddShipDto;
import com.example.battleships.model.dto.BattleDto;
import com.example.battleships.model.entity.Category;
import com.example.battleships.model.entity.Ship;
import com.example.battleships.model.entity.User;
import com.example.battleships.repository.CategoryRepository;
import com.example.battleships.repository.ShipRepository;
import com.example.battleships.repository.UserRepository;
import com.example.battleships.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShipService {

    private final ShipRepository shipRepository;
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;

    private final CategoryRepository categoryRepository;
    @Autowired
    public ShipService(ShipRepository shipRepository, UserRepository userRepository, LoggedUser loggedUser, CategoryRepository categoryRepository) {
        this.shipRepository = shipRepository;

        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.categoryRepository = categoryRepository;
    }




    public boolean create(AddShipDto addShipDto) {
        User user = this.userRepository.findById(loggedUser.getId()).orElseThrow();
        Category category = this.categoryRepository.findByName(addShipDto.getCategoryEnum());
        Ship ship = new Ship();
        ship.setCategory(category);
        ship.setCreated(addShipDto.getCreated());
        ship.setHealth(addShipDto.getHealth());
        ship.setPower(addShipDto.getPower());
        ship.setUser(user);
        ship.setName(addShipDto.getName());
        this.shipRepository.save(ship);


        return true;
    }

    public Set<Ship> getOwnShips() {
        User user = this.userRepository.findById(loggedUser.getId()).orElseThrow();
       return this.shipRepository.findAllByUserId(user.getId());
    }

    public Set<Ship> getOtherShips() {
        User user = this.userRepository.findById(loggedUser.getId()).orElseThrow();
        return this.shipRepository.findAllByUserIdNot(user.getId());
    }

    public void attack(BattleDto battleDto) throws Exception {
        Optional<Ship> attacker = this.shipRepository.findById((long)battleDto.getAttackerId());
        Optional<Ship> defender = this.shipRepository.findById((long)battleDto.getDefenderId());

        if (attacker.isEmpty() || defender.isEmpty()) {
            throw new Exception();
        }


        long newDefenderHealth = defender.get().getHealth() - attacker.get().getPower();

        if (newDefenderHealth <= 0) {
            this.shipRepository.delete(defender.get());
        } else {
            defender.get().setHealth(newDefenderHealth);
            this.shipRepository.save(defender.get());
        }
    }

    public List<Ship> getSortedShips() {
     List<Ship> allShips = this.shipRepository.findAll();

        return allShips.stream()
                .sorted(Comparator.comparingLong(Ship::getHealth).reversed())
                .collect(Collectors.toCollection(ArrayList::new));
    }


}
