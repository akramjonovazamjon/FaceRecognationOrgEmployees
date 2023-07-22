package com.example.lionprintfirstproject.entity;

import com.example.lionprintfirstproject.dto.department.UpdateDepartment;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String info;
    @ManyToOne
    private Branch branch;

    public void update(UpdateDepartment dto) {
        setName(dto.name());
        setInfo(dto.info());
    }
}
