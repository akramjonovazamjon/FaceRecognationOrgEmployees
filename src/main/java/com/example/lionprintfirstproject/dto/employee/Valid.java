package com.example.lionprintfirstproject.dto.employee;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Valid {

    @SerializedName("enable")
    private Boolean enable;

    @SerializedName("beginTime")
    private LocalDateTime beginTime;

    @SerializedName("endTime")
    private LocalDateTime endTime;
}