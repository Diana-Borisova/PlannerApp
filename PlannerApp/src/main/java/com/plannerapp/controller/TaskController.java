package com.plannerapp.controller;

import com.plannerapp.model.binding.TaskAddBindingModel;
import com.plannerapp.model.services.TaskServiceModel;
import com.plannerapp.service.TaskService;
import com.plannerapp.service.UserService;
import com.plannerapp.util.LoggedUser;
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
@RequestMapping("/tasks")
public class TaskController {
    private final LoggedUser loggedUser;
    private final TaskService taskService;
    private  final UserService userService;

    private final ModelMapper modelMapper;

    public TaskController(LoggedUser loggedUser, TaskService taskService, UserService userService, ModelMapper modelMapper) {
        this.loggedUser = loggedUser;
        this.taskService = taskService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/add")
    public String add(Model model){
        if (!model.containsAttribute("taskAddBindingModel")) {

            model.addAttribute("taskAddBindingModel", new TaskAddBindingModel());
            model.addAttribute("isAdded", false);
        }
        return "task-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid TaskAddBindingModel taskAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("taskAddBindingModel", taskAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.taskAddBindingModel",
                    bindingResult);
            return "redirect:add";
        }

        taskService.addTask(modelMapper.map(taskAddBindingModel, TaskServiceModel.class));
        return "redirect:/";
    }

    @GetMapping("/assigned-to-me/{id}")
    public String assignedToMeButton(@PathVariable Long id){

        if (loggedUser.getId() == null) {
            return "redirect:/users/login";
        }

        taskService.addTaskToMe(id);

        return "redirect:/";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable Long id){

        if (loggedUser.getId() == null) {
            return "redirect:/users/login";
        }

        taskService.removeTask(id);

        return "redirect:/";
    }

    @GetMapping("/return/{id}")
    public String returnTask(@PathVariable Long id){

        if (loggedUser.getId() == null) {
            return "redirect:/users/login";
        }
        taskService.returnTaskToAllAvailableTasks(id);


        return "redirect:/";
    }




}
