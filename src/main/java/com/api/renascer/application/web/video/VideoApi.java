package com.api.renascer.application.web.video;

import com.api.renascer.domain.dto.ApiResponse;
import com.api.renascer.domain.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping(value = "/videos", produces = MediaType.APPLICATION_JSON_VALUE)
public interface VideoApi {

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<Video>> getById(@PathVariable Long id);

    @GetMapping("/search-videos")
    ResponseEntity<ApiResponse<List<Video>>> searchVideos(@RequestParam("search") String search);

    @GetMapping("/all")
    ResponseEntity<ApiResponse<Page<Video>>> searchAll(@RequestParam("page") Integer page);

    @GetMapping("/latest-videos")
    ResponseEntity<ApiResponse<Page<Video>>> getLatestVideos(@RequestParam("page") Integer page);

    @GetMapping("/all-by-category")
    ResponseEntity<ApiResponse<Page<Video>>> getAllByCategory(@RequestParam("category") String category,
                                                              @RequestParam("page") Integer page);

    @GetMapping("/last-video")
    ResponseEntity<ApiResponse<Video>> getLastVideo();
}
