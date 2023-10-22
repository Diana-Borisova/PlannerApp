package com.example.coffeeshop.controller;



import com.example.coffeeshop.model.dto.OrderDto;
import com.example.coffeeshop.model.entity.Order;
import com.example.coffeeshop.service.OrderService;
import com.example.coffeeshop.service.UserService;
import com.example.coffeeshop.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {


    private final LoggedUser loggedUser;
    private final OrderService orderService;
    private final UserService userService;


    @Autowired
    public HomeController(LoggedUser loggedUser, OrderService orderService, UserService userService) {

        this.loggedUser = loggedUser;
        this.orderService = orderService;
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

        if (!model.containsAttribute("allOrders")) {
            model.addAttribute("allOrders"
                    , this.orderService.getAllOrders());
        }
        List<OrderDto> orders = this.orderService.getAllSorted();
        if (!model.containsAttribute("orders")) {
            model.addAttribute("orders"
                    , this.orderService.getAllSorted());
        }
        if (!model.containsAttribute("neededTime")) {
            model.addAttribute("neededTime"
                    , orders.stream().mapToInt(OrderDto::getNeededTime).sum());
        }

        if (!model.containsAttribute("employee")) {
            model.addAttribute("employee"
                    ,this.userService.getEmployeeWithOrders());
        }

        return "home";

    }

    @GetMapping("/delete/{id}")
    public String removeProduct(@PathVariable("id") Long id) {
        this.orderService.readyOrder(id);
        return "redirect:/home";
    }
}