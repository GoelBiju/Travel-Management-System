/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import java.math.BigDecimal;

/**
 *
 * @author Goel
 */
public class Stop {
    
    private int stopId;
    
    private String stopName;
    
    private boolean isStation;
    
    private String stopPostcode;
    
    private BigDecimal stopLatitude;
    
    private BigDecimal stopLongitude;

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

    public boolean isIsStation() {
        return isStation;
    }

    public void setIsStation(boolean isStation) {
        this.isStation = isStation;
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
