package com.coffeeshop.controller;

import com.coffeeshop.helper.LoggedUser;
import com.coffeeshop.model.dto.AddOrderDto;
import com.coffeeshop.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OrderController {
    private final OrderService orderService;
    private final LoggedUser loggedUser;
    public OrderController(OrderService orderService, LoggedUser loggedUser) {
        this.orderService =  orderService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/orders/add")
    public String addOrder(){
        if(loggedUser.getId() == null){
            return "redirect:/";
        }
        return "/order-add";
    }

    @PostMapping("/orders/add")
    public String addShip(@Valid @ModelAttribute(name = "addOrderDto") AddOrderDto addOrderDto,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors() || !orderService.create(addOrderDto)) {

            redirectAttributes.addFlashAttribute("addOrderDto", addOrderDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOrderDto",
                    bindingResult);
            return "redirect:/orders/add";
        }

        if(loggedUser.getId() == null){
            return "redirect:/";
        }

        return "redirect:/home";
    }
    @GetMapping("/orders/ready/{id}")
    public String ready(@PathVariable Long id){
        orderService.readyOrder(id);
        return "redirect:/";
    }
    @ModelAttribute(name = "addOrderDto")
    public AddOrderDto addOrderDto(){
        return new AddOrderDto();
    }
}
