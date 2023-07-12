package com.example.lionprintfirstproject.dto.employee;

import org.springframework.web.multipart.MultipartFile;

public record CreateEmployee(
        String firstName,
        String lastName,
        String middleName,
        String phoneNumber,
        String address
) {
}
