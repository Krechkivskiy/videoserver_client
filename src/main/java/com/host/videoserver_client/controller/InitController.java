package com.host.videoserver_client.controller;

import com.host.videoserver_client.entity.Role;
import com.host.videoserver_client.entity.User;
import com.host.videoserver_client.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;

@Controller
public class InitController {

    private final UserService userService;

    public InitController(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        User admin = new User();
        admin.setEmail("admin");
        admin.setPassword("admin");
        admin.setRole(Role.ROLE_ADMIN);
        User user = new User();
        user.setEmail("user");
        user.setPassword("user");
        user.setRole(Role.ROLE_USER);
        userService.save(admin);
        userService.save(user);
    }

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    @GetMapping("/access/define")
    public String accessDefine(@AuthenticationPrincipal User user) {
        if (user.getRole().name().equals("ROLE_ADMIN")) {
            return "user_profile";
        } else {
            return "admin_panel";
        }
    }
}
