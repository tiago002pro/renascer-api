package com.api.renascer.schedule.api;

import com.api.renascer.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/renascer-api/schedule")
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

    @GetMapping("/all-schedule-valid-deadline")
    public ResponseEntity getAllByValidDeadline() {
        return ResponseEntity.ok((service).getAllByValidDeadline());
    }

    @PostMapping("/all-by-date")
    public ResponseEntity getByStartDate(@RequestParam("startDate") String startDate) throws ParseException {
        return ResponseEntity.ok((service).getByStartDate(startDate));
    }

    @PostMapping("/generate-schedule")
    public ResponseEntity getByStartDate(@RequestParam("month") String month,
                                         @RequestParam("year") String year) throws ParseException {
        return ResponseEntity.ok((service).generateSchedule(month, year));
    }
}
