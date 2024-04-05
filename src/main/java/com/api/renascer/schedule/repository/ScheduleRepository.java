package com.api.renascer.schedule.repository;

import com.api.renascer.schedule.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query(nativeQuery = true,
            value = "SELECT * FROM schedule s WHERE s.deadline > now() ORDER BY s.start_date")
    List<Schedule> findAllByValidDeadline();

    @Query(nativeQuery = true,
            value = "SELECT * FROM schedule s WHERE s.start_date > :startDate ORDER BY s.start_date")
    List<Schedule> findByStartDate(@Param("startDate") Date startDate);
}
