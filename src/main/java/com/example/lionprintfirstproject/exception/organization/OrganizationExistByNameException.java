package com.example.lionprintfirstproject.exception.organization;

import lombok.Getter;

@Getter
public class OrganizationExistByNameException extends RuntimeException {
    private final String name;

    public OrganizationExistByNameException(String name) {
        super("error.duplicate.organization.by_name");
        this.name = name;
    }
}
