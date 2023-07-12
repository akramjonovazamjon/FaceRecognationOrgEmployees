package com.example.lionprintfirstproject.exception.department;

import lombok.Getter;

@Getter
public class DepartmentExistByNameException extends RuntimeException {
    private final String name;

    public DepartmentExistByNameException(String name) {
        super("error.duplicate.department.by_name");
        this.name = name;
    }
}
