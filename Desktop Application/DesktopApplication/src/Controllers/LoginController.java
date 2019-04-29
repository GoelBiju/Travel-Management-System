/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import GUIView.CoachView;
import GUIView.LoginScreen;
import datamodel.Login;
import java.util.HashMap;
import utilities.APIConnection;

/**
 *
 * @author adbellas
 */
public class LoginController {
    private LoginScreen loginScreen; //View
    private APIConnection apiConnection;
    private Login loginModel; //Model
    
    public LoginController(){
        apiConnection = new APIConnection();
    }
    
    public boolean loginRequest(Login loginModel){
        this.loginModel = loginModel;
        
        loginModel = apiConnection.login("token");
        //HashMap<String, Object> loginResponse = apiConnection.PostData("employees/login", loginModel);
        
        
        //Integer responseCode = (Integer) loginResponse.get("responseCode");
        
        //if (responseCode >= 200 && responseCode < 300){
        //    return true;
        //}
        //return false;
    }
    
    public void showCoachView(){
        CoachView coachView = new CoachView();
        coachView.setVisible(true);
        
        loginScreen.setVisible(false);
    }
}
