package com.example.lionprintfirstproject.repository;

import com.example.lionprintfirstproject.entity.EmployeeWorkingDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeWorkingDayRepository extends JpaRepository<EmployeeWorkingDay, Long> {

    Optional<EmployeeWorkingDay> findByWorkingDateAndEmployeeIdAndInWork(LocalDate workingDate, Long employeeId, boolean inWork);

    long countByWorkingDateAndInWork(LocalDate workingDate, boolean inWork);

    List<EmployeeWorkingDay> findByWorkingDateAndEmployeeIdOrderByArrivalTime(LocalDate workingDate, Long employeeId);

}
