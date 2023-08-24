package com.likebookapp.controller;


import com.likebookapp.service.PostService;
import com.likebookapp.service.UserService;
import com.likebookapp.util.LoggedUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    private final LoggedUser loggedUser;
    private final PostService postService;
    private  final UserService userService;

    public HomeController(LoggedUser loggedUser, PostService postService, UserService userService) {
        this.loggedUser = loggedUser;
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping()
    public String index(HttpSession httpSession, Model model){
        if (httpSession.getAttribute("user") == null){
            return "index";
        }

        model.addAttribute("myPosts",postService.getMyPosts());

        return "home";
    }


}
