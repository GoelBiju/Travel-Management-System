/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import utilities.APIConnection;

/**
 *
 * @author adbellas
 */
public class StartJourney {
    private APIConnection apiConnection;
    private Employee employee;
    
    public StartJourney(){
        this.apiConnection = APIConnection.getInstance();
        this.employee = new Employee();
    }
    
    public void setJourneyStarted(){
        //apiConnection.putData();
    }
    
}
