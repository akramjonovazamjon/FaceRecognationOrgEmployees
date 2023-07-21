package com.example.lionprintfirstproject.dto.camera.employee;

public record UpdateEmployee(
        String firstName,
        String lastName,
        String middleName,
        String phoneNumber,
        String address
) {
}
