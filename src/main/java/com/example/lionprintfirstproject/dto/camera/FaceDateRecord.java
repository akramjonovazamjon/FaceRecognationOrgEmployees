package com.example.lionprintfirstproject.dto.camera;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FaceDateRecord {

	@SerializedName("FPID")
	private String fPID;

	@SerializedName("faceLibType")
	private String faceLibType;

	@SerializedName("FDID")
	private String fDID;
}