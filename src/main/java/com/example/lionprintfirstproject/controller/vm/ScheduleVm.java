package com.example.lionprintfirstproject.controller.vm;

import java.time.LocalTime;

public record ScheduleVm(Long id, LocalTime start, LocalTime end) {

}
