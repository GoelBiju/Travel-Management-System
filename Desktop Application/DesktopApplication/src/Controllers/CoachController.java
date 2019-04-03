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
    private Coach coachDatamodel;//Model 
    private APIConnection apiConnection;
    
    public CoachController(){
        apiConnection = new APIConnection();
    }
    
    public void getCoachData (Coach coachDatamodel){
        this.coachDatamodel = coachDatamodel;
        apiConnection.getData("coaches", Coach.class, 1);
    }
    
    public void postCoachData(Coach coachDatamodel){
        this.coachDatamodel = coachDatamodel;
        apiConnection.putData("coaches", coachDatamodel);
    }
    
}
