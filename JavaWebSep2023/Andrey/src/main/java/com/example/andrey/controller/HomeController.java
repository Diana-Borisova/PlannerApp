package com.example.andrey.controller;



import com.example.andrey.model.dto.AddItemDto;
import com.example.andrey.model.entity.Category;
import com.example.andrey.model.entity.CategoryEnum;
import com.example.andrey.repository.CategoryRepository;
import com.example.andrey.repository.ItemRepository;
import com.example.andrey.service.ItemService;
import com.example.andrey.service.UserService;
import com.example.andrey.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

private final CategoryRepository categoryRepository;
    private final LoggedUser loggedUser;

    private final UserService userService;

    private final ItemService itemService;
    private final ItemRepository itemRepository;

    @Autowired
    public HomeController(CategoryRepository categoryRepository, LoggedUser loggedUser, UserService userService, ItemService itemService, ItemRepository itemRepository) {
        this.categoryRepository = categoryRepository;


        this.loggedUser = loggedUser;
        this.userService = userService;
        this.itemService = itemService;
        this.itemRepository = itemRepository;
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

        if (!model.containsAttribute("totalItems")) {
            model.addAttribute("totalItems"
                    , this.itemService.getCountOfAllItems());
        }
        List<CategoryEnum> categories = this.categoryRepository.findAll()
                .stream()
                .map(Category::getName)
                .collect(Collectors.toList());
        if (!model.containsAttribute("allItems")) {
            model.addAttribute("allItems"
                    , this.itemService.getAllItems());
        }
        if (!model.containsAttribute("category")) {
            model.addAttribute("category"
                    , categories);
        }

        return "home";

    }

    @GetMapping("/items/details/{id}")
    public String viewDetails(@PathVariable("id") Long id, Model model){
        if (loggedUser.getId() == null) {
            return "redirect:/login";
        }
        if (!model.containsAttribute("allItems")) {
            model.addAttribute("allItems"
                    , this.itemService.getAllItems());
        }
        if(!model.containsAttribute("currentItem")){
            model.addAttribute("currentItem", this.itemRepository.findById(id));
        }
       this.itemService.viewDetails(id);
        return "details-product";
    }

    @GetMapping("/delete/{id}")
    public String ready(@PathVariable Long id){
        itemService.removeItem(id);
        return "redirect:/home";
    }


        @ModelAttribute(name = "addItemDto")
        public AddItemDto addItemDto(){
            return new AddItemDto();
        }

}
