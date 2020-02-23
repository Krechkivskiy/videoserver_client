package com.host.videoserver.client.repository;

import com.host.videoserver.client.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {

    Video findByName(String name);

    @Query(value = "select v from User u inner join u.videos v where u.id = :userId")
    List<Video> findAllByUser(Long userId);
}
