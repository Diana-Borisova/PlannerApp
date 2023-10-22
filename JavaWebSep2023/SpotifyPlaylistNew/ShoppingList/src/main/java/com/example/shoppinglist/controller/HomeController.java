package com.example.shoppinglist.controller;



import com.example.shoppinglist.model.dto.AddProductDto;
import com.example.shoppinglist.model.entity.Product;
import com.example.shoppinglist.service.ProductService;
import com.example.shoppinglist.service.UserService;
import com.example.shoppinglist.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@Controller
public class HomeController {


    private final LoggedUser loggedUser;
    private final ProductService productService;
    private final UserService userService;


    @Autowired
    public HomeController(LoggedUser loggedUser, ProductService productService, UserService userService) {

        this.loggedUser = loggedUser;
        this.productService = productService;

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

        if (!model.containsAttribute("foodList")) {
            model.addAttribute("foodList"
                    , this.productService.getFood());
        }
      if (!model.containsAttribute("drinkList")) {
            model.addAttribute("drinkList"
                    , this.productService.getDrink());
        }
        if (!model.containsAttribute("householdList")) {
            model.addAttribute("householdList"
                    , this.productService.getHousehold());
        }

        if (!model.containsAttribute("otherList")) {
            model.addAttribute("otherList"
                    , this.productService.getOthers());
        }



        Double totalPriceOfProducts = this.productService.getAllProducts()
                .stream()
                .mapToDouble(Product::getPrice)  // Assuming getDuration returns an int
                .sum();
        if (!model.containsAttribute("totalPriceOfProducts")) {

            model.addAttribute("totalPriceOfProducts"
                    ,String.format("%.2f", totalPriceOfProducts));

        }
        return "home";

    }



    @GetMapping("/delete/{id}")
    public String removeProduct(@PathVariable("id") Long id) {
        this.productService.buyProduct(id);
        return "redirect:/home";
    }

    @GetMapping("/delete-all-products")
    public String removeAllProduct() {
        this.productService.buyAllProduct();
        return "redirect:/home";
    }
}
