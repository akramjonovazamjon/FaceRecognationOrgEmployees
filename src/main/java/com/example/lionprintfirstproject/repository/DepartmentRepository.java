package com.example.lionprintfirstproject.repository;

import com.example.lionprintfirstproject.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>, JpaSpecificationExecutor<Department> {

    boolean existsByName(String name);
    Optional<Department> findByNameAndBranchId(String name, Long id);

    boolean existsByNameAndBranchIdAndIdNot(String name, Long branchId, Long id);

}
