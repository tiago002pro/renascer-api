package com.api.renascer.schedule.service;

import com.api.renascer.schedule.dto.ScheduleDTO;
import com.api.renascer.schedule.model.Schedule;
import com.api.renascer.schedule.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    public List<ScheduleDTO> getByStartDate(String startDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM", Locale.ENGLISH);
        Date date = formatter.parse(startDate);
        List<Schedule> scheduleList = repository.findByStartDate(date);

        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();

        for (int y = date.getMonth() + 1 ; y <= 12; y++) {
            ScheduleDTO dto = new ScheduleDTO(String.valueOf(y));
            for (int x = 0; x < scheduleList.size(); x++) {
                if (scheduleList.get(x).getStartDate().getMonth() == y) {
                    dto.getScheduleList().add(scheduleList.get(x));
                }
            }
            scheduleDTOList.add(dto);
        }

        return scheduleDTOList;
    }
}
