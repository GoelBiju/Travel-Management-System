package com.example.customermobileapplication.Model;

import com.google.gson.annotations.SerializedName;

public class Route {

    @SerializedName("routeId")
    public int routeId;

    @SerializedName("departureStationId")
    public int departureStationId;

    @SerializedName("departureStation")
    public String departureStation;

    @SerializedName("arrivalStationId")
    public int arrivalStationId;

    @SerializedName("arrivalStation")
    public String arrivalStation;

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getDepartureStationId() {
        return departureStationId;
    }

    public void setDepartureStationId(int departureStationId) {
        this.departureStationId = departureStationId;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public int getArrivalStationId() {
        return arrivalStationId;
    }

    public void setArrivalStationId(int arrivalStationId) {
        this.arrivalStationId = arrivalStationId;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }
}
