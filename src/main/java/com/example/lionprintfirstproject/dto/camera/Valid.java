package com.example.lionprintfirstproject.dto.camera;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Valid {

    @SerializedName("enable")
    private Boolean enable;

    @SerializedName("beginTime")
    public String beginTime;

    @SerializedName("endTime")
    public String endTime;
}