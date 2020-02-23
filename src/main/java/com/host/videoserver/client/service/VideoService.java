package com.host.videoserver.client.service;

import com.host.videoserver.client.entity.Period;
import com.host.videoserver.client.entity.User;
import com.host.videoserver.client.entity.Video;

import java.util.List;

public interface VideoService {

    Video save(Video video);

    Video getByName(String fileName);

    void remove(String fileName);

    List<Video> getAllByUser(User user);

    List<Video> getLastUploadedVideos(Period period);
}
