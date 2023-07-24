package com.example.lionprintfirstproject.mapper;

import com.example.lionprintfirstproject.dto.schedule.CreateSchedule;
import com.example.lionprintfirstproject.entity.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ScheduleMapper {
    @Mapping(target = "id", ignore = true)
    Schedule asNewSchedule(CreateSchedule dto);
}
