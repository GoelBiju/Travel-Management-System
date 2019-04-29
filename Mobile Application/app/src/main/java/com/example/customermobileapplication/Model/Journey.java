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

    @SerializedName("currentStopId")
    public Integer currentStopId;

    @SerializedName("stopArrivalDateTime")
    public Date stopArrivalDateTime;

    @SerializedName("stopDepartedDateTime")
    public Date stopDepartedDateTime;

    @SerializedName("coachStatus")
    public String coachStatus;

    @SerializedName("route")
    public Route route;

    @SerializedName("currentStop")
    public Stop currentStop;

    public int getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(int journeyId) {
        this.journeyId = journeyId;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    public Date getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(Date departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public Date getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(Date arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public Integer getCurrentStopId() {
        return currentStopId;
    }

    public void setCurrentStopId(Integer currentStopId) {
        this.currentStopId = currentStopId;
    }

    public Date getStopArrivalDateTime() {
        return stopArrivalDateTime;
    }

    public void setStopArrivalDateTime(Date stopArrivalDateTime) {
        this.stopArrivalDateTime = stopArrivalDateTime;
    }

    public Date getStopDepartedDateTime() {
        return stopDepartedDateTime;
    }

    public void setStopDepartedDateTime(Date stopDepartedDateTime) {
        this.stopDepartedDateTime = stopDepartedDateTime;
    }

    public String getCoachStatus() {
        return coachStatus;
    }

    public void setCoachStatus(String coachStatus) {
        this.coachStatus = coachStatus;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Stop getCurrentStop() {
        return currentStop;
    }

    public void setCurrentStop(Stop currentStop) {
        this.currentStop = currentStop;
    }
}
