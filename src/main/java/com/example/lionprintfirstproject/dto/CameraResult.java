package com.example.lionprintfirstproject.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CameraResult{

    @SerializedName("dateTime")
    private String dateTime;

    @SerializedName("portNo")
    private int portNo;

    @SerializedName("protocol")
    private String protocol;

    @SerializedName("macAddress")
    private String macAddress;

    @SerializedName("eventState")
    private String eventState;

    @SerializedName("ipAddress")
    private String ipAddress;

    @SerializedName("eventDescription")
    private String eventDescription;

    @SerializedName("activePostCount")
    private int activePostCount;

    @SerializedName("eventType")
    private String eventType;

    @SerializedName("channelID")
    private int channelID;

    @SerializedName("AccessControllerEvent")
    private AccessControllerEvent accessControllerEvent;
}