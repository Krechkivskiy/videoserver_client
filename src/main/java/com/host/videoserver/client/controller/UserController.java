package com.host.videoserver.client.controller;

import com.host.videoserver.client.entity.command.UserCreateUpdateCommand;
import com.host.videoserver.client.service.UserService;
import com.host.videoserver.client.util.HttpHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;
    private final HttpHandler requestHandler;

    public UserController(UserService userService, HttpHandler requestHandler) {
        this.userService = userService;
        this.requestHandler = requestHandler;
    }

    @GetMapping("/registration")
    public String registerPage(@ModelAttribute("user") UserCreateUpdateCommand userCommand, Model model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("user") UserCreateUpdateCommand userCommand, Model model) {
        userService.save(userCommand);
        requestHandler.registerUserAuthRequest(userCommand.getEmail(),userCommand.getPassword());
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }
}
