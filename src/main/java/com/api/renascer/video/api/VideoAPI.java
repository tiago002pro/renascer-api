package com.api.renascer.video.api;

import com.api.renascer.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/video")
public class VideoAPI {
    private final VideoService service;

    @Autowired
    public VideoAPI(VideoService service) {
        this.service = service;
    }

    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity getAll() {
        return ResponseEntity.ok((service).getAll());
    }

    @CrossOrigin
    @GetMapping("/all-by-category/{category}")
    public ResponseEntity getAllByCategory(@PathVariable String category) {
        return ResponseEntity.ok((service).getAllByCategory(category));
    }
}
