package com.host.videoserver.client.service;

import com.host.videoserver.client.entity.User;
import com.host.videoserver.client.entity.command.UserCreateUpdateCommand;
import com.host.videoserver.client.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(UserCreateUpdateCommand command) {
        command.setPassword(passwordEncoder.encode(command.getPassword()));
        userRepository.save(command.toUser());
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public Optional<User> check(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public User update(User userFromDb) {
        return userRepository.save(userFromDb);
    }
}
