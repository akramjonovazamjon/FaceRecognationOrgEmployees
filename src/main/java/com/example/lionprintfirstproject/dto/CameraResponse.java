package com.example.lionprintfirstproject.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CameraResponse {

	@SerializedName("subStatusCode")
	private String subStatusCode;

	@SerializedName("statusString")
	private String statusString;

	@SerializedName("statusCode")
	private Integer statusCode;
}