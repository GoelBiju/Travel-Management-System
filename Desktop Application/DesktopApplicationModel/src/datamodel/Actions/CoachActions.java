/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.Actions;

import datamodel.Coach;
import utilities.APIConnection;

/**
 *
 * @author Goel
 */
public class CoachActions {
    
    public APIConnection apiConnection;
    
    public CoachActions() {
        
        apiConnection = APIConnection.getInstance();
    }
    
    public Coach getCoachInformation(String coachId) {
        
        Coach coach = (Coach) apiConnection.getData("coaches", Coach.class, coachId);
        return coach;
    }
}
