package com.api.renascer.schedule.repository;

import com.api.renascer.schedule.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query(nativeQuery = true,
            value = "SELECT * FROM schedule s WHERE s.deadline > now() AND s.registration IS TRUE ORDER BY s.start_date")
    List<Schedule> findAllByValidDeadline();

    @Query(nativeQuery = true,
            value = "SELECT * FROM schedule s WHERE s.start_date > :startDate ORDER BY s.start_date")
    List<Schedule> findByStartDate(@Param("startDate") Date startDate);

    @Query(nativeQuery = true,
            value = " SELECT * FROM schedule s WHERE s.notified IS FALSE ORDER BY s.id ")
    List<Schedule> findScheduleToNotify();

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = " UPDATE schedule " +
                    " SET notified = TRUE " +
                    " WHERE id IN :ids ")
    void readScheduleByIds(@Param("ids") List<Long> ids);
}
