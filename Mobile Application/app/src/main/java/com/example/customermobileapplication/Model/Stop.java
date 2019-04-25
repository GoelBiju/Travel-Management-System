package com.example.customermobileapplication.Model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class Stop {

    @SerializedName("stopId")
    public int stopId;

    @SerializedName("stopName")
    public String stopName;

    @SerializedName("isStation")
    public boolean isStation;

    @SerializedName("stopPostcode")
    public String stopPostcode;

    @SerializedName("stopLatitude")
    public BigDecimal stopLatitude;

    @SerializedName("stopLongitude")
    public BigDecimal stopLongitude;

    public int getStopId() {
        return stopId;
    }

    public void setStopId(int stopId) {
        this.stopId = stopId;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public boolean isStation() {
        return isStation;
    }

    public void setStation(boolean station) {
        isStation = station;
    }

    public String getStopPostcode() {
        return stopPostcode;
    }

    public void setStopPostcode(String stopPostcode) {
        this.stopPostcode = stopPostcode;
    }

    public BigDecimal getStopLatitude() {
        return stopLatitude;
    }

    public void setStopLatitude(BigDecimal stopLatitude) {
        this.stopLatitude = stopLatitude;
    }

    public BigDecimal getStopLongitude() {
        return stopLongitude;
    }

    public void setStopLongitude(BigDecimal stopLongitude) {
        this.stopLongitude = stopLongitude;
    }
}
