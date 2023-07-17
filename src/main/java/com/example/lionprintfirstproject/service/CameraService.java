package com.example.lionprintfirstproject.service;

import com.example.lionprintfirstproject.dto.CameraResult;
import com.example.lionprintfirstproject.entity.Employee;
import com.example.lionprintfirstproject.entity.EmployeeWorkingDay;
import com.example.lionprintfirstproject.repository.EmployeeWorkingDayRepository;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CameraService {
    private final EmployeeWorkingDayRepository repository;
    private final Gson gson;
    private final EmployeeService employeeService;

    public String getEmployeeId(String json) {

        CameraResult cameraResult = gson.fromJson(json, CameraResult.class);

        return cameraResult.getAccessControllerEvent().getEmployeeNoString();
    }

    public void detectFaceArrival(HttpServletRequest request) {

        StandardMultipartHttpServletRequest multipartHttpServletRequest = (StandardMultipartHttpServletRequest) request;
        Enumeration<String> parameterNames = multipartHttpServletRequest.getParameterNames();
        Iterator<String> stringIterator = parameterNames.asIterator();

        if (!stringIterator.hasNext())
            return;

        String next = stringIterator.next();
        String json = multipartHttpServletRequest.getParameter(next);

        String employeeId = getEmployeeId(json);

        if (employeeId != null) {
            saveEmployeeActionsForArrival(Long.valueOf(employeeId));
        }
    }

    public void detectFaceExit(HttpServletRequest request) {

        StandardMultipartHttpServletRequest multipartHttpServletRequest = (StandardMultipartHttpServletRequest) request;
        Enumeration<String> parameterNames = multipartHttpServletRequest.getParameterNames();
        Iterator<String> stringIterator = parameterNames.asIterator();

        if (!stringIterator.hasNext())
            return;

        String next = stringIterator.next();
        String json = multipartHttpServletRequest.getParameter(next);

        String employeeId = getEmployeeId(json);

        if (employeeId != null) {
            saveEmployeeActionsForExit(Long.valueOf(employeeId));
        }
    }

    private void saveEmployeeActionsForArrival(Long employeeId) {

        Optional<EmployeeWorkingDay> optionalEmployeeWorkingDay = repository.findByWorkingDateAndEmployeeId(LocalDate.now(), employeeId);

        if (optionalEmployeeWorkingDay.isEmpty()) {
            save(employeeId);
        } else {
            EmployeeWorkingDay employeeWorkingDay = optionalEmployeeWorkingDay.get();
            employeeWorkingDay.setInWork(true);
            employeeWorkingDay.setExitTime(null);
            repository.save(employeeWorkingDay);
        }
    }

    private void saveEmployeeActionsForExit(Long employeeId) {

        Optional<EmployeeWorkingDay> optionalEmployeeWorkingDay = repository.findByWorkingDateAndEmployeeId(LocalDate.now(), employeeId);

        optionalEmployeeWorkingDay.ifPresent(this::update);
    }

    private void update(EmployeeWorkingDay employeeWorkingDay) {

        employeeWorkingDay.setExitTime(LocalTime.now());
        employeeWorkingDay.setInWork(false);

        repository.save(employeeWorkingDay);
    }

    private void save(Long employeeId) {

        Employee employee = employeeService.getById(employeeId);

        EmployeeWorkingDay employeeWorkingDay = EmployeeWorkingDay.of(employee);

        repository.save(employeeWorkingDay);
    }

}
