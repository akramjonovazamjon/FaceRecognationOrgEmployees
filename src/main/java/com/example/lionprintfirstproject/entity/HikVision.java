package com.example.lionprintfirstproject.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class HikVision {
    @Id
    private String ipAddress;
    @ManyToOne
    Branch branch;
}
