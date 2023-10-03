package com.softuni.spotifyplaylist.model.dto;

import com.softuni.spotifyplaylist.model.entity.Style;
import com.softuni.spotifyplaylist.model.entity.StyleEnum;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class AddSongDto {

    private Long id;
    @NotNull
    @Size(min= 3, max = 20, message = "Performer name length must be between 3 and 20 characters (inclusive 3 and 20).")
    private String performer;
    @NotNull
    @Size(min= 2, max = 20, message = "Title length must be between 2 and 20 characters (inclusive 3 and 20).")
    private String title;

    @NotNull
    @Positive
    private int duration;

    @PastOrPresent
    private LocalDate releaseDate;

    @NotNull
    private StyleEnum style;

    public AddSongDto() {
    }
    @NotNull
    @Size(min= 3, max = 20, message = "Performer length must be between 3 and 20 characters!")
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public StyleEnum getStyle() {
        return style;
    }

    public void setStyle(StyleEnum style) {
        this.style = style;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}