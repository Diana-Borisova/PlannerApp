package com.softuni.heros.web;

import com.softuni.heros.entity.Hero;
import com.softuni.heros.repository.HeroRepository;
import com.softuni.heros.service.HeroService;
import com.softuni.heros.utils.LoggedUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final LoggedUser loggedUser;
    private final HeroService heroService;
    private final HeroRepository heroRepository;

    public HomeController(LoggedUser loggedUser, HeroService heroService, HeroRepository heroRepository) {
        this.loggedUser = loggedUser;

        this.heroService = heroService;
        this.heroRepository = heroRepository;
    }



   /* @GetMapping("/home")
    public String index(Model model){
        if(loggedUser.getId() == null){
            return "index";
        }
        List<HeroServiceModel> heroes = heroService.findAll();
        model.addAttribute("heroes", heroes);
        model.addAttribute("heroesCount" , heroes.size());
        return "home";
    }*/

     @GetMapping("/home")
    public String index(Model model) {

        if (loggedUser.getId() == null) {
            return "index";
        }
        List<Hero> heroes = this.heroRepository.findAllByOrderByLevelDesc();

        model.addAttribute("heroes" , heroes);
        model.addAttribute("heroName", loggedUser.getUsername());

        return "home";
    }





}
