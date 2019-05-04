/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.BindingModels;

import java.util.Date;

/**
 *
 * @author Goel
 */
public class JourneyUpdateBindingModel {
    
    private int journeyId;
    
    private int currentStopId;
    
    private Date stopArrivalDateTime;
    
    private Date stopDepartedDateTime;
    
    private String coachStatus;

    public int getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(int journeyId) {
        this.journeyId = journeyId;
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
}
