package com.taskmanager.controller;


import com.taskmanager.model.User;
import com.taskmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;

    @GetMapping
    public String home(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "users-list";
    }
}