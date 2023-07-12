package com.example.lionprintfirstproject.dto.user;

import com.example.lionprintfirstproject.entity.UserRole;

public record UpdateUser(String fullName, String username, String password, UserRole role) {
}
