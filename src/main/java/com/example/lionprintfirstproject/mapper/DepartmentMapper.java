package com.example.lionprintfirstproject.mapper;

import com.example.lionprintfirstproject.controller.vm.DepartmentVm;
import com.example.lionprintfirstproject.dto.department.CreateDepartment;
import com.example.lionprintfirstproject.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    @Mapping(target = "id", ignore = true)
    Department asNewDepartment(CreateDepartment dto);

    DepartmentVm asDepartmentVm(Department department);

    List<DepartmentVm> asDepartmentList(List<Department> departments);
}
