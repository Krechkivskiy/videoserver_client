package com.host.videoserver.client.controller;

import com.host.videoserver.client.entity.User;
import com.host.videoserver.client.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/add")
    public String mainPage(@ModelAttribute("user") User user, Model model) {
        userService.save(user);
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }
}
