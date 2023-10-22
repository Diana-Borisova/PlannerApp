package com.example.shoppinglist.controller;

import com.example.shoppinglist.model.dto.AddProductDto;
import com.example.shoppinglist.service.ProductService;
import com.example.shoppinglist.util.LoggedUser;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class ProductController {
    private final ProductService productService;
    private final LoggedUser loggedUser;

    @Autowired
    public ProductController(ProductService productService, LoggedUser loggedUser) {
        this.productService = productService;
        this.loggedUser = loggedUser;
    }


    @GetMapping("/products/add")
    public String addProduct(){
        if(loggedUser.getId() == null){
            return "redirect:/";
        }
        return "/product-add";
    }

    @PostMapping("/products/add")
    public String addProduct(@Valid @ModelAttribute(name = "addProductDto") AddProductDto addProductDto,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors() || !productService.create(addProductDto)) {

            redirectAttributes.addFlashAttribute("addProductDto", addProductDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addProductDto",
                    bindingResult);
            return "redirect:/products/add";
        }

        if(loggedUser.getId() == null){
            return "redirect:/";
        }

        return "redirect:/home";
    }

    @ModelAttribute(name = "addProductDto")
    public AddProductDto addProductDto(){
        return new AddProductDto();
    }


}
