package com.api.renascer.domain.repository;

import com.api.renascer.domain.model.Schedule;

import java.util.Date;
import java.util.List;

public interface ScheduleRepository {

    List<Schedule> getAll();

    List<Schedule> saveAll(List<Schedule> schedules);

    List<Schedule> getAllByValidDeadline();

    List<Schedule> getScheduleToNotify();

    List<Schedule> findByStartDate(Date date);

    void readScheduleByIds(List<Long> ids);
}
