package com.example.lionprintfirstproject.entity;

import com.example.lionprintfirstproject.dto.camera.CameraDateAndEmployeeId;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.*;

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

    public static EmployeeWorkingDay of(Employee employee, LocalDateTime localDateTime) {
        LocalDate localDate = LocalDate.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth());
        LocalTime localTime = LocalTime.of(localDateTime.getHour(), localDateTime.getMinute());
        return EmployeeWorkingDay.builder()
                .workingDate(localDate)
                .arrivalTime(localTime)
                .inWork(true)
                .employee(employee)
                .build();
    }
}
