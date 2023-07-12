package com.example.lionprintfirstproject.controller;

import com.example.lionprintfirstproject.controller.vm.EmployeeVm;
import com.example.lionprintfirstproject.dto.ResponseData;
import com.example.lionprintfirstproject.dto.employee.CreateEmployee;
import com.example.lionprintfirstproject.entity.Employee;
import com.example.lionprintfirstproject.mapper.EmployeeMapper;
import com.example.lionprintfirstproject.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;
    private final EmployeeMapper mapper;


    @PostMapping
    public ResponseData<EmployeeVm> create(
            @RequestParam(name = "firstName") String firstName,
            @RequestParam(name = "lastName") String lastName,
            @RequestParam(name = "middleName") String middleName,
            @RequestParam(name = "phoneNumber") String phoneNumber,
            @RequestParam(name = "address") String address,
            @RequestParam(name = "file") MultipartFile file,
            @RequestParam(name = "departmentId") Long departmentId,
            @RequestParam(name = "jobId") Long jobId
    ) throws IOException {
        Employee employee = service.create(new CreateEmployee(firstName, lastName, middleName, phoneNumber, address), file, departmentId, jobId);
        return ResponseData.of(mapper.asEmployeeVm(employee));
    }

}
