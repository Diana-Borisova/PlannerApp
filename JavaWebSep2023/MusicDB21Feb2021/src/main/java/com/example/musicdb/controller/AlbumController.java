package com.example.musicdb.controller;

import com.example.musicdb.model.dto.AddAlbumDto;
import com.example.musicdb.service.AlbumService;
import com.example.musicdb.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class AlbumController {
    private final AlbumService albumService;
    private final LoggedUser loggedUser;

    @Autowired
    public AlbumController( AlbumService albumService, LoggedUser loggedUser) {
        this.albumService = albumService;
        this.loggedUser = loggedUser;
    }


    @GetMapping("/albums/add")
    public String addPost(){
        if(loggedUser.getId() == null){
            return "redirect:/";
        }
        return "/add-album";
    }

    @PostMapping("/albums/add")
    public String addSong(@Valid @ModelAttribute(name = "addAlbumDto")AddAlbumDto addAlbumDto,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors() || !albumService.create(addAlbumDto)) {

            redirectAttributes.addFlashAttribute("addAlbumDto", addAlbumDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addAlbumDto",
                    bindingResult);
            return "redirect:/albums/add";
        }

        if(loggedUser.getId() == null){
            return "redirect:/";
        }

        return "redirect:/home";
    }

    @ModelAttribute(name = "addAlbumDto")
    public AddAlbumDto addAlbumDto(){
        return new AddAlbumDto();
    }


}
