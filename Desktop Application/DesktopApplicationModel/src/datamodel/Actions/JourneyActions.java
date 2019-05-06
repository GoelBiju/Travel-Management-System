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
    
    private static JourneyActions actionsInstance;
    
    private APIConnection apiConnection;
    
    private JourneyActions() {
        
        this.apiConnection = APIConnection.getInstance();
    }
    
    public static JourneyActions getInstance() {
        if (actionsInstance == null) {
            actionsInstance = new JourneyActions();
        }
        return actionsInstance;
    }
    
    public Journey getJourney(Integer journeyId) {
        
        Journey journey = (Journey) apiConnection.getData("journeys", Journey.class, journeyId.toString());
        
        return journey;
    }
    
    public boolean updateJourneyInformation(Journey journey) {
        
        JourneyUpdateBindingModel updateJourney = new JourneyUpdateBindingModel();
        updateJourney.setJourneyId(journey.getJourneyId());
        updateJourney.setCurrentStopId(journey.getCurrentStopId());
        updateJourney.setStopArrivalDateTime(journey.getStopArrivalDateTime());
        updateJourney.setStopDepartedDateTime(journey.getStopDepartedDateTime());
        updateJourney.setCoachStatus(journey.getCoachStatus());
        
        // Check the status and set the coach status;
        int response = this.apiConnection.putData("journeys/" + journey.getJourneyId(), updateJourney);
        System.out.println(response);
        return true;
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
        System.out.println(actions.getJourney(3).getCoachStatus());
    }
}
