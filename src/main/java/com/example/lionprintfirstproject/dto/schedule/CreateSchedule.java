package com.example.lionprintfirstproject.dto.schedule;

import java.time.LocalTime;

public record CreateSchedule(LocalTime start, LocalTime end) {
}
