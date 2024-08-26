package com.api.renascer.video.service;

import com.api.renascer.video.model.Video;
import com.api.renascer.video.repository.VideoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Video> searchAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Video getById(Long id) {
        return repository.findById(id).get();
    }

    @Transactional
    public Page<Video> getAllByCategory(String category, Pageable pageable) {
        category = category.equals("ALL") ? "" : category;
        return repository.findAllByCategory(category, pageable);
    }

    @Transactional
    public Page<Video> getLatestVideos(Pageable pageable) {
        return repository.findLatest(pageable);
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

    @Transactional
    public Video getLastVideo() {
        return repository.findLastVideo();
    }
}
