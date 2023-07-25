package com.example.lionprintfirstproject.exception.schedule;

public class ScheduleExistException extends RuntimeException{
    public ScheduleExistException() {
        super("error.schedule.already.exist.found");
    }
}
