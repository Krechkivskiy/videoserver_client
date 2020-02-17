package com.host.videoserver.client.controller;

import com.host.videoserver.client.entity.Role;
import com.host.videoserver.client.entity.User;
import com.host.videoserver.client.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import java.util.Arrays;

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
        admin.setRoles(Arrays.asList(new Role[]{new Role("admin")}));
        User user = new User();
        user.setEmail("user");
        user.setPassword("user");
        admin.setRoles(Arrays.asList(new Role[]{new Role("user")}));
        userService.save(admin);
        userService.save(user);
    }

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    @GetMapping("/access/define")
    public String accessDefine(@AuthenticationPrincipal User user) {
        if (user.getRoles().stream().anyMatch(role -> role.getUniqueCode().equals("ROLE_ADMIN"))) {
            //todo send http request to get server token
            return "user_profile";
        } else {
            return "admin_panel";
        }
    }
}
