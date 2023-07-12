package com.example.lionprintfirstproject.mapper;

import com.example.lionprintfirstproject.controller.vm.EmployeeVm;
import com.example.lionprintfirstproject.dto.employee.CreateEmployee;
import com.example.lionprintfirstproject.entity.Department;
import com.example.lionprintfirstproject.entity.Employee;
import com.example.lionprintfirstproject.entity.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DepartmentMapper.class, JobMapper.class})
public interface EmployeeMapper {
    @Mapping(target = "id", ignore = true)
    Employee asNewEmployee(CreateEmployee dto, String imageUrl, Department department, Job job);

    EmployeeVm asEmployeeVm(Employee employee);

}
