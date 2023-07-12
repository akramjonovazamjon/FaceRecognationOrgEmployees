package com.example.lionprintfirstproject.service;

import com.example.lionprintfirstproject.dto.department.CreateDepartment;
import com.example.lionprintfirstproject.dto.department.DepartmentFilter;
import com.example.lionprintfirstproject.dto.department.UpdateDepartment;
import com.example.lionprintfirstproject.entity.Department;
import com.example.lionprintfirstproject.exception.department.DepartmentExistByNameException;
import com.example.lionprintfirstproject.exception.department.DepartmentNotFoundByIdException;
import com.example.lionprintfirstproject.mapper.DepartmentMapper;
import com.example.lionprintfirstproject.repository.DepartmentRepository;
import com.example.lionprintfirstproject.util.QueryUtils;
import jakarta.persistence.criteria.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    public List<Department> getAll(DepartmentFilter filter, Pageable pageable) {
        Specification<Department> specification = buildDepartmentSpecification(filter);
        return repository.findAll(specification, pageable).getContent();
    }

    private Specification<Department> buildDepartmentSpecification(DepartmentFilter filter) {
        Specification<Department> spec = Specification.where(null);

        if (StringUtils.hasText(filter.name()))
            spec = spec.and(QueryUtils.like(root -> root.get("name"), filter.name()));

        return spec;
    }


}
