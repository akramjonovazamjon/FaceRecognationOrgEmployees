package com.example.lionprintfirstproject.repository;

import com.example.lionprintfirstproject.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Long id);

    Page<Employee> findAllByDepartmentId(Long departmentId, Pageable pageable);

    Page<Employee> findAllByJobId(Long jobId, Pageable pageable);

}
