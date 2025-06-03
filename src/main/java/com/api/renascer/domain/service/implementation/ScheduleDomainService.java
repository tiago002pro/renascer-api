package com.api.renascer.domain.service.implementation;

import com.api.renascer.domain.dto.ScheduleDTO;
import com.api.renascer.domain.model.Schedule;
import com.api.renascer.domain.repository.ScheduleRepository;
import com.api.renascer.domain.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ScheduleDomainService implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> getAll() {
        return scheduleRepository.getAll();
    }

    @Override
    public List<Schedule> getAllByValidDeadline() {
        return scheduleRepository.getAllByValidDeadline();
    }

    @Override
    public List<Schedule> getScheduleToNotify() {
        return scheduleRepository.getScheduleToNotify();
    }

    @Override
    public List<ScheduleDTO> getByStartDate(String startDate) throws ParseException {
        if (startDate.length() < 10) {
            startDate = startDate + "-01";
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(startDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        List<Schedule> scheduleList = scheduleRepository.findByStartDate(date);

        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();

        for (int y = calendar.get(Calendar.MONTH) + 1 ; y <= 12; y++) {
            ScheduleDTO dto = new ScheduleDTO(String.valueOf(y));
            for (int x = 0; x < scheduleList.size(); x++) {
                if ((scheduleList.get(x).getStartDate().getMonth() + 1) == y) {
                    dto.getScheduleList().add(scheduleList.get(x));
                }
            }
            scheduleDTOList.add(dto);
        }

        return scheduleDTOList;
    }

    @Override
    public List<Schedule> generateSchedule(String month, String year) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String dateString = "01-" + month + "-" + year;
        Date date = formatter.parse(dateString);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int daysOnMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        String[] days = new String[daysOnMonth];
        List<Schedule> scheduleList = new ArrayList<>();

        for (int i = 0; i < daysOnMonth; i++) {
            days[i] = formatter.format(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);

            Schedule schedule = createSchedule(formatter.parse(days[i]));
            if (Objects.nonNull(schedule)) {
                scheduleList.add(schedule);
            }
        }

        return scheduleRepository.saveAll(scheduleList);
    }

    @Override
    public void readScheduleByIds(List<Long> ids) {
        scheduleRepository.readScheduleByIds(ids);
    }

    private Schedule createSchedule(Date date) {
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(date);

        Calendar endDate = Calendar.getInstance();
        endDate.setTime(date);

        switch (startDate.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                startDate.set(Calendar.HOUR, 19);
                endDate.set(Calendar.HOUR, 21);
                return new Schedule("Culto Da Família", startDate.getTime(), endDate.getTime());
            case 2:
                startDate.set(Calendar.HOUR, 20);
                endDate.set(Calendar.HOUR, 21);
                return new Schedule("Reunião De Oração", startDate.getTime(), endDate.getTime());
            case 3:
                startDate.set(Calendar.HOUR, 20);
                endDate.set(Calendar.HOUR, 21);
                return new Schedule("Estudo Conecte", startDate.getTime(), endDate.getTime());
            case 4:
                startDate.set(Calendar.HOUR, 20);
                endDate.set(Calendar.HOUR, 22);
                return new Schedule("Quarta Do Poder", startDate.getTime(), endDate.getTime());
            case 7:
                startDate.set(Calendar.HOUR, 19);
                endDate.set(Calendar.HOUR, 21);
                return new Schedule("Culto De Ensino", startDate.getTime(), endDate.getTime());
        }
        return null;
    }
}
