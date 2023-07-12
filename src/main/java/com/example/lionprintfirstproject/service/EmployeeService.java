package com.example.lionprintfirstproject.service;

import com.example.lionprintfirstproject.dto.employee.CreateEmployee;
import com.example.lionprintfirstproject.entity.Department;
import com.example.lionprintfirstproject.entity.Employee;
import com.example.lionprintfirstproject.entity.Job;
import com.example.lionprintfirstproject.exception.PictureNotFoundException;
import com.example.lionprintfirstproject.exception.employee.EmployeeExistException;
import com.example.lionprintfirstproject.mapper.EmployeeMapper;
import com.example.lionprintfirstproject.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;
    private final DepartmentService departmentService;
    private final JobService jobService;

    public Employee create(CreateEmployee dto, MultipartFile file, Long departmentId, Long jobId) throws IOException {

        if (repository.existsByPhoneNumber(dto.phoneNumber())) {
            throw new EmployeeExistException();
        }

        Department department = departmentService.getById(departmentId);

        Job job = jobService.getById(jobId);

        String imageUrl = saveUserPicture(file);

        Employee employee = mapper.asNewEmployee(dto, imageUrl, department, job);

        return repository.save(employee);
    }

    private static final String BASE_IMAGE_PATH = "src/main/resources/";

    private String saveUserPicture(MultipartFile picture) throws IOException {
        if (Objects.isNull(picture) || picture.isEmpty()) {
            throw new PictureNotFoundException();
        }
        String imageUrl = String.format("images/%s.jpg", UUID.randomUUID());
        File file = new File(BASE_IMAGE_PATH + imageUrl);
        file.getParentFile().mkdirs();
        file.createNewFile();
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            byte[] mainContent = picture.getBytes();
            outputStream.write(mainContent);
        }
        return imageUrl;
    }

}
