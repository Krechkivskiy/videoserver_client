package com.host.videoserver.client.controller;

import com.host.videoserver.client.entity.User;
import com.host.videoserver.client.entity.Video;
import com.host.videoserver.client.repository.UserRepository;
import com.host.videoserver.client.service.UserService;
import com.host.videoserver.client.service.VideoService;
import com.host.videoserver.client.util.HttpHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
public class InitController {

    @Value("${videoserver.username}")
    private String defaultServerUsername;

    @Value("${videoserver.username}")
    private String defaultServerPassword;

    private final UserRepository userRepository;
    private final UserService userService;
    private final HttpHandler requestHandler;
    private final VideoService videoService;
    private final BCryptPasswordEncoder passwordEncoder;

    public InitController(UserRepository userRepository, UserService userService, HttpHandler requestHandler,
                          VideoService videoService, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.requestHandler = requestHandler;
        this.videoService = videoService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        User admin = new User();
        admin.setEmail(defaultServerUsername);
        admin.setPassword(passwordEncoder.encode(defaultServerPassword));
        admin.setSurname("misha");
        admin.setLastname("krechkivskiy");
        String token = requestHandler.registerUserAuthRequest(defaultServerUsername, defaultServerPassword);
        admin.setToken(token);
        userRepository.save(admin);
    }

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    @GetMapping("/cabinet")
    public ModelAndView accessDefine(@AuthenticationPrincipal User user) {
        if (user.getRoles().stream().anyMatch(role -> role.getUniqueCode().equals("ROLE_ADMIN"))) {
            String token = requestHandler.registerUserAuthRequest(user.getUsername(), user.getPassword());
            user.setToken(token);
            userRepository.save(user);
            List<Video> allByUser = videoService.getAllByUser(user);
            ModelAndView map = new ModelAndView("admin_panel");
            map.addObject("videos", allByUser);
            return map;
        } else {
            String token = requestHandler.registerUserAuthRequest(user.getUsername(), user.getPassword());
            user.setToken(token);
            List<Video> allByUser = videoService.getAllByUser(user);
            ModelAndView map = new ModelAndView("user_profile");
            map.addObject("videos", allByUser);
            return map;
        }
    }
}
