package com.example.lionprintfirstproject.controller.vm;

import com.example.lionprintfirstproject.entity.UserRole;

public record UserVm(
        Long id,
        String fullName,
        String username,
        UserRole role
) {
}
