package com.likebookapp.controller;

import com.likebookapp.service.PostService;

import com.likebookapp.service.UserService;
import com.likebookapp.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@Controller
public class HomeController {

    private final PostService postService;
    private final LoggedUser loggedUser;

    private final UserService userService;


    @Autowired
    public HomeController(PostService postService, LoggedUser loggedUser, UserService userService) {
        this.postService = postService;
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
        if(!model.containsAttribute("username")){
            model.addAttribute("username", this.loggedUser.getUsername());
        }

        if (!model.containsAttribute("userContents")) {
            model.addAttribute("userContents"
                    , this.userService.getUserContents());
        }

        if (!model.containsAttribute("otherContents")) {
            model.addAttribute("otherContents"
                    , this.userService.getOtherUsersContents());
        }
        return "home";

    }

    @GetMapping("/add-like/{id}")
    public String addLike(@PathVariable("id") Long id){
        if (!loggedUser.isLogged()) {
            return "redirect:/login";
        }

        this.postService.addLike( id);
        return "redirect:/home";
    }

    @GetMapping("/remove-post/{id}")
    public String removePost(@PathVariable("id") Long id){
        if (loggedUser.getId() == null) {
            return "redirect:/login";
        }

        this.postService.removePost(  loggedUser.getId(), id);
        return "redirect:/home";
    }
//
//    @GetMapping("/delete-all-songs")
//    public String removeAllSongsFromPlaylist(){
//        if (loggedUser.getId() == null) {
//            return "redirect:/login";
//        }
//
//        this.userService.deleteAllSongs(  loggedUser.getId());
//        return "redirect:/home";
//    }



}
