package com.example.lionprintfirstproject.exception.branch;

public class BranchExistException extends RuntimeException{
    public BranchExistException() {
        super("error.duplicate.branch");
    }
}
