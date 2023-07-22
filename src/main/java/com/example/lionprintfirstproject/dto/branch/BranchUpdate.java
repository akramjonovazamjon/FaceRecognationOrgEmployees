package com.example.lionprintfirstproject.dto.branch;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BranchUpdate(@NotBlank(message = "error.invalid.name.not_blank") String name,
                           String info, @NotNull(message = "error.invalid.organizationId.not_null")Long organizationId) {
}
