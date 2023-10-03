package com.softuni.spotifyplaylist.model.dto;

import com.softuni.spotifyplaylist.model.entity.Song;

import java.util.Set;

public class PlaylistDto {

    private Set<SongDto> playlist;

    public PlaylistDto() {

    }

    public Set<SongDto> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Set<SongDto> playlist) {
        this.playlist = playlist;
    }
}
