package com.softuni.spotifyplaylist.service;

import com.softuni.spotifyplaylist.model.dto.AddSongDto;
import com.softuni.spotifyplaylist.model.dto.SongDto;
import com.softuni.spotifyplaylist.model.entity.Song;
import com.softuni.spotifyplaylist.model.entity.Style;
import com.softuni.spotifyplaylist.model.entity.User;
import com.softuni.spotifyplaylist.repository.SongRepository;
import com.softuni.spotifyplaylist.repository.UserRepository;
import com.softuni.spotifyplaylist.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final StyleService styleService;
    private final UserRepository userRepository;
    private final UserService userService;
    private final LoggedUser loggedUser;

    @Autowired
    public SongService(SongRepository songRepository, StyleService styleService, UserRepository userRepository, UserService userService, LoggedUser loggedUser) {
        this.songRepository = songRepository;
        this.styleService = styleService;
        this.userRepository = userRepository;
        this.userService = userService;
        this.loggedUser = loggedUser;
    }

    public boolean create(AddSongDto addSongDto) {

        Song song = new Song();
        song.setDuration(addSongDto.getDuration());
        song.setPerformer(addSongDto.getPerformer());
        song.setStyle(this.styleService.findByName(addSongDto.getStyle()));
        song.setReleaseDate(addSongDto.getReleaseDate());
        song.setTitle(addSongDto.getTitle());
        song.setId(addSongDto.getId());
        this.songRepository.save(song);
        return true;
    }


    public Set<SongDto> findSongsByStyle(Style style) {
        return this.songRepository.findByStyle(style).stream()
                .map(song -> new SongDto(song))
                .collect(Collectors.toSet());
    }

    public Set<SongDto> getPlaylist(Long id){
        return this.songRepository
                .findAllByUserId(loggedUser.getId())
                .stream()
                .map(song -> new SongDto(song))
                .collect(Collectors.toSet());
    }
    public void songAdd(Long songId, Long userId) {
        Song song = this.songRepository.findSongById(songId);
        User user = this.userRepository.findById(userId).orElseThrow();
        this.userService.addSongToUser( userId, song);
        this.userRepository.save(user);
    }

    public void songRemove( Long userId, Long songId) {
        Song song = this.songRepository.findSongById(songId);
        User user = this.userRepository.findById(userId).orElseThrow();
        user.removeSongFromPlaylist(songId);
       // this.userService.removeSongFromUser( userId, songId);
        this.userRepository.save(user);
    }


}
