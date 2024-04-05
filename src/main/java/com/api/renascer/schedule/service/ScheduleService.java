package com.api.renascer.schedule.service;

import com.api.renascer.schedule.model.Schedule;
import com.api.renascer.schedule.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class ScheduleService {
    private final ScheduleRepository repository;

    @Autowired
    public ScheduleService(ScheduleRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<Schedule> getAll() {
        return repository.findAll();
    }

    @Transactional
    public List<Schedule> getAllByValidDeadline() {
        return repository.findAllByValidDeadline();
    }

    public List<Schedule> getByStartDate(String startDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM", Locale.ENGLISH);
        Date date = formatter.parse(startDate);
        return repository.findByStartDate(date);
    }
}
