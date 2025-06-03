package com.api.renascer.application.web.schedule;

import com.api.renascer.domain.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleController implements ScheduleApi {

    private final ScheduleService scheduleService;

    @Override
    public ResponseEntity getAll() {
        return null;
    }

    @Override
    public ResponseEntity getAllByValidDeadline() {
        return null;
    }

    @Override
    public ResponseEntity getByStartDate(String startDate) {
        return null;
    }

    @Override
    public ResponseEntity getByStartDate(String month, String year) {
        return null;
    }
}
