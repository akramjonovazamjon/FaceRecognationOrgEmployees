package com.example.lionprintfirstproject.mapper;

import com.example.lionprintfirstproject.controller.vm.EmployeeResponse;
import com.example.lionprintfirstproject.controller.vm.EmployeeVm;
import com.example.lionprintfirstproject.dto.employee.CreateEmployee;
import com.example.lionprintfirstproject.dto.employee.UpdateEmployee;
import com.example.lionprintfirstproject.entity.Department;
import com.example.lionprintfirstproject.entity.Employee;
import com.example.lionprintfirstproject.entity.EmployeeWorkingDay;
import com.example.lionprintfirstproject.entity.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(uses = {DepartmentMapper.class, JobMapper.class, EmployeeWorkingDayMapper.class})
public interface EmployeeMapper {
    @Mapping(target = "id", ignore = true)
    Employee asNewEmployee(CreateEmployee dto, String imageUrl, Department department, Job job);

    @Mapping(target = "id", source = "employee.id")
    EmployeeVm asEmployeeVm(Employee employee, EmployeeWorkingDay workingDay);

    @Mapping(target = "id", ignore = true)
    void updateEmployee(UpdateEmployee dto, String imageUrl, Department department, Job job, @MappingTarget Employee employee);

    List<EmployeeVm> asEmployeeList(List<Employee> employees);

    EmployeeResponse asEmployee(Employee employee);
}
