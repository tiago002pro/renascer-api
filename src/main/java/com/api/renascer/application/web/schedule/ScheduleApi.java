package com.api.renascer.application.web.schedule;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public interface ScheduleApi {

    @GetMapping("/all")
    ResponseEntity getAll();

    @GetMapping("/all-schedule-valid-deadline")
    ResponseEntity getAllByValidDeadline();

    @PostMapping("/all-by-date")
    ResponseEntity getByStartDate(@RequestParam("startDate") String startDate);

    @PostMapping("/generate-schedule")
    ResponseEntity getByStartDate(@RequestParam("month") String month,
                                         @RequestParam("year") String year);
}
