package com.example.lionprintfirstproject.config;

import com.example.lionprintfirstproject.dto.ResponseData;
import com.example.lionprintfirstproject.exception.*;
import com.example.lionprintfirstproject.exception.user.UserExistByUsernameException;
import com.example.lionprintfirstproject.exception.user.UserNotFoundByIdException;
import com.example.lionprintfirstproject.exception.user.UserNotFoundByUsernameException;
import com.example.lionprintfirstproject.exception.user.UserPasswordNoMatchesException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(PictureNotFoundException.class)
    public ResponseData<Object> handlePictureNotFoundException(PictureNotFoundException e) {
        return ResponseData.errorOf(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserExistByUsernameException.class)
    public ResponseData<Object> handleUserExistByPhoneNumberException(UserExistByUsernameException e) {
        return ResponseData.errorOf(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserNotFoundByIdException.class)
    public ResponseData<Object> handleUserNotFoundByIdException(UserNotFoundByIdException e) {
        return ResponseData.errorOf(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserNotFoundByUsernameException.class)
    public ResponseData<Object> handleUserNotFoundByPhoneNumberException(UserNotFoundByUsernameException e) {
        return ResponseData.errorOf(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserPasswordNoMatchesException.class)
    public ResponseData<Object> handleUserPasswordNoMatchesException(UserPasswordNoMatchesException e) {
        return ResponseData.errorOf(e.getMessage());
    }

}
