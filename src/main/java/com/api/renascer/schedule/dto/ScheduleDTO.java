package com.api.renascer.schedule.dto;

import com.api.renascer.schedule.model.Schedule;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ScheduleDTO {
    private String month;
    private List<Schedule> scheduleList;

    public ScheduleDTO(String month) {
        this.month = month;
        this.scheduleList = new ArrayList<>();
    }
}
