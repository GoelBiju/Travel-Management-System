/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.Actions;

import datamodel.BindingModels.JourneyUpdateBindingModel;
import datamodel.BindingModels.LoginBindingModel;
import datamodel.CoachStatus;
import datamodel.Journey;
import utilities.APIConnection;

/**
 *
 * @author Goel
 */
public class JourneyActions {
    
    private APIConnection apiConnection;
    
    public JourneyActions() {
        
        this.apiConnection = APIConnection.getInstance();
    }
    
    public Journey getJourney(Integer journeyId) {
        
        Journey journey = (Journey) apiConnection.getData("journeys", Journey.class, journeyId.toString());
        
        return journey;
    }
    
    public boolean updateJourneyInformation(Journey journey) {
        
        
        if (coachStatus == CoachStatus.SCHEDULED) {
            
        } else if (coachStatus == CoachStatus.DEPARTED) {
            
        } else if (coachStatus == CoachStatus.ON_ROUTE) {
            
        } else if (coachStatus == CoachStatus.AT_STOP) {
            
        } else if (coachStatus == CoachStatus.BROKEN_DOWN) {
            
        } else if (coachStatus == CoachStatus.ARRIVED) {
            
        } else if (coachStatus == CoachStatus.REPLACEMENT_DEPLOYED) {
            
        } else if (coachStatus == CoachStatus.CANCELLED) {
            
        } else if (coachStatus == CoachStatus.COMPLETE) {
            
        }
        
        // Check the status and set the coach status;
        int response = 
    }
    
    
    public static void main(String[] args) {
        
        JourneyActions actions = new JourneyActions();
        
        //
        LoginBindingModel loginModel = new LoginBindingModel();
        loginModel.setEmployeeID("D1006");
        loginModel.setPassword("testpassword123");
        loginModel.setLoginType("employee");
        
        boolean response = actions.apiConnection.login(loginModel);
        System.out.println(response);      
        
        System.out.println(actions.getJourney(3).getArrivalDateTime());
    }
}
