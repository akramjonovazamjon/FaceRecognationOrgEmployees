package com.example.lionprintfirstproject.exception.schedule;

public class ScheduleNoFoundException extends RuntimeException {
    public ScheduleNoFoundException() {
        super("error.schedule.not.found");
    }
}
