package com.example.battleships.model.dto;

import jakarta.validation.constraints.Positive;

public class BattleDto {

    @Positive
    private int attackerId;
    @Positive
    private int defenderId;

    public int getAttackerId() {
        return attackerId;
    }

    public void setAttackerId(int attackerId) {
        this.attackerId = attackerId;
    }

    public int getDefenderId() {
        return defenderId;
    }

    public void setDefenderId(int defenderId) {
        this.defenderId = defenderId;
    }
}
