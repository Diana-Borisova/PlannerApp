package com.example.coffeeshop.controller;


import com.example.coffeeshop.model.dto.AddOrderDto;
import com.example.coffeeshop.service.OrderService;
import com.example.coffeeshop.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class OrderController {
    private final OrderService orderService;
    private final LoggedUser loggedUser;

    @Autowired
    public OrderController( OrderService orderService, LoggedUser loggedUser) {
        this.orderService = orderService;

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
    public String addOrder(@Valid @ModelAttribute(name = "addOrderDto")AddOrderDto addOrderDto,
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




    @ModelAttribute(name = "addOrderDto")
    public AddOrderDto addOrderDto(){
        return new AddOrderDto();
    }


}
