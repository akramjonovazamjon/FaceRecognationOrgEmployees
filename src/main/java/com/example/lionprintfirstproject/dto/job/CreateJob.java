package com.example.lionprintfirstproject.dto.job;

import jakarta.validation.constraints.NotBlank;

public record CreateJob(@NotBlank(message = "error.invalid.name.not_blank") String name, String info) {
}
