/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import GUIView.CoachView;
import datamodel.Coach;
import utilities.APIConnection;

/**
 *
 * @author vcastellani
 */
public class CoachController {
    private CoachView coachview; //View
    private Coach coachDataModel;//Model 
    private APIConnection apiConnection;
    
    public CoachController(){
        apiConnection = APIConnection.getInstance();
    }
    
    public Coach getCoachData (Coach coachDatamodel){
        coachDataModel = apiConnection.getCoachData("6");
        return coachDataModel;
    }
    
    public void postCoachData(Coach dataModel){
        this.coachDataModel = dataModel;
        apiConnection.putData("coaches", dataModel);
    }
    
}
