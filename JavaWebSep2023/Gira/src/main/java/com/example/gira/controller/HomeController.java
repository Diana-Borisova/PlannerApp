package com.example.gira.controller;

import com.example.gira.model.dto.AddTaskDto;
import com.example.gira.service.TaskService;
import com.example.gira.service.UserService;
import com.example.gira.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    private final TaskService taskService;

    private final LoggedUser loggedUser;

    private final UserService userService;


    @Autowired
    public HomeController(TaskService taskService, LoggedUser loggedUser, UserService userService) {
        this.taskService = taskService;
        this.loggedUser = loggedUser;
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

//        if (!model.containsAttribute("totalCopies")) {
//            model.addAttribute("totalCopies"
//                    , this.albumService.getCountOfAllCopies());
//        }
//
        if (!model.containsAttribute("allTasks")) {
            model.addAttribute("allTasks"
                    , this.taskService.getAllTasks());
        }

        return "home";

    }

    @GetMapping("/progress/{id}")
    public String changeProgress(@PathVariable("id") Long id) {
        if (loggedUser.getId() == null) {
            return "redirect:/login";
        }

        this.taskService.changeProgress(id);
        return "redirect:/home";

    }
        @ModelAttribute(name = "addTaskDto")
        public AddTaskDto addTaskDto () {
            return new AddTaskDto();
        }

    }
