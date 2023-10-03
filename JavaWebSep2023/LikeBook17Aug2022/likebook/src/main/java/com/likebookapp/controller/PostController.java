package com.likebookapp.controller;


import com.likebookapp.model.dto.AddPostDto;
import com.likebookapp.service.PostService;
import com.likebookapp.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class PostController {
    private final PostService postService;
    private final LoggedUser loggedUser;

    @Autowired
    public PostController(PostService postService, LoggedUser loggedUser) {
        this.postService = postService;
        this.loggedUser = loggedUser;
    }


    @GetMapping("/posts/add")
    public String addPost(){
        if(loggedUser.getId() == null){
            return "redirect:/";
        }
        return "/post-add";
    }

    @PostMapping("/posts/add")
    public String addSong(@Valid @ModelAttribute(name = "addPostDto") AddPostDto addPostDto,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors() || !postService.create(addPostDto)) {

            redirectAttributes.addFlashAttribute("addPostDto", addPostDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addPostDto",
                    bindingResult);
            return "redirect:/posts/add";
        }

        if(loggedUser.getId() == null){
            return "redirect:/";
        }

        return "redirect:/home";
    }

    @ModelAttribute(name = "addPostDto")
    public AddPostDto addPostDto(){
        return new AddPostDto();
    }


}
