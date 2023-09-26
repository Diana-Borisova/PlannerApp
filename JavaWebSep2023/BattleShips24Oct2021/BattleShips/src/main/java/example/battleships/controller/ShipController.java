package example.battleships.controller;

import example.battleships.model.dtos.AddShipDto;
import example.battleships.service.ShipService;
import example.battleships.session.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping()
public class ShipController {

    private final ShipService shipService;
    private final LoggedUser loggedUser;
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
    public String addShip(@Valid @ModelAttribute(name = "addShipDto") AddShipDto addShipDto,
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

    @ModelAttribute(name = "addShipDto")
    public AddShipDto addShipDto(){
        return new AddShipDto();
    }
}
