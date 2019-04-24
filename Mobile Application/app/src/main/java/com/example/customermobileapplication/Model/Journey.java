package com.example.customermobileapplication.Model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Journey {

    @SerializedName("journeyId")
    public int journeyId;

    @SerializedName("routeId")
    public int routeId;

    @SerializedName("coachId")
    public int coachId;

    @SerializedName("departureDateTime")
    public Date departureDateTime;

    @SerializedName("arrivalDateTime")
    public Date arrivalDateTime;

    @SerializedName("currentStop")
    public Integer currentStop;

    @SerializedName("stopArrivalDateTime")
    public Date stopArrivalDateTime;

    @SerializedName("stopDepartedDateTime")
    public Date stopDepartedDateTime;

    @SerializedName("coachStatus")
    public String coachStatus;
}
