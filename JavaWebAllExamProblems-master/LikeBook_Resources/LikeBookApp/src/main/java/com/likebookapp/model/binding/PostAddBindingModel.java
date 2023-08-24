package com.likebookapp.model.binding;

import com.likebookapp.model.entity.MoodEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostAddBindingModel {

    @NotNull
    @Size(min= 2, max= 150, message = "Content length must be between 2 and 150 characters!")
    private String content;


    @NotNull(message = "You must select a priority!")
    @Enumerated(EnumType.STRING)
    private MoodEnum mood;
}
