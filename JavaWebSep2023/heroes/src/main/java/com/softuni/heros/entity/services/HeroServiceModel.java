package com.softuni.heros.entity.services;

import com.softuni.heros.entity.ClassEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HeroServiceModel {
    private String id;

    private String name;

    private ClassEnum classEnum;


    private Integer level;
}
