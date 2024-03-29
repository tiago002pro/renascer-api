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

    @Transactional
    public List<Video> getAllByCategory(String category) {
        return repository.findAllByCategory(category);
    }

    @Transactional
    public List<Video> getLatest() {
        return repository.findLatest();
    }
}
