package com.api.renascer.video.api;

import com.api.renascer.video.model.Video;
import com.api.renascer.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/renascer-api/video")
public class VideoAPI {
    private final VideoService service;

    @Autowired
    public VideoAPI(VideoService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        return ResponseEntity.ok((service).getById(id));
    }

    @GetMapping("/search-videos")
    public ResponseEntity searchVideos(@RequestParam("search") String search) {
        return ResponseEntity.ok((service).searchVideos(search));
    }

    @GetMapping("/latest-videos")
    public ResponseEntity<Page<Video>> getLatestVideos(@RequestParam("page") Integer page) {
        Pageable pageable = PageRequest.of(page, 2);
        return ResponseEntity.ok((service).getLatestVideos(pageable));
    }

    @GetMapping("/all")
    public ResponseEntity searchAll(@RequestParam("page") Integer page) {
        Pageable pageable = PageRequest.of(page, 3);
        return ResponseEntity.ok((service).searchAll(pageable));
    }
    @GetMapping("/all-by-category")
    public ResponseEntity getAllByCategory(@RequestParam("category") String category,
                                           @RequestParam("page") Integer page) {
        Pageable pageable = PageRequest.of(page, 3);
        return ResponseEntity.ok((service).getAllByCategory(category, pageable));
    }

    @GetMapping("/last-video")
    public ResponseEntity<Video> getLastVideo() {
        return ResponseEntity.ok((service).getLastVideo());
    }
}
