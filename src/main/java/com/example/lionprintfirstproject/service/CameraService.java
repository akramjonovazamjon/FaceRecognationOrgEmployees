package com.example.lionprintfirstproject.service;

import com.example.lionprintfirstproject.dto.CameraResult;
import com.example.lionprintfirstproject.dto.camera.CameraDateAndEmployeeId;
import com.example.lionprintfirstproject.entity.Employee;
import com.example.lionprintfirstproject.entity.EmployeeWorkingDay;
import com.example.lionprintfirstproject.repository.EmployeeWorkingDayRepository;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.time.*;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CameraService {
    private final EmployeeWorkingDayRepository repository;
    private final Gson gson;
    private final EmployeeService employeeService;

    public CameraDateAndEmployeeId getEmployeeId(String json) {

        CameraResult cameraResult = gson.fromJson(json, CameraResult.class);
        OffsetDateTime dateTime = OffsetDateTime.parse(cameraResult.getDateTime());
        String employeeNoString = cameraResult.getAccessControllerEvent().getEmployeeNoString();
        return new CameraDateAndEmployeeId(dateTime.toLocalDateTime(), employeeNoString == null ? null : Long.valueOf(employeeNoString));
    }

    public void detectFaceArrival(HttpServletRequest request) {
        CameraDateAndEmployeeId cameraDateAndEmployeeId = getEmployeeId(request);
        if (cameraDateAndEmployeeId.getEmployeeId() != null) {
            saveEmployeeActionsForArrival(cameraDateAndEmployeeId);
        }
    }

    public void detectFaceExit(HttpServletRequest request) {
        CameraDateAndEmployeeId cameraDateAndEmployeeId = getEmployeeId(request);
        if (cameraDateAndEmployeeId.getEmployeeId() != null) {
            saveEmployeeActionsForExit(cameraDateAndEmployeeId);
        }
    }

    public CameraDateAndEmployeeId getEmployeeId(HttpServletRequest request) {
        StandardMultipartHttpServletRequest multipartHttpServletRequest = (StandardMultipartHttpServletRequest) request;
        Enumeration<String> parameterNames = multipartHttpServletRequest.getParameterNames();
        Iterator<String> stringIterator = parameterNames.asIterator();

        if (!stringIterator.hasNext())
            return null;

        String next = stringIterator.next();
        String json = multipartHttpServletRequest.getParameter(next);

        return getEmployeeId(json);
    }

    private void saveEmployeeActionsForArrival(CameraDateAndEmployeeId cameraDateAndEmployeeId) {

        Optional<EmployeeWorkingDay> optionalEmployeeWorkingDay = repository.findByWorkingDateAndEmployeeIdAndInWork(LocalDate.now(), cameraDateAndEmployeeId.getEmployeeId(), true);

        if (optionalEmployeeWorkingDay.isEmpty()) {
            save(cameraDateAndEmployeeId);
        }
    }

    private void saveEmployeeActionsForExit(CameraDateAndEmployeeId cameraDateAndEmployeeId) {

        Optional<EmployeeWorkingDay> optionalEmployeeWorkingDay = repository.findByWorkingDateAndEmployeeIdAndInWork(LocalDate.now(), cameraDateAndEmployeeId.getEmployeeId(), true);

        optionalEmployeeWorkingDay.ifPresent(employeeWorkingDay -> update(employeeWorkingDay, cameraDateAndEmployeeId.getDateTime()));
    }

    private void update(EmployeeWorkingDay employeeWorkingDay, LocalDateTime localDateTime) {

        employeeWorkingDay.setExitTime(LocalTime.of(localDateTime.getHour(), localDateTime.getMinute()));
        employeeWorkingDay.setInWork(false);
        employeeWorkingDay.setWorkingHour(Duration.between(employeeWorkingDay.getArrivalTime(), employeeWorkingDay.getExitTime()));
        repository.save(employeeWorkingDay);
    }

    private void save(CameraDateAndEmployeeId cameraDateAndEmployeeId) {

        Employee employee = employeeService.getById(cameraDateAndEmployeeId.getEmployeeId());

        EmployeeWorkingDay employeeWorkingDay = EmployeeWorkingDay.of(employee, cameraDateAndEmployeeId.getDateTime());

        repository.save(employeeWorkingDay);
    }
}



