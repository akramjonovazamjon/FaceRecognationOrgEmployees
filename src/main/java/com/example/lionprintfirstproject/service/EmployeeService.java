package com.example.lionprintfirstproject.service;

import com.example.lionprintfirstproject.controller.vm.EmployeeVm;
import com.example.lionprintfirstproject.dto.employee.EmployeeCount;
import com.example.lionprintfirstproject.dto.employee.CreateEmployee;
import com.example.lionprintfirstproject.dto.employee.UpdateEmployee;
import com.example.lionprintfirstproject.entity.Department;
import com.example.lionprintfirstproject.entity.Employee;
import com.example.lionprintfirstproject.entity.EmployeeWorkingDay;
import com.example.lionprintfirstproject.entity.Job;
import com.example.lionprintfirstproject.exception.employee.PictureNotFoundException;
import com.example.lionprintfirstproject.exception.employee.EmployeeExistException;
import com.example.lionprintfirstproject.exception.employee.EmployeeNotFoundByIdException;
import com.example.lionprintfirstproject.mapper.EmployeeMapper;
import com.example.lionprintfirstproject.repository.EmployeeRepository;
import com.example.lionprintfirstproject.repository.EmployeeWorkingDayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;
    private final DepartmentService departmentService;
    private final JobService jobService;
    private final EmployeeWorkingDayRepository employeeWorkingDayRepository;
    private final CameraEmployeeService cameraEmployeeService;
    private final String HIK_VISION_ENTER = "http://192.168.0.191";
    private final String HIK_VISION_EXIT = "http://192.168.0.192";

    public Employee create(CreateEmployee dto, MultipartFile file, Long departmentId, Long jobId) throws IOException {

        if (repository.existsByPhoneNumber(dto.phoneNumber())) {
            throw new EmployeeExistException();
        }

        Department department = departmentService.getById(departmentId);

        Job job = jobService.getById(jobId);

        String imageUrl = saveUserPicture(file);

        Employee employee = mapper.asNewEmployee(dto, imageUrl, department, job);

        Employee savedEmployee = repository.save(employee);
        boolean b = cameraEmployeeService.saveEmployeeToCamera(savedEmployee,HIK_VISION_ENTER ,true);
        employee.setAddedToEnter(b);
        b = cameraEmployeeService.saveEmployeeToCamera(savedEmployee,HIK_VISION_EXIT,true);
        employee.setAddedToExit(b);

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
        return "http://192.168.0.133:8008/" + imageUrl;
    }

    public Employee getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundByIdException(id));
    }

    public List<Employee> getAll(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    public void update(UpdateEmployee dto, MultipartFile file, Long id, Long departmentId, Long jobId) throws IOException {

        if (repository.existsByPhoneNumberAndIdNot(dto.phoneNumber(), id)) {
            throw new EmployeeExistException();
        }

        Department department = departmentService.getById(departmentId);

        Job job = jobService.getById(jobId);

        String imageUrl = saveUserPicture(file);

        Employee employee = getById(id);

        mapper.updateEmployee(dto, imageUrl, department, job, employee);

        Employee savedEmployee = repository.save(employee);
        boolean b = cameraEmployeeService.saveEmployeeToCamera(savedEmployee,HIK_VISION_ENTER ,false);
        employee.setAddedToEnter(b);
        b = cameraEmployeeService.saveEmployeeToCamera(savedEmployee,HIK_VISION_EXIT,false);
        employee.setAddedToExit(b);
        repository.save(employee);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public EmployeeCount getEmployeesCount() {
        long all = repository.count();
        long here = employeeWorkingDayRepository.countByWorkingDate(LocalDate.now());
        return new EmployeeCount(all, here, all - here);
    }

    public List<Employee> getAllByDepartmentId(Long departmentId, Pageable pageable) {
        return repository.findAllByDepartmentId(departmentId, pageable).getContent();
    }

    public List<Employee> getAllByJobId(Long jobId, Pageable pageable) {
        return repository.findAllByJobId(jobId, pageable).getContent();
    }

    public List<EmployeeVm> getAllEmployees(Pageable pageable) {
        List<Employee> employees = getAll(pageable);
        return employees.stream().map(this::convertEmployeeToEmployeeVm).toList();
    }

    public List<EmployeeVm> getAllEmployeesByJobId(Long jobId, Pageable pageable) {
        List<Employee> employeeList = getAllByJobId(jobId, pageable);
        return employeeList.stream().map(this::convertEmployeeToEmployeeVm).toList();
    }

    public List<EmployeeVm> getAllEmployeesByDepartmentId(Long departmentId, Pageable pageable) {
        List<Employee> employeeList = getAllByDepartmentId(departmentId, pageable);
        return employeeList.stream().map(this::convertEmployeeToEmployeeVm).toList();
    }

    public EmployeeVm getEmployeeById(Long id) {
        return convertEmployeeToEmployeeVm(getById(id));
    }

    private EmployeeVm convertEmployeeToEmployeeVm(Employee employee) {
        List<EmployeeWorkingDay> arrivalTime = employeeWorkingDayRepository.findByWorkingDateAndEmployeeIdOrderByArrivalTime(LocalDate.now(), employee.getId());
        if (arrivalTime.isEmpty()) {
            return mapper.asEmployeeVm(employee, null);
        } else {
            return mapper.asEmployeeVm(employee, arrivalTime.get(0));
        }
    }

}
