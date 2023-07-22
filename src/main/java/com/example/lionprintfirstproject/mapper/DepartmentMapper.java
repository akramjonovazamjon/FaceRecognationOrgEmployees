package com.example.lionprintfirstproject.mapper;

import com.example.lionprintfirstproject.controller.vm.DepartmentVm;
import com.example.lionprintfirstproject.dto.department.CreateDepartment;
import com.example.lionprintfirstproject.entity.Branch;
import com.example.lionprintfirstproject.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "info", source = "dto.info")
    Department asNewDepartment(CreateDepartment dto, Branch branch);

    DepartmentVm asDepartmentVm(Department department);

    List<DepartmentVm> asDepartmentList(List<Department> departments);
}
