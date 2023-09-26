package example.battleships.model.dtos;

import example.battleships.model.entity.ShipType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class AddShipDto {
    @Size(min= 2, max = 10)
    @NotNull
    private String name;

    @Positive
    @NotNull
    private Long health;

    @Positive
    @NotNull
    private Long power;

    @PastOrPresent
    @NotNull
    private LocalDate created;



    @NotNull
    private ShipType category;

    public ShipType getCategory() {
        return category;
    }

    public AddShipDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getHealth() {
        return health;
    }

    public void setHealth(Long health) {
        this.health = health;
    }

    public Long getPower() {
        return power;
    }

    public void setPower(Long power) {
        this.power = power;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public void setCategory(ShipType category) {
        this.category = category;
    }

}
