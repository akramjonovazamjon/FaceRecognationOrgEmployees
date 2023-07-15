package com.example.lionprintfirstproject.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessControllerEvent{

    @SerializedName("cardReaderKind")
    private int cardReaderKind;

    @SerializedName("frontSerialNo")
    private int frontSerialNo;

    @SerializedName("cardReaderNo")
    private int cardReaderNo;

    @SerializedName("statusValue")
    private int statusValue;

    @SerializedName("majorEventType")
    private int majorEventType;

    @SerializedName("employeeNoString")
    private String employeeNoString;

    @SerializedName("label")
    private String label;

    @SerializedName("deviceName")
    private String deviceName;

    @SerializedName("currentVerifyMode")
    private String currentVerifyMode;

    @SerializedName("serialNo")
    private int serialNo;

    @SerializedName("purePwdVerifyEnable")
    private boolean purePwdVerifyEnable;

    @SerializedName("subEventType")
    private int subEventType;

    @SerializedName("name")
    private String name;

    @SerializedName("verifyNo")
    private int verifyNo;

    @SerializedName("userType")
    private String userType;

    @SerializedName("picturesNumber")
    private int picturesNumber;

    @SerializedName("attendanceStatus")
    private String attendanceStatus;

    @SerializedName("mask")
    private String mask;
}