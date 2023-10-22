package com.example.battleships.controller;


import com.example.battleships.model.dto.AddShipDto;
import com.example.battleships.model.dto.BattleDto;
import com.example.battleships.service.ShipService;
import com.example.battleships.service.UserService;
import com.example.battleships.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {


    private final LoggedUser loggedUser;
    private final ShipService shipService;
    private final UserService userService;


    @Autowired
    public HomeController(LoggedUser loggedUser, ShipService shipService, UserService userService) {

        this.loggedUser = loggedUser;
        this.shipService = shipService;
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

        if (!model.containsAttribute("ownShips")) {
            model.addAttribute("ownShips"
                    , this.shipService.getOwnShips());
        }
        if (!model.containsAttribute("otherShips")) {
            model.addAttribute("otherShips"
                    , this.shipService.getOtherShips());
        }
        if (!model.containsAttribute("sortedShips")) {
            model.addAttribute("sortedShips"
                    , this.shipService.getSortedShips());
        }
//
//        if (!model.containsAttribute("otherList")) {
//            model.addAttribute("otherList"
//                    , this.productService.getOthers());
//        }
//
//
//
//        Double totalPriceOfProducts = this.productService.getAllProducts()
//                .stream()
//                .mapToDouble(Product::getPrice)  // Assuming getDuration returns an int
//                .sum();
//        if (!model.containsAttribute("totalPriceOfProducts")) {
//
//            model.addAttribute("totalPriceOfProducts"
//                    ,String.format("%.2f", totalPriceOfProducts));
//
//        }
        return "home";

    }

    @ModelAttribute(name = "battleDto")
    public BattleDto battleDto(){

        return new BattleDto();
    }

}