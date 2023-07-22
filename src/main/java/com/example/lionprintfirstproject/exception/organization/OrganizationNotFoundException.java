package com.example.lionprintfirstproject.exception.organization;


public class OrganizationNotFoundException extends RuntimeException {
    public OrganizationNotFoundException() {
        super("error.organization.not.found");
    }
}
