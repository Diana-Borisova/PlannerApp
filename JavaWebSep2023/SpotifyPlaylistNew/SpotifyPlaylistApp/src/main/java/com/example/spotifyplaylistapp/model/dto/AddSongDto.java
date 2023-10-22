package com.example.spotifyplaylistapp.model.dto;


import com.example.spotifyplaylistapp.model.entity.StyleEnum;
import jakarta.validation.constraints.*;



import java.time.LocalDate;

public class AddSongDto {
    @NotNull(message = "Performer name length must be between 3 and 20 characters(inclusive 3 and 20).")
    @Size(min= 3, max = 50, message = "Performer name length must be between 3 and 20 characters(inclusive 3 and 20).")
    private String performer;

    @NotNull( message = "Title name length must be between 2 and 20 characters(inclusive 2 and 20).")
    @Size(min= 2, max = 50, message = "Title name length must be between 2 and 20 characters(inclusive 2 and 20).")
    private String title;

    @NotNull(message = "Duration must be positive!")
    @Positive(message = "Duration must be positive!")
    private int duration;


    @PastOrPresent
    private LocalDate releaseDate;

    @NotNull(message = "You must select a style!")
    private StyleEnum styleEnum;

    public AddSongDto() {
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public StyleEnum getStyleEnum() {
        return styleEnum;
    }

    public void setStyleEnum(StyleEnum styleEnum) {
        this.styleEnum = styleEnum;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
