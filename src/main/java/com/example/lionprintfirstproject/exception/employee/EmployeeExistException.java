package com.example.lionprintfirstproject.exception.employee;

public class EmployeeExistException extends RuntimeException {
    public EmployeeExistException() {
        super("error.duplicate.employee.by_phone_number");
    }
}
