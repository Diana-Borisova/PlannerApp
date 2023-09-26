package example.battleships.model.dtos;

import example.battleships.model.entity.Ship;

public class ShipDto {
    private Long id;
    private String name;
    private Long health;
    private Long power;

    public ShipDto(Ship ship) {
        this.id = ship.getId();
        this.name = ship.getName();
        this.health = ship.getHealth();
        this.power = ship.getPower();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getHealth() {
        return health;
    }

    public Long getPower() {
        return power;
    }
}
