package com.api.renascer.schedule.repository;

import com.api.renascer.schedule.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
