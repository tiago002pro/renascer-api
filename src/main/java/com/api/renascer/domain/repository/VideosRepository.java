package com.api.renascer.domain.repository;

import com.api.renascer.domain.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface VideosRepository {

    Optional<Video> findById(Long id);

    List<Video> searchVideos(String query);

    Page<Video> searchAll(Pageable pageable);

    Page<Video> getLatestVideos(Pageable pageable);

    Page<Video> getAllByCategory(String category, Pageable pageable);

    Video getLastVideo();

    List<Video> getVideosToNotify();

    void readVideosByIds(List<Long> ids);
}
