package com.dictionaryapp.controller;

import com.dictionaryapp.service.UserService;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    private final LoggedUser loggedUser;
    private final UserService userService;
    private final WordService wordService;

    @Autowired
    public HomeController(LoggedUser loggedUser, UserService userService, WordService wordService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.wordService = wordService;
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

        if (!model.containsAttribute("allWords")) {
            model.addAttribute("allWords"
                    , this.wordService.getAllWords());
        }
        if (!model.containsAttribute("germanWords")) {

            model.addAttribute("germanWords"
                    , this.wordService.getGermanWords());

        }
        if (!model.containsAttribute("frenchWords")) {

            model.addAttribute("frenchWords"
                    , this.wordService.getFrenchWords());

        }
        if (!model.containsAttribute("italianWords")) {

            model.addAttribute("italianWords"
                    , this.wordService.getItalianWords());

        }
        if (!model.containsAttribute("spanishWords")) {

            model.addAttribute("spanishWords"
                    , this.wordService.getSpanishWords());

        }

        return "home";

    }

    @GetMapping("/remove/{id}")
    public String removeWord(@PathVariable("id") Long id){
        if (loggedUser.getId() == null) {
            return "redirect:/login";
        }

        this.wordService.removeWord(id);
        return "redirect:/home";
    }


    @GetMapping("/delete-all")
    public String removeAllWords() {
        if (loggedUser.getId() == null) {
            return "redirect:/login";
        }
        this.wordService.removeAllWords();
        return "redirect:/home";
    }
}
