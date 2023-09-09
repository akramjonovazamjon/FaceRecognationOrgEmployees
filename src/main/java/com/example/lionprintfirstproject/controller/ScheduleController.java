package com.example.lionprintfirstproject.controller;

import com.example.lionprintfirstproject.controller.vm.ScheduleVm;
import com.example.lionprintfirstproject.dto.ResponseData;
import com.example.lionprintfirstproject.dto.schedule.CreateSchedule;
import com.example.lionprintfirstproject.entity.Schedule;
import com.example.lionprintfirstproject.mapper.ScheduleMapper;
import com.example.lionprintfirstproject.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final ScheduleMapper scheduleMapper;

    @PostMapping("")
    public ResponseData<ScheduleVm> create(@RequestBody CreateSchedule createSchedule) {
        ScheduleVm scheduleVm = scheduleMapper.asScheduleVm(scheduleService.create(createSchedule));
        return ResponseData.of(scheduleVm);
    }

    @GetMapping("/{id}")
    public ResponseData<ScheduleVm> getById(@PathVariable Long id) {
        ScheduleVm scheduleVm = scheduleMapper.asScheduleVm(scheduleService.getById(id));
        return ResponseData.of(scheduleVm);
    }

    @GetMapping("")
    public ResponseData<List<ScheduleVm>> getAll() {
        List<Schedule> schedules = scheduleService.getAll();
        List<ScheduleVm> scheduleVms = scheduleMapper.asScheduleVms(schedules);
        return ResponseData.of(scheduleVms);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        scheduleService.deleteById(id);
    }
}
