package com.example.lionprintfirstproject.repository;

import com.example.lionprintfirstproject.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    boolean existsByStartTimeAndEndTime(LocalTime start, LocalTime end);
}
