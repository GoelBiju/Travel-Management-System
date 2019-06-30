package com.example.customermobileapplication.Model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class RouteStop {

    @SerializedName("routeId")
    public int routeId;

    @SerializedName("stopId")
    public int stopId;

    @SerializedName("positionInRoute")
    public int positionInRoute;

    @SerializedName("expectedArrivalDateTime")
    public Date expectedArrivalDateTime;

    @SerializedName("stop")
    public Stop stop;

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getStopId() {
        return stopId;
    }

    public void setStopId(int stopId) {
        this.stopId = stopId;
    }

    public int getPositionInRoute() {
        return positionInRoute;
    }

    public void setPositionInRoute(int positionInRoute) {
        this.positionInRoute = positionInRoute;
    }

    public Date getExpectedArrivalDateTime() {
        return expectedArrivalDateTime;
    }

    public void setExpectedArrivalDateTime(Date expectedArrivalDateTime) {
        this.expectedArrivalDateTime = expectedArrivalDateTime;
    }

    public Stop getStop() {
        return stop;
    }

    public void setStop(Stop stop) {
        this.stop = stop;
    }
}
