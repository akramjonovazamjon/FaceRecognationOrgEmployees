package com.example.lionprintfirstproject.service;

import com.example.lionprintfirstproject.dto.department.CreateDepartment;
import com.example.lionprintfirstproject.dto.department.UpdateDepartment;
import com.example.lionprintfirstproject.entity.Department;
import com.example.lionprintfirstproject.exception.department.DepartmentExistByNameException;
import com.example.lionprintfirstproject.exception.department.DepartmentNotFoundByIdException;
import com.example.lionprintfirstproject.mapper.DepartmentMapper;
import com.example.lionprintfirstproject.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository repository;
    private final DepartmentMapper mapper;

    public Department create(CreateDepartment dto) {

        if (repository.existsByName(dto.name())) {
            throw new DepartmentExistByNameException(dto.name());
        }

        Department department = mapper.asNewDepartment(dto);

        return repository.save(department);
    }

    public void update(Long id, UpdateDepartment dto) {
        if (repository.existsByNameAndIdNot(dto.name(), id)) {
            throw new DepartmentExistByNameException(dto.name());
        }

        Department department = repository.findById(id).orElseThrow(() -> new DepartmentNotFoundByIdException(id));

        department.update(dto);

        repository.save(department);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Department getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DepartmentNotFoundByIdException(id));
    }

    public List<Department> getAll(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }


}
