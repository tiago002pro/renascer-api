package com.api.renascer.schedule.repository;

import com.api.renascer.schedule.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query(nativeQuery = true,
            value = "SELECT * FROM schedule s WHERE s.deadline > now()")
    List<Schedule> findAllByValidDeadline();
}
