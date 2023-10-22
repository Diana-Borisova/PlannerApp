package com.example.andrey.controller;

import com.example.andrey.model.dto.AddItemDto;
import com.example.andrey.service.ItemService;
import com.example.andrey.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class ItemController {
    private final ItemService itemService;
    private final LoggedUser loggedUser;

    @Autowired
    public ItemController(ItemService itemService, LoggedUser loggedUser) {
        this.itemService = itemService;

        this.loggedUser = loggedUser;
    }


    @GetMapping("/items/add")
    public String addPost(){
        if(loggedUser.getId() == null){
            return "redirect:/";
        }
        return "/add-product";
    }

    @PostMapping("/items/add")
    public String addSong(@Valid @ModelAttribute(name = "addItemDto") AddItemDto addItemDto,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors() || !itemService.create(addItemDto)) {

            redirectAttributes.addFlashAttribute("addItemDto", addItemDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addItemDto",
                    bindingResult);
            return "redirect:/items/add";
        }

        if(loggedUser.getId() == null){
            return "redirect:/";
        }

        return "redirect:/home";
    }


    @ModelAttribute(name = "addItemDto")
    public AddItemDto addItemDto(){
        return new AddItemDto();
    }


}
