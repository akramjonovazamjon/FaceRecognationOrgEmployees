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

    public void detectFace(HttpServletRequest request) {

        StandardMultipartHttpServletRequest multipartHttpServletRequest = (StandardMultipartHttpServletRequest) request;
        Enumeration<String> parameterNames = multipartHttpServletRequest.getParameterNames();
        Iterator<String> stringIterator = parameterNames.asIterator();

        if (!stringIterator.hasNext())
            return;

        String next = stringIterator.next();
        String json = multipartHttpServletRequest.getParameter(next);

        CameraResult cameraResult = gson.fromJson(json, CameraResult.class);

        String employeeId = cameraResult.getAccessControllerEvent().getEmployeeNoString();

        if (employeeId != null) {
            saveEmployeeActions(Long.valueOf(employeeId));
        }
    }

    private void saveEmployeeActions(Long employeeId) {

        Optional<EmployeeWorkingDay> optionalEmployeeWorkingDay = repository.findByWorkingDateAndEmployeeId(LocalDate.now(), employeeId);

        if (optionalEmployeeWorkingDay.isPresent()) {
            update(optionalEmployeeWorkingDay.get());
        } else {
            save(employeeId);
        }
    }

    private void update(EmployeeWorkingDay employeeWorkingDay) {

        if (employeeWorkingDay.isInWork()) {
            employeeWorkingDay.setExitTime(LocalTime.now());
            employeeWorkingDay.setInWork(false);
        } else {
            employeeWorkingDay.setExitTime(null);
            employeeWorkingDay.setInWork(true);
        }
        repository.save(employeeWorkingDay);
    }

    private void save(Long employeeId) {

        Employee employee = employeeService.getById(employeeId);

        EmployeeWorkingDay employeeWorkingDay = EmployeeWorkingDay.of(employee);

        repository.save(employeeWorkingDay);
    }

}
