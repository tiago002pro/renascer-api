package com.api.renascer.application.web.open.video;

import com.api.renascer.domain.model.Video;
import com.api.renascer.domain.service.VideosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OpenVideoController implements OpenVideoApi {

    private final VideosService videosService;

    @Override
    public ResponseEntity<List<Video>> searchVideos(String query) {
        List<Video> videos = this.videosService.searchVideos(query);
        return ResponseEntity.ok(videos);
    }
}
