package com.api.renascer.domain.service;

import com.api.renascer.domain.dto.ScheduleDTO;
import com.api.renascer.domain.model.Schedule;

import java.text.ParseException;
import java.util.List;

public interface ScheduleService {

    List<Schedule> getAll();

    List<Schedule> getAllByValidDeadline();

    List<Schedule> getScheduleToNotify();

    List<ScheduleDTO> getByStartDate(String date) throws ParseException;

    List<Schedule> generateSchedule(String month, String year) throws ParseException;

    void readScheduleByIds(List<Long> ids);
}
