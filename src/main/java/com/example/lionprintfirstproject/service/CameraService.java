package com.example.lionprintfirstproject.service;

import com.example.lionprintfirstproject.dto.CameraResponse;
import com.example.lionprintfirstproject.dto.CameraResult;
import com.example.lionprintfirstproject.dto.camera.*;
import com.example.lionprintfirstproject.entity.Employee;
import com.example.lionprintfirstproject.entity.EmployeeWorkingDay;
import com.example.lionprintfirstproject.repository.EmployeeWorkingDayRepository;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CameraService {
    private final EmployeeWorkingDayRepository repository;
    private final Gson gson;
    private final EmployeeService employeeService;
    private final RestTemplate restTemplate;

    public String getEmployeeId(String json) {

        CameraResult cameraResult = gson.fromJson(json, CameraResult.class);

        return cameraResult.getAccessControllerEvent().getEmployeeNoString();
    }

    public void detectFaceArrival(HttpServletRequest request) {
        String employeeId = getEmployeeId(request);
        if (employeeId != null) {
            saveEmployeeActionsForArrival(Long.valueOf(employeeId));
        }
    }

    public void detectFaceExit(HttpServletRequest request) {
        String employeeId = getEmployeeId(request);
        if (employeeId != null) {
            saveEmployeeActionsForExit(Long.valueOf(employeeId));
        }
    }

    public String getEmployeeId(HttpServletRequest request) {
        StandardMultipartHttpServletRequest multipartHttpServletRequest = (StandardMultipartHttpServletRequest) request;
        Enumeration<String> parameterNames = multipartHttpServletRequest.getParameterNames();
        Iterator<String> stringIterator = parameterNames.asIterator();

        if (!stringIterator.hasNext())
            return null;

        String next = stringIterator.next();
        String json = multipartHttpServletRequest.getParameter(next);

        return getEmployeeId(json);
    }

    private void saveEmployeeActionsForArrival(Long employeeId) {

        Optional<EmployeeWorkingDay> optionalEmployeeWorkingDay = repository.findByWorkingDateAndEmployeeIdAndInWork(LocalDate.now(), employeeId, true);

        if (optionalEmployeeWorkingDay.isEmpty()) {
            save(employeeId);
        }
    }

    private void saveEmployeeActionsForExit(Long employeeId) {

        Optional<EmployeeWorkingDay> optionalEmployeeWorkingDay = repository.findByWorkingDateAndEmployeeIdAndInWork(LocalDate.now(), employeeId, true);

        optionalEmployeeWorkingDay.ifPresent(this::update);
    }

    private void update(EmployeeWorkingDay employeeWorkingDay) {

        employeeWorkingDay.setExitTime(LocalTime.now());
        employeeWorkingDay.setInWork(false);
        employeeWorkingDay.setWorkingHour(Duration.between(employeeWorkingDay.getArrivalTime(), employeeWorkingDay.getExitTime()));
        repository.save(employeeWorkingDay);
    }

    private void save(Long employeeId) {

        Employee employee = employeeService.getById(employeeId);

        EmployeeWorkingDay employeeWorkingDay = EmployeeWorkingDay.of(employee);

        repository.save(employeeWorkingDay);
    }
}



