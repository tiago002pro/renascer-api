package com.api.renascer.infrastructure.repository.schedule;

import com.api.renascer.domain.model.Schedule;
import com.api.renascer.domain.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ScheduleRepositoryAdapter implements ScheduleRepository {

    private final ScheduleJPARepository scheduleJPARepository;

    @Override
    public List<Schedule> getAll() {
        return scheduleJPARepository.findAll();
    }

    @Override
    public List<Schedule> saveAll(List<Schedule> schedules) {
        return scheduleJPARepository.saveAll(schedules);
    }

    @Override
    public List<Schedule> getAllByValidDeadline() {
        return scheduleJPARepository.findAllByValidDeadline();
    }

    @Override
    public List<Schedule> getScheduleToNotify() {
        return scheduleJPARepository.findScheduleToNotify();
    }

    @Override
    public List<Schedule> findByStartDate(Date date) {
        return scheduleJPARepository.findByStartDate(date);
    }

    @Override
    public void readScheduleByIds(List<Long> ids) {
        scheduleJPARepository.readScheduleByIds(ids);
    }
}
