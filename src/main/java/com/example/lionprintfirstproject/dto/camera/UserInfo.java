package com.example.lionprintfirstproject.dto.camera;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo{

	@SerializedName("Valid")
	private Valid Valid;

	@SerializedName("name")
	private String name;

	@SerializedName("userType")
	private String userType;

	@SerializedName("employeeNo")
	private String employeeNo;
	@SerializedName("gender")
	private String gender;
}