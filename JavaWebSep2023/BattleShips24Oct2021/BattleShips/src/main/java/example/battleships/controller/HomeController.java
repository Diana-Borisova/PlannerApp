package example.battleships.controller;

import example.battleships.model.dtos.ShipDto;
import example.battleships.model.dtos.StartBattleDto;
import example.battleships.service.ShipService;
import example.battleships.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {

    private final ShipService shipService;
    private final LoggedUser loggedUser;

    @ModelAttribute("startBattleDto")
    public StartBattleDto startBattleDto(){
        return new StartBattleDto();
    }
    @Autowired
    public HomeController(ShipService shipService, LoggedUser loggedUser) {
        this.shipService = shipService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/")
    public String index(){


        return "index";

    }

    @GetMapping("/home")
    public String home(Model model){

        if(loggedUser.getId() == null){
            return "redirect:/";
        }
        Long id = this.loggedUser.getId();
        List<ShipDto> ownShips = this.shipService.getOwnedShips(id);
        List<ShipDto> enemyShips = this.shipService.getNotOwnedShips(id);
        List<ShipDto> sortedShips = this.shipService.getAllSorted();
        model.addAttribute("ownShips", ownShips);
        model.addAttribute("enemyShips", enemyShips);
        model.addAttribute("sortedShips", sortedShips);
        return "home";

    }




}
