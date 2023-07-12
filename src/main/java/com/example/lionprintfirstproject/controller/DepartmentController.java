package com.example.lionprintfirstproject.controller;

import com.example.lionprintfirstproject.controller.vm.DepartmentVm;
import com.example.lionprintfirstproject.dto.ResponseData;
import com.example.lionprintfirstproject.dto.department.CreateDepartment;
import com.example.lionprintfirstproject.dto.department.DepartmentFilter;
import com.example.lionprintfirstproject.dto.department.UpdateDepartment;
import com.example.lionprintfirstproject.entity.Department;
import com.example.lionprintfirstproject.mapper.DepartmentMapper;
import com.example.lionprintfirstproject.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService service;
    private final DepartmentMapper mapper;

    @PostMapping
    public ResponseData<DepartmentVm> create(@RequestBody @Valid CreateDepartment dto) {
        Department department = service.create(dto);
        return ResponseData.of(mapper.asDepartmentVm(department));
    }

    @GetMapping("/{id}")
    public ResponseData<DepartmentVm> getById(@PathVariable Long id) {
        Department department = service.getById(id);
        return ResponseData.of(mapper.asDepartmentVm(department));
    }

    @GetMapping
    public ResponseData<List<DepartmentVm>> getAll(DepartmentFilter filter, Pageable pageable) {
        List<Department> departments = service.getAll(filter, pageable);
        return ResponseData.of(mapper.asDepartmentList(departments));
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody UpdateDepartment dto) {
        service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
