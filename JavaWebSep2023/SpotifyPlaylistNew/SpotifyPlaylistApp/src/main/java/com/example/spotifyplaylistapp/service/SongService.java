package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dto.AddSongDto;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.StyleEnum;
import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;

    private final StyleRepository styleRepository;

    @Autowired
    public SongService(SongRepository songRepository, UserRepository userRepository, LoggedUser loggedUser, StyleRepository styleRepository) {
        this.songRepository = songRepository;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.styleRepository = styleRepository;
    }

    public boolean create(AddSongDto addSongDto) {


        Style style = this.styleRepository.findByName(addSongDto.getStyleEnum());
        Song song = new Song();
       song.setPerformer(addSongDto.getPerformer());
       song.setDuration(addSongDto.getDuration());
       song.setStyle(style);
       song.setTitle(addSongDto.getTitle());
       song.setReleaseDate(addSongDto.getReleaseDate());

        this.songRepository.save(song);


        return true;
    }

    public Set<Song> getPopSongs() {
        return this.songRepository.findByStyleName(StyleEnum.POP);
    }

    public Set<Song> getRockSongs() {
        return this.songRepository.findByStyleName(StyleEnum.ROCK);
    }

    public Set<Song> getJazzSongs() {
        return this.songRepository.findByStyleName(StyleEnum.JAZZ);
    }

    public void addSongToPlayList(Long songId) {
        User user =this.userRepository.findById(loggedUser.getId()).orElseThrow();
        Song song = this.songRepository.findById(songId).orElseThrow();
        user.getPlaylist().add(song);
        this.songRepository.save(song);
        this.userRepository.save(user);
    }

    public Set<Song> getMyPlaylist() {
        User user = this.userRepository.findById(loggedUser.getId()).orElseThrow();
        return user.getPlaylist();

    }

    public void removeSong() {
        User user = this.userRepository.findById(loggedUser.getId()).orElseThrow();

        user.getPlaylist().clear();
        this.userRepository.save(user);
    }
}
