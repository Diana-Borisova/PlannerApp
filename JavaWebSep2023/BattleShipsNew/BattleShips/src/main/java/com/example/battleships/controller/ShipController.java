package com.example.battleships.controller;


import com.example.battleships.model.dto.AddShipDto;
import com.example.battleships.model.dto.BattleDto;
import com.example.battleships.service.ShipService;
import com.example.battleships.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;




@Controller
public class ShipController {
    private final ShipService shipService;
    private final LoggedUser loggedUser;

    @Autowired
    public ShipController(ShipService shipService, LoggedUser loggedUser) {
        this.shipService = shipService;
        this.loggedUser = loggedUser;
    }


    @GetMapping("/ships/add")
    public String addShip(){
        if(loggedUser.getId() == null){
            return "redirect:/";
        }
        return "/ship-add";
    }

    @PostMapping("/ships/add")
    public String addShip(@Valid @ModelAttribute(name = "addShipDto")AddShipDto addShipDto,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors() || !shipService.create(addShipDto)) {

            redirectAttributes.addFlashAttribute("addShipDto", addShipDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addShipDto",
                    bindingResult);
            return "redirect:/ships/add";
        }

        if(loggedUser.getId() == null){
            return "redirect:/";
        }

        return "redirect:/home";
    }

    @PostMapping("/battle")
    public String addShip(@Valid @ModelAttribute(name = "battleDto") BattleDto battleDto,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) throws Exception {
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("battleDto", battleDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.battleDto",
                    bindingResult);
            return "redirect:/home";
        }

        if (loggedUser.getId() == null) {
            return "redirect:/";
        }
        this.shipService.attack(battleDto);
        return "redirect:/home";
    }


    @ModelAttribute(name = "addShipDto")
    public AddShipDto addShipDto(){
        return new AddShipDto();
    }


}
