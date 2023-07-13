package com.example.lionprintfirstproject.exception.employee;

import lombok.Getter;

@Getter
public class EmployeeNotFoundByIdException extends RuntimeException {
    private final Long id;

    public EmployeeNotFoundByIdException(Long id) {
        super("error.duplicate.employee.by_phone_number");
        this.id = id;
    }
}
