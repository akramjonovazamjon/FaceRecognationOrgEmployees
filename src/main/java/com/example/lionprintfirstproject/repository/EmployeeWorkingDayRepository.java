package com.example.lionprintfirstproject.repository;

import com.example.lionprintfirstproject.entity.EmployeeWorkingDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface EmployeeWorkingDayRepository extends JpaRepository<EmployeeWorkingDay, Long> {

    Optional<EmployeeWorkingDay> findByWorkingDateAndEmployeeId(LocalDate workingDate, Long employeeId);

}
