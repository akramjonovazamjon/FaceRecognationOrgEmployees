package com.example.lionprintfirstproject.dto.employee;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeForCamera {

    @SerializedName("UserInfo")
    private UserInfo userInfo;

    public static EmployeeForCamera of(UserInfo userInfo) {
        return EmployeeForCamera
                .builder()
                .userInfo(userInfo)
                .build();
    }
}