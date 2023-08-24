package com.likebookapp.controller;


import com.likebookapp.model.binding.PostAddBindingModel;
import com.likebookapp.model.binding.PostServiceModel;
import com.likebookapp.service.PostService;
import com.likebookapp.service.UserService;
import com.likebookapp.util.LoggedUser;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/posts")
public class PostController {
    private final LoggedUser loggedUser;
    private final PostService postService;
    private  final UserService userService;

    private final ModelMapper modelMapper;

    public PostController(LoggedUser loggedUser, PostService postService, UserService userService, ModelMapper modelMapper) {
        this.loggedUser = loggedUser;
        this.postService = postService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String add(Model model){
        if (!model.containsAttribute("postAddBindingModel")) {

            model.addAttribute("postAddBindingModel", new PostAddBindingModel());
            model.addAttribute("isAdded", false);
        }
        return "post-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid PostAddBindingModel postAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("postAddBindingModel", postAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.postAddBindingModel",
                    bindingResult);
            return "redirect:add";
        }

        postService.addPost(modelMapper.map(postAddBindingModel, PostServiceModel.class));
        return "redirect:/";
    }

    @GetMapping("/assigned-to-me/{id}")
    public String assignedToMeButton(@PathVariable Long id){

        if (loggedUser.getId() == null) {
            return "redirect:/users/login";
        }

       // taskService.addTaskToMe(id);

        return "redirect:/";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable Long id){

        if (loggedUser.getId() == null) {
            return "redirect:/users/login";
        }

      //  taskService.removeTask(id);

        return "redirect:/";
    }

    @GetMapping("/return/{id}")
    public String returnTask(@PathVariable Long id){

        if (loggedUser.getId() == null) {
            return "redirect:/users/login";
        }
   //     taskService.returnTaskToAllAvailableTasks(id);


        return "redirect:/";
    }




}
