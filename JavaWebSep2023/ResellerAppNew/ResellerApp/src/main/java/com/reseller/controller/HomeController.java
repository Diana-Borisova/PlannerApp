package com.reseller.controller;


import com.reseller.service.OfferService;
import com.reseller.service.UserService;
import com.reseller.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

private final OfferService offerService;
    private final LoggedUser loggedUser;

    private final UserService userService;


    @Autowired
    public HomeController(OfferService offerService, LoggedUser loggedUser, UserService userService) {
        this.offerService = offerService;

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

        if (!model.containsAttribute("getAllOtherOffers")) {
            model.addAttribute("getAllOtherOffers"
                    , this.offerService.getAllOtherOffers());
        }

        if (!model.containsAttribute("myOffers")) {
            model.addAttribute("myOffers"
                    , this.offerService.getAllMyOffers());
        }
        if (!model.containsAttribute("boughtOffers")) {
            model.addAttribute("boughtOffers"
                    , this.offerService.getBoughtOffers());
        }

        return "home";

    }

    @GetMapping("/remove/{id}")
    public String removePost(@PathVariable("id") Long id){
        if (loggedUser.getId() == null) {
            return "redirect:/login";
        }

        this.offerService.removeOffer( id);
        return "redirect:/home";
    }

    @GetMapping("/buy-offer/{id}")
    public String buyOffer(@PathVariable("id") Long id){
        if (!loggedUser.isLogged()) {
            return "redirect:/login";
        }

        this.offerService.buyOffer( id);
        return "redirect:/home";
    }
    }
