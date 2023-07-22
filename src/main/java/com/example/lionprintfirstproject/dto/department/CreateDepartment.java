package com.example.lionprintfirstproject.dto.department;

import jakarta.validation.constraints.NotBlank;

public record CreateDepartment(
        @NotBlank(message = "error.invalid.name.not_blank") String name,
        String info,
        Long branchId
) {
}
