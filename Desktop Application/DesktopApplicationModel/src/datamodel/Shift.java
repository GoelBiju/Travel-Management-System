/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

/**
 *
 * @author Goel
 */
public class Shift {
    
    private int shiftId;
    
    private String employeeId;
    
    private int journeyId;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date startDateTime;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date endDateTime;
    
    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public int getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(int journeyId) {
        this.journeyId = journeyId;
    }
    
    public Date getStartDatetime() {
        return startDateTime;
    }

    public void setStartDatetime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDatetime() {
        return endDateTime;
    }

    public void setEndDatetime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }    
}
