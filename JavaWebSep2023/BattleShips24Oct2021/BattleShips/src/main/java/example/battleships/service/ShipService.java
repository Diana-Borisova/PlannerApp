package example.battleships.service;

import example.battleships.model.dtos.AddShipDto;
import example.battleships.model.dtos.ShipDto;
import example.battleships.model.entity.Ship;
import example.battleships.model.entity.User;
import example.battleships.repository.UserRepository;
import example.battleships.repository.ShipRepository;
import example.battleships.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShipService {

    private final ShipRepository shipRepository;
    private final UserRepository userRepository;
    public List<ShipDto> getOwnedShips;
    private LoggedUser loggedUser;
    private CategoryService categoryService;



    private ModelMapper modelMapper;


    public ShipService(ShipRepository shipRepository, UserRepository userRepository, LoggedUser loggedUser, CategoryService categoryService, ModelMapper modelMapper) {
        this.shipRepository = shipRepository;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;

    }


    public boolean create(AddShipDto addShipDto) {
        Optional<Ship> byName = this.shipRepository.findByName(addShipDto.getName());
        Optional<User> user = this.userRepository.findById(loggedUser.getId());
        if(byName.isPresent()){
            return false;
        }
        Ship ship = modelMapper.map(addShipDto, Ship.class);
        ship.setCreated(addShipDto.getCreated());
        ship.setHealth(addShipDto.getHealth());
        ship.setPower(addShipDto.getPower());
        ship.setUser(user.get());
        ship.setName(addShipDto.getName());
        ship.setCategory(this.categoryService.findByName(addShipDto.getCategory()));

        this.shipRepository.save(ship);
        return true;
    }

    public List<ShipDto> getOwnedShips(Long ownerId){
        return this.shipRepository.findByUserId(ownerId)
                .stream()
                .map(ship -> new ShipDto(ship))
                .collect(Collectors.toList());
    }

    public List<ShipDto> getNotOwnedShips(Long ownerId){
        return this.shipRepository.findByUserIdNot(ownerId)
                .stream()
                .map(ship -> new ShipDto(ship))
                .collect(Collectors.toList());
    }

    public List<ShipDto> getAllSorted() {
        return this.shipRepository.findByOrderByHealth()
                .stream()
                .map(ShipDto::new)
                .collect(Collectors.toList());
    }

public   List<ShipDto> findOrderByName(){
    return this.shipRepository.findByOrderByHealth()
            .stream()
            .map(ShipDto::new)
            .collect(Collectors.toList());
}
}
