package com.reseller.controller;


import com.reseller.model.dto.AddOfferDto;
import com.reseller.service.OfferService;
import com.reseller.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
public class OfferController {
    private final OfferService offerService;
    private final LoggedUser loggedUser;

    @Autowired
    public OfferController(OfferService offerService, LoggedUser loggedUser) {
        this.offerService=offerService;
        this.loggedUser = loggedUser;
    }


    @GetMapping("/offers/add")
    public String addTask(){
        if(loggedUser.getId() == null){
            return "redirect:/";
        }
        return "/offer-add";
    }

    @PostMapping("/offers/add")
    public String addSong(@Valid @ModelAttribute(name = "addOfferDto")AddOfferDto addOfferDto,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors() || !offerService.create(addOfferDto)) {

            redirectAttributes.addFlashAttribute("addOfferDto", addOfferDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOfferDto",
                    bindingResult);
            return "redirect:/offers/add";
        }

        if(loggedUser.getId() == null){
            return "redirect:/";
        }

        return "redirect:/home";
    }

    @ModelAttribute(name = "addOfferDto")
    public AddOfferDto addOfferDto(){
        return new AddOfferDto();
    }


}
