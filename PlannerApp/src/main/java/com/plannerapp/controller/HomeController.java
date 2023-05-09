package com.plannerapp.controller;

import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import com.plannerapp.service.TaskService;
import com.plannerapp.service.UserService;
import com.plannerapp.util.LoggedUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/")
public class HomeController {
    private final LoggedUser loggedUser;
    private final TaskService taskService;
    private  final UserService userService;

    public HomeController(LoggedUser loggedUser, TaskService taskService, UserService userService) {
        this.loggedUser = loggedUser;
        this.taskService = taskService;
        this.userService = userService;
    }


    @GetMapping()
    public String index(HttpSession httpSession, Model model){
        if (httpSession.getAttribute("user") == null){
            return "index";
        }

        User user = userService.findUserById(loggedUser.getId()).orElse(null);

        model.addAttribute("currentUserInfo", user);

        if (!model.containsAttribute("userAllTasks")) {
            model.addAttribute("userAllTasks"
                    , this.userService.getAllUserTasks());
        }
    //    model.addAttribute("userTasks", taskService.getAssignedTasks(loggedUser.getId()));
        model.addAttribute("allAvailableTasks",taskService.getAllAvailableTasks());
        model.addAttribute("user", user);

        return "home";
    }


}
