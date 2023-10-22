package com.example.spotifyplaylistapp.controller;
import com.example.spotifyplaylistapp.model.dto.AddSongDto;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;



@Controller
public class SongController {
    private final SongService songService;
    private final LoggedUser loggedUser;

    @Autowired
    public SongController(SongService songService, LoggedUser loggedUser) {
        this.songService = songService;
        this.loggedUser = loggedUser;
    }


    @GetMapping("/songs/add")
    public String addSong(){
        if(loggedUser.getId() == null){
            return "redirect:/";
        }
        return "/song-add";
    }

    @PostMapping("/songs/add")
    public String addSong(@Valid @ModelAttribute(name = "addSongDto") AddSongDto addSongDto,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors() || !songService.create(addSongDto)) {

            redirectAttributes.addFlashAttribute("addSongDto", addSongDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addSongDto",
                    bindingResult);
            return "redirect:/songs/add";
        }

        if(loggedUser.getId() == null){
            return "redirect:/";
        }

        return "redirect:/home";
    }

    @ModelAttribute(name = "addSongDto")
    public AddSongDto addSongDto(){
        return new AddSongDto();
    }


}
