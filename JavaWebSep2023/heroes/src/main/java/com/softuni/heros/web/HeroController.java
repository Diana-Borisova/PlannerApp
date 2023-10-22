package com.softuni.heros.web;


import com.softuni.heros.entity.Hero;
import com.softuni.heros.entity.binding.HeroCreateBindingModel;
import com.softuni.heros.entity.services.HeroServiceModel;
import com.softuni.heros.repository.HeroRepository;
import com.softuni.heros.service.HeroService;
import com.softuni.heros.utils.LoggedUser;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/heroes")
public class HeroController {
    private final LoggedUser loggedUser;
    private final HeroService heroService;
    private final ModelMapper modelMapper;
    private final HeroRepository heroRepository;

    public HeroController(LoggedUser loggedUser, HeroService heroService, ModelMapper modelMapper, HeroRepository heroRepository) {
        this.loggedUser = loggedUser;
        this.heroService = heroService;
        this.modelMapper = modelMapper;
        this.heroRepository = heroRepository;
    }


    @GetMapping("/create")
    public String add(Model model) {
if (!model.containsAttribute("heroCreateBindingModel")){
    model.addAttribute("heroCreateBindingModel", new HeroCreateBindingModel());
}
        return "create-hero";
    }
    @PostMapping("/create")
    public String addConfirm(@Valid HeroCreateBindingModel heroCreateBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("heroCreateBindingModel", heroCreateBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.heroCreateBindingModel",
                    bindingResult);
            return "redirect:create";
        }
        heroService.createHero(modelMapper.map(heroCreateBindingModel, HeroServiceModel.class));
        return "redirect:/home";
    }
    @GetMapping("/details/{id}")
    public ModelAndView details(@PathVariable String id , ModelAndView model) {

        Hero hero = this.heroRepository.findById(id).orElseThrow();

        model.setViewName("details-hero");
        model.addObject("hero", hero);
        return model;
    }

    @GetMapping("/deletePage/{id}")
    public ModelAndView deletePage(@PathVariable String  id, ModelAndView model){

        Hero hero = this.heroRepository.findById(id).orElseThrow();

        model.setViewName("delete-hero");
        model.addObject("heroDelete" , hero);
        return model;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id){

        this.heroService.deleteHeroById(id);

        return "redirect:/home";
    }
    @ModelAttribute
    public HeroCreateBindingModel heroCreateBindingModel(){
        return new HeroCreateBindingModel();
    }


}