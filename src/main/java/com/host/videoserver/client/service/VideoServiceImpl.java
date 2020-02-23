package com.host.videoserver.client.service;

import com.host.videoserver.client.entity.Period;
import com.host.videoserver.client.entity.User;
import com.host.videoserver.client.entity.Video;
import com.host.videoserver.client.repository.VideoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;

    public VideoServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public Video save(Video video) {
        return videoRepository.save(video);
    }

    @Override
    public Video getByName(String fileName) {
        return videoRepository.findByName(fileName);
    }

    @Override
    public void remove(String fileName) {
        videoRepository.delete(getByName(fileName));
    }

    @Override
    public List<Video> getAllByUser(User user) {
        return videoRepository.findAllByUser(user.getId());
    }

    @Override
    public List<Video> getLastUploadedVideos(Period period) {
        return null;
    }
}
