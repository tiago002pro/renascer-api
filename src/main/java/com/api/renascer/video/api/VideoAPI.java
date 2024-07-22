package com.api.renascer.video.api;

import com.api.renascer.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity getLatest() {
        return ResponseEntity.ok((service).getLatest());
    }

    @GetMapping("/all")
    public ResponseEntity getAll() {
        return ResponseEntity.ok((service).getAll());
    }

    @GetMapping("/all-by-category/{category}")
    public ResponseEntity getAllByCategory(@PathVariable String category) {
        return ResponseEntity.ok((service).getAllByCategory(category));
    }
}
