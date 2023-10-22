package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.AddWordDto;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class WordController {
    private final WordService wordService;
    private final LoggedUser loggedUser;

    @Autowired
    public WordController(WordService wordService, LoggedUser loggedUser) {
        this.wordService = wordService;
        this.loggedUser = loggedUser;
    }


    @GetMapping("/words/add")
    public String addWord(){
        if(loggedUser.getId() == null){
            return "redirect:/";
        }
        return "/word-add";
    }

    @PostMapping("/words/add")
    public String addWord(@Valid @ModelAttribute(name = "addWordDto")AddWordDto addWordDto,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors() || !wordService.create(addWordDto)) {

            redirectAttributes.addFlashAttribute("addWordDto", addWordDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addWordDto",
                    bindingResult);
            return "redirect:/words/add";
        }

        if(loggedUser.getId() == null){
            return "redirect:/";
        }

        return "redirect:/home";
    }

    @ModelAttribute(name = "addWordDto")
    public AddWordDto addWordDto(){
        return new AddWordDto();
    }


}
