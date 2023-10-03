package com.softuni.spotifyplaylist.controller;

import com.softuni.spotifyplaylist.model.dto.SongDto;
import com.softuni.spotifyplaylist.model.dto.SongsByGenreDto;
import com.softuni.spotifyplaylist.model.entity.Style;
import com.softuni.spotifyplaylist.model.entity.StyleEnum;
import com.softuni.spotifyplaylist.service.SongService;
import com.softuni.spotifyplaylist.service.StyleService;
import com.softuni.spotifyplaylist.service.UserService;
import com.softuni.spotifyplaylist.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@Controller
public class HomeController {

    private final SongService songService;
    private final StyleService styleService;
    private final LoggedUser loggedUser;

    private final UserService userService;


    @Autowired
    public HomeController(SongService songService, StyleService styleService, LoggedUser loggedUser, UserService userService) {
        this.songService = songService;
        this.styleService = styleService;
        this.loggedUser = loggedUser;
        this.userService = userService;
    }


    @GetMapping("/")
    public String index(){


        return "index";

    }

    @GetMapping("/home")
    public String home(Model model){

        if(loggedUser.getId() == null){
            return "redirect:/";
        }
        Long id = this.loggedUser.getId();
        Set<SongDto> allSongsInPlaylist = this.songService.getPlaylist(loggedUser.getId());
        model.addAttribute("songs", this.getSongs());
        model.addAttribute("playlist", this.songService.getPlaylist(loggedUser.getId()));
        model.addAttribute("totalDuration", allSongsInPlaylist.stream().mapToInt(SongDto::getDuration).sum());
        return "home";

    }

    @GetMapping("/add-song-to-playlist/{id}")
    public String addSongToPlaylist(@PathVariable("id") Long id){
        if (!loggedUser.isLogged()) {
            return "redirect:/login";
        }

        this.songService.songAdd( id, loggedUser.getId());
        return "redirect:/home";
    }

    @GetMapping("/remove-song-from-playlist/{id}")
    public String removeSongFromPlaylist(@PathVariable("id") Long id){
        if (loggedUser.getId() == null) {
            return "redirect:/login";
        }

        this.songService.songRemove(  loggedUser.getId(), id);
        return "redirect:/home";
    }

    @GetMapping("/delete-all-songs")
    public String removeAllSongsFromPlaylist(){
        if (loggedUser.getId() == null) {
            return "redirect:/login";
        }

        this.userService.deleteAllSongs(  loggedUser.getId());
        return "redirect:/home";
    }

    public SongsByGenreDto getSongs() {
        SongsByGenreDto songs = new SongsByGenreDto();
        songs.setJazzSongs(this.getSongsByGenre(this.styleService.findByName(StyleEnum.JAZZ)));
        songs.setRockSongs(this.getSongsByGenre(this.styleService.findByName(StyleEnum.ROCK)));
        songs.setPopSongs(this.getSongsByGenre(this.styleService.findByName(StyleEnum.POP)));
        return songs;
    }

    private Set<SongDto> getSongsByGenre(Style style) {
        return this.songService.findSongsByStyle(style);
    }

}
