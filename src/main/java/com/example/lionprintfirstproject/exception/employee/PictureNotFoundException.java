package com.example.lionprintfirstproject.exception.employee;

public class PictureNotFoundException extends RuntimeException {
    public PictureNotFoundException() {
        super("error.not_found.picture");
    }
}
