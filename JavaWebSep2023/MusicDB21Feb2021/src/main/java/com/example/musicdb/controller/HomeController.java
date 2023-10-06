package com.example.musicdb.controller;


import com.example.musicdb.model.dto.AddAlbumDto;
import com.example.musicdb.service.AlbumService;
import com.example.musicdb.service.UserService;
import com.example.musicdb.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    private final AlbumService albumService;
    private final LoggedUser loggedUser;

    private final UserService userService;


    @Autowired
    public HomeController(AlbumService albumService, LoggedUser loggedUser, UserService userService) {
        this.albumService = albumService;

        this.loggedUser = loggedUser;
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

        if (!model.containsAttribute("totalCopies")) {
            model.addAttribute("totalCopies"
                    , this.albumService.getCountOfAllCopies());
        }

        if (!model.containsAttribute("allAlbums")) {
            model.addAttribute("allAlbums"
                    , this.albumService.getAllAlbums());
        }

        return "home";

    }

    @GetMapping("/delete/{id}")
    public String removeAlbum(@PathVariable("id") Long id) {
        if (loggedUser.getId() == null) {
            return "redirect:/login";
        }

       this.albumService.removeAlbum( id);
        return "redirect:/home";
    }

        @ModelAttribute(name = "addAlbumDto")
        public AddAlbumDto addAlbumDto(){
            return new AddAlbumDto();
        }

}
