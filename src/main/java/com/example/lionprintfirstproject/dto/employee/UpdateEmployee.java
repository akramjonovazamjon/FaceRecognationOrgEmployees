package com.example.lionprintfirstproject.dto.employee;

import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

public record UpdateEmployee(
        String firstName,
        String lastName,
        String middleName,
        String phoneNumber,
        String address,
        LocalDateTime beginTime,
        LocalDateTime endTime,
        Gender gender
) {
}
