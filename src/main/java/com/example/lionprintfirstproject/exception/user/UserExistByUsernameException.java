package com.example.lionprintfirstproject.exception.user;

import lombok.Getter;

@Getter
public class UserExistByUsernameException extends RuntimeException {

    private final String username;

    public UserExistByUsernameException(String username) {
        super("error.duplicate.user.by_username");
        this.username = username;
    }
}
