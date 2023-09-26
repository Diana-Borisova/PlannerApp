package com.coffeeshop.controller;

import com.coffeeshop.helper.LoggedUser;
import com.coffeeshop.model.dto.UserLoginDto;
import com.coffeeshop.model.dto.UserRegistrationDto;
import com.coffeeshop.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller()
public class UserController {

public  final UserService userService;
public  final LoggedUser loggedUser;
    public UserController(UserService userService, LoggedUser loggedUser) {
        this.userService = userService;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute(name = "userRegistrationDto")
    public UserRegistrationDto userRegistrationDto(){
        return new UserRegistrationDto();
    }

    @ModelAttribute(name = "userLoginDto")
    public UserLoginDto userLoginDto(){
        return new UserLoginDto();
    }


    @GetMapping("/register")
    public String register(){
        if(loggedUser.getId() != null){
            return "redirect:/home";
        }


        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid  @ModelAttribute(name = "userRegistrationDto")  UserRegistrationDto userRegistrationDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){
        if(loggedUser.getId() != null){
            return "redirect:/home";
        }
        if (bindingResult.hasErrors() || !this.userService.register(userRegistrationDto)) {

            redirectAttributes.addFlashAttribute("userRegistrationDto", userRegistrationDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDto",
                    bindingResult);
            return "redirect:/register";
        }
        return "/login";
    }

    @GetMapping("/login")
    public String login(){
        if(loggedUser.getId() != null){
            return "redirect:/home";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid  @ModelAttribute(name = "userLoginDto")  UserLoginDto userLoginDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){
        if(loggedUser.getId() != null){
            return "redirect:/home";
        }
        if (bindingResult.hasErrors() ) {

            redirectAttributes.addFlashAttribute("userLoginDto", userLoginDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginDto",
                    bindingResult);
            return "redirect:/login";
        }

        if( !this.userService.login(userLoginDto)){
            redirectAttributes.addFlashAttribute("userLoginDto", userLoginDto);
            redirectAttributes.addFlashAttribute("badCredentials", true);
            return "redirect:/login";
        }
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }
}
