package com.api.renascer.infrastructure.repository.videos;

import com.api.renascer.domain.model.Video;
import com.api.renascer.domain.repository.VideosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VideoRepositoryAdapter implements VideosRepository {

    private final VideoJPARepository videoJPARepository;

    @Override
    public Optional<Video> findById(Long id) {
        return videoJPARepository.findById(id);
    }

    @Override
    public List<Video> searchVideos(String query) {
        return videoJPARepository.searchVideos(query);
    }

    @Override
    public Page<Video> searchAll(Pageable pageable) {
        return videoJPARepository.findAll(pageable);
    }

    @Override
    public Page<Video> getLatestVideos(Pageable pageable) {
        return videoJPARepository.findLatest(pageable);
    }

    @Override
    public Page<Video> getAllByCategory(String category, Pageable pageable) {
        return videoJPARepository.findAllByCategory(category, pageable);
    }

    @Override
    public Video getLastVideo() {
        return videoJPARepository.findLastVideo();
    }

    @Override
    public List<Video> getVideosToNotify() {
        return videoJPARepository.findVideosToNotify();
    }

    @Override
    public void readVideosByIds(List<Long> ids) {
        videoJPARepository.readVideosByIds(ids);
    }
}
