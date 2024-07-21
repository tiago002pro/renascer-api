package com.api.renascer.video.service;

import com.api.renascer.video.model.Video;
import com.api.renascer.video.repository.VideoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {
    private final VideoRepository repository;

    @Autowired
    public VideoService(VideoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<Video> getAll() {
        return repository.findAll();
    }

    public Video getById(Long id) {
        return repository.findById(id).get();
    }

    @Transactional
    public List<Video> getAllByCategory(String category) {
        category = category.equals("ALL") ? "" : category;
        return repository.findAllByCategory(category);
    }

    @Transactional
    public List<Video> getLatest() {
        return repository.findLatest();
    }

    @Transactional
    public List<Video> searchVideos(String search) {
        return repository.searchVideos(search);
    }

    public List<Video> getVideosToNotify() {
        return this.repository.findVideosToNotify();
    }

    public void readVideosByIds(List<Long> ids) {
        this.repository.readVideosByIds(ids);
    }
}
