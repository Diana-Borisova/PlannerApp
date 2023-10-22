package com.example.likebook.model.dto;
import com.example.likebook.model.entity.MoodEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AddPostDto {
    @NotNull
    @Size(min= 2, max = 50, message = "Content length must be between 2 and 50 characters!")
    private String content;


    @NotNull(message = "You must select a mood!")
    @Enumerated(EnumType.STRING)
    private MoodEnum moodEnum;



    public AddPostDto() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MoodEnum getMoodEnum() {
        return moodEnum;
    }

    public void setMoodEnum(MoodEnum moodEnum) {
        this.moodEnum = moodEnum;
    }
}
