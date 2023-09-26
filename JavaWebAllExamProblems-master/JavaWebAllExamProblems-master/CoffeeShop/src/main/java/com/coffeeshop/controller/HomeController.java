package com.coffeeshop.controller;

import com.coffeeshop.helper.LoggedUser;
import com.coffeeshop.model.dto.OrderDto;
import com.coffeeshop.service.OrderService;
import com.coffeeshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

private final OrderService orderService;
private final UserService userService;
    private final LoggedUser loggedUser;


    @Autowired
    public HomeController(OrderService orderService, UserService userService, LoggedUser loggedUser) {
        this.orderService = orderService;
        this.userService = userService;

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
        List<OrderDto> allOrders = this.orderService.getAllSorted();
        model.addAttribute("allOrders", allOrders);
        model.addAttribute("allPreparingTime",allOrders.stream()
                .mapToInt(OrderDto::getNeededTime).sum());
        model.addAttribute("users",this.userService.findAllByOrdersSizeDesc());
        return "home";

    }

//    @PostMapping("/all-orders")
//    public String allOrders(@Valid @ModelAttribute(name = "orderDto") OrderDto orderDto,
//        BindingResult bindingResult,
//        RedirectAttributes redirectAttributes) throws Exception {
//
//            if (loggedUser.getId() == null) {
//                return "redirect:/";
//            }
//
//            if (bindingResult.hasErrors()) {
//
//                redirectAttributes.addFlashAttribute("orderDto", orderDto);
//                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderDto", bindingResult);
//
//                return "redirect:/home";
//            }
//          //  this.battleService.attack(startBattleDto);
//
//            return "redirect:/home";
//    }




}
