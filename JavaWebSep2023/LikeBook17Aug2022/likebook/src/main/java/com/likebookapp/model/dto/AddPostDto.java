package com.likebookapp.model.dto;

import com.likebookapp.model.entity.MoodEnum;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddPostDto {

    @NotNull
    @Size(min= 2, max = 50, message = "Content length must be between 2 and 50 characters!")
    private String content;

    @NotNull(message = "You must select a mood!")
    private MoodEnum name;

    public AddPostDto() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MoodEnum getName() {
        return name;
    }

    public void setName(MoodEnum name) {
        this.name = name;
    }
}
