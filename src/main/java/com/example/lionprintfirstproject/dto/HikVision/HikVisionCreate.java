package com.example.lionprintfirstproject.dto.HikVision;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HikVisionCreate(@NotBlank(message = "error.invalid.ipAddress.not_blank") String ipAddress,
                              @NotNull(message = "error.invalid.branchId.not_null") Long branchId) {
}
