package com.example.gira.controller;

import com.example.gira.model.dto.AddTaskDto;
import com.example.gira.service.TaskService;
import com.example.gira.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class TaskController {
    private final TaskService taskService;
    private final LoggedUser loggedUser;

    @Autowired
    public TaskController( TaskService taskService, LoggedUser loggedUser) {
        this.taskService = taskService;
        this.loggedUser = loggedUser;
    }


    @GetMapping("/tasks/add")
    public String addTask(){
        if(loggedUser.getId() == null){
            return "redirect:/";
        }
        return "/add-task";
    }

    @PostMapping("/tasks/add")
    public String addSong(@Valid @ModelAttribute(name = "addTaskDto")AddTaskDto addTaskDto,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors() || !taskService.create(addTaskDto)) {

            redirectAttributes.addFlashAttribute("addTaskDto", addTaskDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addTaskDto",
                    bindingResult);
            return "redirect:/tasks/add";
        }

        if(loggedUser.getId() == null){
            return "redirect:/";
        }

        return "redirect:/home";
    }

    @ModelAttribute(name = "addTaskDto")
    public AddTaskDto addTaskDto(){
        return new AddTaskDto();
    }


}
