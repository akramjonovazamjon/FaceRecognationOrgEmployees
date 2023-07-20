package com.example.lionprintfirstproject.dto.employee;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public record CreateEmployee(
        @NotBlank(message = "error.invalid.first_name.not_blank") String firstName,
        @NotBlank(message = "error.invalid.last_name.not_blank") String lastName,
        @NotBlank(message = "error.invalid.middle_name.not_blank") String middleName,
        @NotBlank(message = "error.invalid.phone_number.not_blank") String phoneNumber,
        @NotBlank(message = "error.invalid.address.not_blank") String address,
        LocalDateTime startTime,
        LocalDateTime endTime,
        Gender gender
) {
}
