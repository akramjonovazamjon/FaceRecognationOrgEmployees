package com.example.lionprintfirstproject.exception.job;

public class JobExistException extends RuntimeException {
    public JobExistException() {
        super("error.duplicate.job.by_name");
    }
}
