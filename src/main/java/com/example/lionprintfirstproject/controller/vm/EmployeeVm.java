package com.example.lionprintfirstproject.controller.vm;

import com.example.lionprintfirstproject.entity.Schedule;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record EmployeeVm(
        Long id,
        String firstName,
        String lastName,
        String middleName,
        String phoneNumber,
        String address,
        String imageUrl,
        DepartmentVm department,
        JobVm job,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDateTime beginTime,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDateTime endTime,
        String gender,
        EmployeeWorkingDayVm workingDay,
        LocalDate dateOfBirth,
        ScheduleVm schedule
) {
}
