package com.softuni.spotifyplaylist.model.dto;

import java.util.Set;

public class SongsByGenreDto {

    private Set<SongDto> popSongs;
    private Set<SongDto> rockSongs;
    private Set<SongDto> jazzSongs;

    public SongsByGenreDto() {
    }

    public Set<SongDto> getPopSongs() {
        return popSongs;
    }

    public void setPopSongs(Set<SongDto> popSongs) {
        this.popSongs = popSongs;
    }

    public Set<SongDto> getRockSongs() {
        return rockSongs;
    }

    public void setRockSongs(Set<SongDto> rockSongs) {
        this.rockSongs = rockSongs;
    }

    public Set<SongDto> getJazzSongs() {
        return jazzSongs;
    }

    public void setJazzSongs(Set<SongDto> jazzSongs) {
        this.jazzSongs = jazzSongs;
    }
}
