package com.example.lionprintfirstproject.controller.vm;

import java.time.LocalDateTime;

public record EmployeeResponse(
        Long id,
        String firstName,
        String lastName,
        String middleName,
        String phoneNumber,
        String address,
        String imageUrl,
        DepartmentVm department,
        JobVm job,
        LocalDateTime beginTime,
        LocalDateTime endTime
) {
}
