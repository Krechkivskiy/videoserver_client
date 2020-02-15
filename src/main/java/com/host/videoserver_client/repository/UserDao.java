package com.host.videoserver_client.repository;


import com.host.videoserver_client.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {

    User findByEmail(String email);
}
