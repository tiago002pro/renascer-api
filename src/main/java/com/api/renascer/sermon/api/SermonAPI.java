package com.api.renascer.sermon.api;

import com.api.renascer.sermon.service.SermonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sermon")
public class SermonAPI {
    private final SermonService service;

    public SermonAPI(SermonService service) {
        this.service = service;
    }

    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity getAll() {
        return ResponseEntity.ok((service).getAll());
    }
}
