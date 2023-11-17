package com.example.likebook.controller;




import com.example.likebook.servce.PostService;
import com.example.likebook.servce.UserService;
import com.example.likebook.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {


    private final LoggedUser loggedUser;
    private final PostService postService;
//    private final UserService userService;


    @Autowired
    public HomeController(LoggedUser loggedUser, PostService postService) {


        this.loggedUser = loggedUser;
        this.postService = postService;
//        this.userService = userService;
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

        if (!model.containsAttribute("myPost")) {
            model.addAttribute("myPost"
                    , this.postService.getMyPosts());
        }
        if (!model.containsAttribute("otherPosts")) {

            model.addAttribute("otherPosts"
                    , this.postService.getOtherPosts());

        }

        return "home";

    }

    @GetMapping("/add-like/{id}")
    public String addLike(@PathVariable("id") Long id) {
        if (!loggedUser.isLogged()) {
            return "redirect:/login";
        }

        this.postService.addLike(id);
        return "redirect:/home";

    }

    @GetMapping("/delete/{id}")
    public String removePost(@PathVariable Long id) {
        this.postService.removePost(id);
        return "redirect:/home";
    }
}
