package com.softuni.spotifyplaylist.service;

import com.softuni.spotifyplaylist.model.dto.PlaylistDto;
import com.softuni.spotifyplaylist.model.dto.SongDto;
import com.softuni.spotifyplaylist.model.dto.UserLoginDto;
import com.softuni.spotifyplaylist.model.dto.UserRegistrationDto;
import com.softuni.spotifyplaylist.model.entity.Song;
import com.softuni.spotifyplaylist.model.entity.User;
import com.softuni.spotifyplaylist.repository.UserRepository;
import com.softuni.spotifyplaylist.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    @Autowired
    public UserService(UserRepository userRepository, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    public boolean register(UserRegistrationDto userRegistrationDto) {
        if(!userRegistrationDto.getPassword().equals(userRegistrationDto.getConfirmPassword())){
            return false;
        }
        Optional<User> byEmail = this.userRepository.findByEmail(userRegistrationDto.getEmail());
        if(byEmail.isPresent()){
            return  false;
        }
        Optional<User> byUsername = this.userRepository.findByUsername(userRegistrationDto.getUsername());
        if(byUsername.isPresent()){
            return  false;
        }
        User user = new User();
        user.setUsername(userRegistrationDto.getUsername());
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(userRegistrationDto.getPassword());
        this.userRepository.save(user);
        return true;
    }

    public boolean login(UserLoginDto userLoginDto) {
        Optional<User> user = this.userRepository.findByUsernameAndPassword(userLoginDto.getUsername(),
                userLoginDto.getPassword());
        if(!user.isPresent()){
            return false;
        }
        loggedUser.setId(user.get().getId());
        loggedUser.setUsername(user.get().getUsername());

        return true;
    }

    public void addSongToUser(Long userId, Song song) {
        User user = this.userRepository.findById(userId).orElseThrow();
        if (user.getPlaylist().stream().noneMatch(s -> s.getId().equals(song.getId()))) {
            user.addSongToPlaylist(song);
            this.userRepository.save(user);
        }
    }

    public void removeSongFromUser(Long userId, Long songId){
        User user = userRepository.findById(userId).orElseThrow();
        if (user.getPlaylist().stream().noneMatch(s -> s.getId().equals(songId))) {
            user.removeSongFromPlaylist(songId);
            this.userRepository.save(user);
        }
    }
    public void deleteAllSongs(Long id) {
        User user = this.userRepository.findById(loggedUser.getId()).orElseThrow();
        user.deleteAllSongFromPlaylist();
        this.userRepository.save(user);

    }
}
