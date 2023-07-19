package com.example.lionprintfirstproject.repository;

import com.example.lionprintfirstproject.entity.EmployeeWorkingDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeWorkingDayRepository extends JpaRepository<EmployeeWorkingDay, Long> {

    Optional<EmployeeWorkingDay> findByWorkingDateAndEmployeeIdAndInWork(LocalDate workingDate, Long employeeId, boolean inWork);

    @Query("SELECT COUNT(DISTINCT e.employee) FROM EmployeeWorkingDay e WHERE e.workingDate = :workingDate")
    long countByWorkingDate(LocalDate workingDate);

    List<EmployeeWorkingDay> findByWorkingDateAndEmployeeIdOrderByArrivalTime(LocalDate workingDate, Long employeeId);

}
