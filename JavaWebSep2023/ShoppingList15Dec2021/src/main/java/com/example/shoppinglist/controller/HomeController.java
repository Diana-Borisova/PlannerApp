package com.example.shoppinglist.controller;

import com.example.shoppinglist.service.ProductService;
import com.example.shoppinglist.service.UserService;
import com.example.shoppinglist.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    private final ProductService productService;
    private final LoggedUser loggedUser;

    private final UserService userService;


    @Autowired
    public HomeController(ProductService productService, LoggedUser loggedUser, UserService userService) {
        this.productService = productService;

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

        if (!model.containsAttribute("totalSum")) {
            model.addAttribute("totalSum"
                    , this.productService.getTotalSum());
        }

        if (!model.containsAttribute("food")) {
            model.addAttribute("food"
                    , this.productService.getAllFoods());
        }

        if (!model.containsAttribute("drink")) {
            model.addAttribute("drink"
                    , this.productService.getAllDrinks());
        }
        if (!model.containsAttribute("household")) {
            model.addAttribute("household"
                    , this.productService.getAllHouseholds());
        }

        if (!model.containsAttribute("other")) {
            model.addAttribute("other"
                    , this.productService.getAllOthers());
        }
        return "home";

    }

//    @GetMapping("/add-like/{id}")
//    public String addLike(@PathVariable("id") Long id){
//        if (!loggedUser.isLogged()) {
//            return "redirect:/login";
//        }
//
//        this.postService.addLike( id);
//        return "redirect:/home";
//    }

    @GetMapping("/buy/{id}")
    public String removePost(@PathVariable("id") Long id) {
        if (loggedUser.getId() == null) {
            return "redirect:/login";
        }

        this.productService.buyProduct( id);
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
