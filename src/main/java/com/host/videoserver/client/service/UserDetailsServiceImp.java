package com.host.videoserver.client.service;

import com.host.videoserver.client.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private  UserService userService;

    @Autowired
    private  BCryptPasswordEncoder passwordEncoder;

    public UserDetailsServiceImp() {
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.check(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email" + email + "doesn't exist"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }
}
