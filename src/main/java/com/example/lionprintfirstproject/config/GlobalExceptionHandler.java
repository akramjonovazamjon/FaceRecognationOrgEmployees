package com.example.lionprintfirstproject.config;

import com.example.lionprintfirstproject.dto.ResponseData;
import com.example.lionprintfirstproject.exception.branch.BranchExistException;
import com.example.lionprintfirstproject.exception.branch.BranchNotFoundException;
import com.example.lionprintfirstproject.exception.department.DepartmentExistByNameException;
import com.example.lionprintfirstproject.exception.department.DepartmentNotFoundByIdException;
import com.example.lionprintfirstproject.exception.employee.EmployeeExistException;
import com.example.lionprintfirstproject.exception.employee.EmployeeNotFoundByIdException;
import com.example.lionprintfirstproject.exception.employee.PictureNotFoundException;
import com.example.lionprintfirstproject.exception.job.JobExistException;
import com.example.lionprintfirstproject.exception.job.JobNotFoundException;
import com.example.lionprintfirstproject.exception.organization.OrganizationExistByNameException;
import com.example.lionprintfirstproject.exception.organization.OrganizationNotFoundException;
import com.example.lionprintfirstproject.exception.schedule.ScheduleExistException;
import com.example.lionprintfirstproject.exception.schedule.ScheduleNoFoundException;
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

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DepartmentExistByNameException.class)
    public ResponseData<Object> handleDepartmentExistByNameException(DepartmentExistByNameException e) {
        return ResponseData.errorOf(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DepartmentNotFoundByIdException.class)
    public ResponseData<Object> handleDepartmentNotFoundByIdException(DepartmentNotFoundByIdException e) {
        return ResponseData.errorOf(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EmployeeExistException.class)
    public ResponseData<Object> handleEmployeeExistException(EmployeeExistException e) {
        return ResponseData.errorOf(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EmployeeNotFoundByIdException.class)
    public ResponseData<Object> handleEmployeeNotFoundByIdException(EmployeeNotFoundByIdException e) {
        return ResponseData.errorOf(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(JobExistException.class)
    public ResponseData<Object> handleJobExistException(JobExistException e) {
        return ResponseData.errorOf(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(JobNotFoundException.class)
    public ResponseData<Object> handleJobNotFoundException(JobNotFoundException e) {
        return ResponseData.errorOf(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(OrganizationExistByNameException.class)
    public ResponseData<Object> handleOrganizationExistByNameException(OrganizationExistByNameException e) {
        return ResponseData.errorOf(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(OrganizationNotFoundException.class)
    public ResponseData<Object> handleOrganizationNotFoundException(OrganizationNotFoundException e) {
        return ResponseData.errorOf(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(BranchExistException.class)
    public ResponseData<Object> handleBranchExistException(BranchExistException e) {
        return ResponseData.errorOf(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(BranchNotFoundException.class)
    public ResponseData<Object> handleBranchNotFoundException(BranchNotFoundException e) {
        return ResponseData.errorOf(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ScheduleExistException.class)
    public ResponseData<Object> handleScheduleExistException(ScheduleExistException e) {
        return ResponseData.errorOf(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ScheduleNoFoundException.class)
    public ResponseData<Object> handleScheduleNoFoundException(ScheduleNoFoundException e) {
        return ResponseData.errorOf(e.getMessage());
    }

}
