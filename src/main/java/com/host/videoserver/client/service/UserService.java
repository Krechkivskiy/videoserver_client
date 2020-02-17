package com.host.videoserver.client.service;

import com.host.videoserver.client.entity.User;
import com.host.videoserver.client.repository.UserDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void save(User user) {
        userDao.save(user);
    }


    public List<User> getAllUsers() {
        return userDao.findAll();
    }


    public Optional<User> check(String email) {
        return Optional.ofNullable(userDao.findByEmail(email));
    }

    public void deleteUser(int id) {
        userDao.deleteById(id);
    }
}
