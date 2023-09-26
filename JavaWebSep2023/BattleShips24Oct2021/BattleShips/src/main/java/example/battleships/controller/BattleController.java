package example.battleships.controller;

import example.battleships.model.dtos.StartBattleDto;
import example.battleships.service.BattleService;
import example.battleships.session.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BattleController {

    private final LoggedUser loggedUser;
    private BattleService battleService;

    public BattleController(LoggedUser loggedUser, BattleService battleService) {
        this.loggedUser = loggedUser;
        this.battleService = battleService;
    }

    @PostMapping("/battle")
    public String battle(@Valid @ModelAttribute(name = "startBattleDto") StartBattleDto startBattleDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) throws Exception {

        if (loggedUser.getId() == null) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("startBattleDto", startBattleDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.startBattleDto", bindingResult);

            return "redirect:/home";
        }
         this.battleService.attack(startBattleDto);

        return "redirect:/home";
    }

}
