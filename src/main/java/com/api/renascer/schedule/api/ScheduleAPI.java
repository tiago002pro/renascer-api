package com.api.renascer.schedule.api;

import com.api.renascer.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleAPI {
    private final ScheduleService service;

    @Autowired
    public ScheduleAPI(ScheduleService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity getAll() {
        return ResponseEntity.ok((service).getAll());
    }
}
