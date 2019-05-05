/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Map;

/**
 *
 * @author Goel
 */
public enum CoachStatus {
    
//    @JsonProperty("Scheduled")
    SCHEDULED("SCHEDULED"),
    
//    @JsonProperty("Departed")
    DEPARTED("DEPARTED"),
    
//    @JsonProperty("On-route")
    ON_ROUTE("ON_ROUTE"),
    
//    @JsonProperty("At Stop")
    AT_STOP("AT_STOP"),
    
//    @JsonProperty("Arrived")
    ARRIVED("ARRIVED"),
    
//    @JsonProperty("Broken Down")
    BROKEN_DOWN("BROKEN_DOWN"),
    
//    @JsonProperty("Replacement Deployed")
    REPLACEMENT_DEPLOYED("REPLACEMENT_DEPLOYED"),
    
//    @JsonProperty("Cancelled")
    CANCELLED("CANCELLED"),
    
//    @JsonProperty("Complete")
    COMPLETE("COMPLETE");
    
    private String coachStatus;
//    
    CoachStatus(String coachStatus) {
        this.coachStatus = coachStatus;
    }
//    
    public String getStatus() {
        return coachStatus;
    }
    
//    @Override
//    public String toString() {
//        return coachStatus;
//    }
    
//    private CoachStatus() {
//        this.coachStatus = this.name();
//    }
//    
//    @JsonValue
//    final String value() {
//        return this.coachStatus;
//    }
}
