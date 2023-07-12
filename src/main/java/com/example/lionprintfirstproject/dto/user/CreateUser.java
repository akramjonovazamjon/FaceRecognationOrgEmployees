package com.example.lionprintfirstproject.dto.user;

public record CreateUser(
        String fullName,
        String username,
        String password,
        String role
) {
}
