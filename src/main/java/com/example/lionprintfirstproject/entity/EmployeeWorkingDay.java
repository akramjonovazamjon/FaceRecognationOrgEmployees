package com.example.lionprintfirstproject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "employees_working_day")
public class EmployeeWorkingDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "working_date")
    private LocalDate workingDate;
    @Column(name = "arrival_time")
    private LocalTime arrivalTime;
    @Column(name = "exit_time")
    private LocalTime exitTime;
    @Column(name = "in_work")
    private boolean inWork;
    @Column(name = "working_hour")
    private Duration workingHour;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public static EmployeeWorkingDay of(Employee employee) {
        return EmployeeWorkingDay.builder()
                .workingDate(LocalDate.now())
                .arrivalTime(LocalTime.now())
                .inWork(true)
                .employee(employee)
                .build();
    }
}
