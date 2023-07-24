package com.example.lionprintfirstproject.controller.vm;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;

public record ScheduleVm(Long id, @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm") LocalTime startTime, @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm") LocalTime endTime) {

}
