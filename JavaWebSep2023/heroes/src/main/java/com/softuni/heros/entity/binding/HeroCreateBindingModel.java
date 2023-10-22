package com.softuni.heros.entity.binding;

import com.softuni.heros.entity.ClassEnum;
import jakarta.persistence.Column;
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
public class HeroCreateBindingModel {

    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ClassEnum classEnum;

    @NotNull
    private Integer level;
}
