package com.dictionaryapp.model.dto;

import com.dictionaryapp.model.entity.LanguageEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class AddWordDto {
    @NotNull
    @Size(min= 2, max = 40, message = "The term length must be between 2 and 40 characters!")
    private String term;

    @NotNull
    @Size(min= 2, max = 80, message = "The translation length must be between 2 and 80 characters!")
    private String translation;

    @NotNull
    @Size(min= 2, max = 200, message = "The example length must be between 2 and 200 characters!")
    private String example;

    @NotNull(message = "The input date must be in the past or present!")
    @PastOrPresent(message = "The input date must be in the past or present!")
    private LocalDate inputDate;

    @NotNull(message = "You must select a language!")
    @Enumerated(EnumType.STRING)
    private LanguageEnum languageEnum;



    public AddWordDto() {
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public LocalDate getInputDate() {
        return inputDate;
    }

    public void setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
    }

    public LanguageEnum getLanguageEnum() {
        return languageEnum;
    }

    public void setLanguageEnum(LanguageEnum languageEnum) {
        this.languageEnum = languageEnum;
    }
}
