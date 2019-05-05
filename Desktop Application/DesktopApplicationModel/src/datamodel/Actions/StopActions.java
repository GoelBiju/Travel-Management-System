/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.Actions;

import datamodel.Stop;

import utilities.APIConnection;
import datamodel.BindingModels.LoginBindingModel;

/**
 *
 * @author Goel
 */
public class StopActions {
    
    private APIConnection apiConnection;
    
    public StopActions() {
        apiConnection = APIConnection.getInstance();
    }
        
    public Stop getStops(Integer stopId){
        
        Stop stop = (Stop) apiConnection.getData("stops", Stop.class, stopId.toString());
        return stop;
    }    
         

    public static void main(String[] args){
        StopActions actions = new StopActions();

        LoginBindingModel loginModel = new LoginBindingModel();
        loginModel.setEmployeeID("D1006");
        loginModel.setPassword("testpassword123");
        loginModel.setLoginType("employee");
        
        boolean response = actions.apiConnection.login(loginModel);
        System.out.println(response); 
        
        Stop stops = actions.getStops(4);
        System.out.println(stops.getStopName());
    }
}
