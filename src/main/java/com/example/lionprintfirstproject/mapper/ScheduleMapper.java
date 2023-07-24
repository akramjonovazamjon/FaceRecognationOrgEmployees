package com.example.lionprintfirstproject.mapper;

import com.example.lionprintfirstproject.controller.vm.ScheduleVm;
import com.example.lionprintfirstproject.dto.schedule.CreateSchedule;
import com.example.lionprintfirstproject.entity.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ScheduleMapper {
    @Mapping(target = "id", ignore = true)
    Schedule asNewSchedule(CreateSchedule dto);
    ScheduleVm asScheduleVm(Schedule schedule);

    List<ScheduleVm> asScheduleVms(List<Schedule> schedules);
}
