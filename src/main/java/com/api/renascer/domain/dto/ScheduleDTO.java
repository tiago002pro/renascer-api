package com.api.renascer.domain.dto;

import com.api.renascer.domain.model.Schedule;
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
