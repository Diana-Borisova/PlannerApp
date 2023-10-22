package com.example.spotifyplaylistapp.controller;



import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.service.UserService;
import com.example.spotifyplaylistapp.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.Duration;
import java.util.Set;

@Controller
public class HomeController {


    private final LoggedUser loggedUser;
    private final SongService songService;
    private final UserService userService;


    @Autowired
    public HomeController(LoggedUser loggedUser, UserService userService, SongService songService) {

        this.loggedUser = loggedUser;
        this.songService = songService;

        this.userService = userService;

    }


    @GetMapping("/")
    public String index() {


        return "index";

    }

    @GetMapping("/home")
    public String home(Model model) {

        if (loggedUser.getId() == null) {
            return "redirect:/";
        }
        Long id = this.loggedUser.getId();

        if (!model.containsAttribute("popPlaylist")) {
            model.addAttribute("popPlaylist"
                    , this.songService.getPopSongs());
        }

        if (!model.containsAttribute("rockPlaylist")) {
            model.addAttribute("rockPlaylist"
                    , this.songService.getRockSongs());
        }
        if (!model.containsAttribute("jazzPlaylist")) {
            model.addAttribute("jazzPlaylist"
                    , this.songService.getJazzSongs());
        }
        if (!model.containsAttribute("myPlaylist")) {

            model.addAttribute("myPlaylist"
                    , this.songService.getMyPlaylist());

        }
        int duration = this.songService.getMyPlaylist()
                .stream()
                .mapToInt(Song::getDuration)  // Assuming getDuration returns an int
                .sum();
        if (!model.containsAttribute("duration")) {

            model.addAttribute("duration"
                    , duration);

        }
        return "home";

    }

    @GetMapping("/add-song/{id}")
    public String addLike(@PathVariable("id") Long id) {
        if (!loggedUser.isLogged()) {
            return "redirect:/login";
        }

        this.songService.addSongToPlayList(id);
        return "redirect:/home";

    }

    @GetMapping("/delete")
    public String removePlaylist() {
        this.songService.removeSong();
        return "redirect:/home";
    }
}
