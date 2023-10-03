package com.softuni.spotifyplaylist.model.dto;

import com.softuni.spotifyplaylist.model.entity.Song;
import com.softuni.spotifyplaylist.model.entity.StyleEnum;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class SongDto {

    private Long id;
    @NotNull
    @Size(min = 3, max = 20, message = "Performer name length must be between 3 and 20 characters (inclusive 3 and 20).")
    private String performer;
    @Column(nullable = false)
    @Size(min = 2, max = 20, message = "Title length must be between 2 and 20 characters (inclusive 3 and 20).")
    private String title;

    @NotNull
    @Positive
    private int duration;



    public SongDto(Song song) {
        this.id = song.getId();
        this.duration = song.getDuration();
        this.title = song.getTitle();
        this.performer = song.getPerformer();
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}