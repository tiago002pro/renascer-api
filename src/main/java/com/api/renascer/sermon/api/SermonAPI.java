package com.api.renascer.sermon.api;

import com.api.renascer.sermon.service.SermonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sermon")
public class SermonAPI {
    private final SermonService service;

    public SermonAPI(SermonService service) {
        this.service = service;
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity getAll(@PathVariable Long id) {
        return ResponseEntity.ok((service).getById(id));
    }

    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity getAll() {
        return ResponseEntity.ok((service).getAll());
    }

    @CrossOrigin
    @GetMapping("/by-speaker")
    public ResponseEntity searchBySpeaker(@RequestParam("speaker") String speaker) {
        return ResponseEntity.ok((service).searchBySpeaker(speaker));
    }

    @CrossOrigin
    @PostMapping("/by-speakers")
    public ResponseEntity searchBySpeakers(@RequestBody List<String> speakers) {
        return ResponseEntity.ok((service).searchBySpeakers(speakers));
    }

    @CrossOrigin
    @GetMapping("/speakers")
    public ResponseEntity searchSpeakers(@RequestParam("name") String name) {
        return ResponseEntity.ok((service).searchSpeakers(name));
    }
}
