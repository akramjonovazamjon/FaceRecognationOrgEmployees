package com.example.lionprintfirstproject.dto.camera;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
@AllArgsConstructor
public class EmployeeForCameraImg {
    @SerializedName("FaceDateRecord")
    public FaceDateRecord faceDateRecord;
    @SerializedName("img")
    public MultipartFile img;
}
