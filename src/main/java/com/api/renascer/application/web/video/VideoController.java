package com.api.renascer.application.web.video;

import com.api.renascer.domain.dto.ApiResponse;
import com.api.renascer.domain.exception.ClientException;
import com.api.renascer.domain.model.Video;
import com.api.renascer.domain.service.VideosService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VideoController implements VideoApi {

    private final VideosService videosService;

    @Override
    public ResponseEntity<ApiResponse<Video>> getById(Long id) {
        try {
            ApiResponse<Video> response = new ApiResponse<>(videosService.getById(id), HttpStatus.OK.value());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ClientException e) {
            ApiResponse<Video> response = new ApiResponse<>(null, e.getHttpStatus().value(), e.getMessage());
            return new ResponseEntity<>(response, e.getHttpStatus());
        }
    }

    @Override
    public ResponseEntity<ApiResponse<List<Video>>> searchVideos(String search) {
        try {
            ApiResponse<List<Video>> response = new ApiResponse<>(videosService.searchVideos(search), HttpStatus.OK.value());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ClientException e) {
            ApiResponse<List<Video>> response = new ApiResponse<>(null, e.getHttpStatus().value(), e.getMessage());
            return new ResponseEntity<>(response, e.getHttpStatus());
        }
    }

    @Override
    public ResponseEntity<ApiResponse<Page<Video>>> searchAll(Integer page) {
        try {
            ApiResponse<Page<Video>> response = new ApiResponse<>(videosService.searchAll(page), HttpStatus.OK.value());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ClientException e) {
            ApiResponse<Page<Video>> response = new ApiResponse<>(null, e.getHttpStatus().value(), e.getMessage());
            return new ResponseEntity<>(response, e.getHttpStatus());
        }
    }

    @Override
    public ResponseEntity<ApiResponse<Page<Video>>> getLatestVideos(Integer page) {
        try {
            ApiResponse<Page<Video>> response = new ApiResponse<>(videosService.getLatestVideos(page), HttpStatus.OK.value());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ClientException e) {
            ApiResponse<Page<Video>> response = new ApiResponse<>(null, e.getHttpStatus().value(), e.getMessage());
            return new ResponseEntity<>(response, e.getHttpStatus());
        }
    }

    @Override
    public ResponseEntity<ApiResponse<Page<Video>>> getAllByCategory(String category, Integer page) {
        try {
            ApiResponse<Page<Video>> response = new ApiResponse<>(videosService.getAllByCategory(category, page), HttpStatus.OK.value());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ClientException e) {
            ApiResponse<Page<Video>> response = new ApiResponse<>(null, e.getHttpStatus().value(), e.getMessage());
            return new ResponseEntity<>(response, e.getHttpStatus());
        }
    }

    @Override
    public ResponseEntity<ApiResponse<Video>> getLastVideo() {
        try {
            ApiResponse<Video> response = new ApiResponse<>(videosService.getLastVideo(), HttpStatus.OK.value());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ClientException e) {
            ApiResponse<Video> response = new ApiResponse<>(null, e.getHttpStatus().value(), e.getMessage());
            return new ResponseEntity<>(response, e.getHttpStatus());
        }
    }
}
