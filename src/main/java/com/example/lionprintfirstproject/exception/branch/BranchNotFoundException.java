package com.example.lionprintfirstproject.exception.branch;

public class BranchNotFoundException extends RuntimeException{
    public BranchNotFoundException() {
        super("error.not_found.branch");
    }
}
