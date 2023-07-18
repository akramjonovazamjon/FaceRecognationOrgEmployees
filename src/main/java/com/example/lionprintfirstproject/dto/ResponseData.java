package com.example.lionprintfirstproject.dto;

import com.example.lionprintfirstproject.dto.employee.EmployeeCount;

public record ResponseData<T>(T result, ErrorData error, EmployeeCount count) {

    public static <T> ResponseData<T> of(T result) {
        return new ResponseData<>(result, null, null);
    }

    public static <T> ResponseData<T> of(T result, EmployeeCount count) {
        return new ResponseData<>(result, null, count);
    }

    public static ResponseData<Object> errorOf(String message) {
        return new ResponseData<>(null, ErrorData.of(message), null);
    }

}
