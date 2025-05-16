package com.api.renascer.application.web.open.video;

import com.api.renascer.domain.model.Video;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping(value = "/open/videos", produces = MediaType.APPLICATION_JSON_VALUE)
public interface OpenVideoApi {

    @GetMapping("/search")
    ResponseEntity<List<Video>> searchVideos(@RequestParam("query") String query);
}
