package com.example.lionprintfirstproject.exception.department;

import lombok.Getter;

@Getter
public class DepartmentNotFoundByIdException extends RuntimeException {
    private final Long id;

    public DepartmentNotFoundByIdException(Long id) {
        super("error.duplicate.department.by_name");
        this.id = id;
    }
}
