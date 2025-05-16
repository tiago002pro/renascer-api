package com.api.renascer.domain.service.implementation;

import com.api.renascer.domain.exception.HttpResquestException;
import com.api.renascer.domain.exception.NotFoundDatabaseException;
import com.api.renascer.domain.model.Video;
import com.api.renascer.domain.repository.VideosRepository;
import com.api.renascer.domain.service.VideosService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideosDomainService implements VideosService {

    private final VideosRepository videosRepository;

    private final String NOT_FOUND_MESSAGE = "Nenhum reultado encontrado";

    @Override
    public Video getById(Long id) {
        try {
            Optional<Video> video = videosRepository.findById(id);
            if (video.isPresent()) {
                return video.get();
            } else {
                throw new NotFoundDatabaseException(NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND);
            }
        } catch (HttpResquestException e) {
            throw new HttpResquestException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<Video> searchVideos(String query) {
        try {
            return videosRepository.searchVideos(query);
        } catch (HttpResquestException e) {
            throw new HttpResquestException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Page<Video> searchAll(Integer page) {
        try {
            Pageable pageable = PageRequest.of(page, 3);
            return videosRepository.searchAll(pageable);
        } catch (HttpResquestException e) {
            throw new HttpResquestException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Page<Video> getLatestVideos(Integer page) {
        try {
            Pageable pageable = PageRequest.of(page, 3);
            return videosRepository.getLatestVideos(pageable);
        } catch (HttpResquestException e) {
            throw new HttpResquestException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Page<Video> getAllByCategory(String category, Integer page) {
        try {
            Pageable pageable = PageRequest.of(page, 3);
            category = category.equals("ALL") ? "" : category;
            return videosRepository.getAllByCategory(category, pageable);
        } catch (HttpResquestException e) {
            throw new HttpResquestException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Video getLastVideo() {
        try {
            return videosRepository.getLastVideo();
        } catch (HttpResquestException e) {
            throw new HttpResquestException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<Video> getVideosToNotify() {
        try {
            return videosRepository.getVideosToNotify();
        } catch (HttpResquestException e) {
            throw new HttpResquestException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void readVideosByIds(List<Long> ids) {
        try {
            videosRepository.readVideosByIds(ids);
        } catch (HttpResquestException e) {
            throw new HttpResquestException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
