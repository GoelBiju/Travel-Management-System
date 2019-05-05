/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import java.util.Date;

/**
 *
 * @author Goel
 */
public class Journey {
    
    private int journeyId;
    
    private int routeId;   
    
    private int coachId;
    
    private Date departureDateTime;
    
    private Date arrivalDateTime;
    
    private int currentStopId;
    
    private Date stopArrivalDateTime;
    
    private Date stopDepartedDateTime;
    
    private String coachStatus;
    //private CoachStatus coachStatus;
    
    private Route route;
    
    private Stop currentStop;

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

    public int getCurrentStopId() {
        return currentStopId;
    }

    public void setCurrentStopId(int currentStopId) {
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

//    public CoachStatus getCoachStatus() {
//        return coachStatus;
//    }
//
//    public void setCoachStatus(CoachStatus coachStatus) {
//        this.coachStatus = coachStatus;
//    }

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
