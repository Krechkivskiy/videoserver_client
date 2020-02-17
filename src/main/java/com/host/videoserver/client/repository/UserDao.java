package com.host.videoserver.client.repository;


import com.host.videoserver.client.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {

    User findByEmail(String email);
}
