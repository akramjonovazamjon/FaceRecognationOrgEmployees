package com.example.lionprintfirstproject.dto.camera;

import com.example.lionprintfirstproject.dto.camera.UserInfo;
import com.google.gson.annotations.SerializedName;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeForCamera {

    @SerializedName("UserInfo")
    private UserInfo UserInfo;

    public static EmployeeForCamera of(com.example.lionprintfirstproject.dto.camera.UserInfo userInfo) {
        return EmployeeForCamera
                .builder()
                .UserInfo(userInfo)
                .build();
    }
}