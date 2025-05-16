package com.api.renascer.domain.service;

import com.api.renascer.domain.model.Video;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VideosService {

    Video getById(Long id);

    List<Video> searchVideos(String query);

    Page<Video> searchAll(Integer page);

    Page<Video> getLatestVideos(Integer page);

    Page<Video> getAllByCategory(String category, Integer page);

    Video getLastVideo();

    List<Video> getVideosToNotify();

    void readVideosByIds(List<Long> ids);
}
