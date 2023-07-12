package com.example.lionprintfirstproject.exception.job;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException() {
        super("error.not_found.job.by_id");
    }
}
