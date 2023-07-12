package com.example.lionprintfirstproject.exception.user;

public class UserPasswordNoMatchesException extends RuntimeException {
    public UserPasswordNoMatchesException() {
        super("error.no_matches.password");
    }
}
