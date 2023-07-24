package com.example.lionprintfirstproject.service;

import com.example.lionprintfirstproject.dto.schedule.CreateSchedule;
import com.example.lionprintfirstproject.entity.Schedule;
import com.example.lionprintfirstproject.exception.schedule.ScheduleExistException;
import com.example.lionprintfirstproject.mapper.ScheduleMapper;
import com.example.lionprintfirstproject.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository repository;
    private final ScheduleMapper mapper;

    public Schedule create(CreateSchedule dto) {
        if (repository.existsByStartAndEnd(dto.start(), dto.end())) {
            throw new ScheduleExistException();
        }
        Schedule schedule = mapper.asNewSchedule(dto);
        return repository.save(schedule);
    }
}
