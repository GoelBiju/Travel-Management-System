/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.Actions;

import com.fasterxml.jackson.core.type.TypeReference;
import datamodel.BindingModels.LoginBindingModel;
import datamodel.Coach;
import java.util.ArrayList;
import java.util.List;
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
    
    /**
     * 
     * @param coachId
     * @return 
     */
    public Coach getCoach(Integer coachId) {
        
        Coach coach = (Coach) apiConnection.getData("coaches", Coach.class, coachId.toString());
        
        return coach;
    }
    
    /**
     * 
     * @return 
     */
    public ArrayList<Coach> getCoaches() {
     
        ArrayList<Coach> coaches = (ArrayList<Coach>) apiConnection.getListData("coaches", new TypeReference<List<Coach>>() {});
        
        return coaches;
    }
    
    public static void main(String[] args) {
        
        CoachActions actions = new CoachActions();
        
        //
        LoginBindingModel loginModel = new LoginBindingModel();
        loginModel.setEmployeeID("D1006");
        loginModel.setPassword("testpassword123");
        loginModel.setLoginType("employee");
        
        int response = actions.apiConnection.login(loginModel);
        System.out.println(response);
        
        //
        ArrayList<Coach> coaches = actions.getCoaches();
        System.out.println(coaches.get(0).toString());
        
        //
        Coach coach = actions.getCoach(coaches.get(0).getCoachId());
        System.out.println(coach.getCoachCapacity());
    }
}

