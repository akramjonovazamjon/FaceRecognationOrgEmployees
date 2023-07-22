package com.example.lionprintfirstproject.dto.camera;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class CameraDateAndEmployeeId {
    LocalDateTime dateTime;
    Long employeeId;
}
