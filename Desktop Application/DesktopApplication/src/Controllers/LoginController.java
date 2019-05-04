/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import GUIView.Coach.CoachView;
import GUIView.LoginScreen;
import datamodel.BindingModels.LoginBindingModel;
import java.util.HashMap;
import utilities.APIConnection;

/**
 *
 * @author adbellas
 */
public class LoginController {
    private LoginScreen loginScreen; //View
    private APIConnection apiConnection;
    private LoginBindingModel loginModel; //Model
    
    public LoginController(){
        apiConnection = APIConnection.getInstance();
    }
    
    public boolean loginRequest(LoginBindingModel loginModel){
        // this.loginModel = loginModel;
        
        // Call the login request as an employee.
        loginModel.setLoginType("employee");
        boolean responseCode = apiConnection.login(loginModel);
        //HashMap<String, Object> loginResponse = apiConnection.PostData("employees/login", loginModel);
        //Integer responseCode = (Integer) loginResponse.get("responseCode");
//        
//        if (responseCode >= 200 && responseCode < 300){
//            return true;
//        }
        return responseCode;
    }
    
    public void showCoachView(){
        CoachView coachView = new CoachView();
        coachView.setVisible(true);
        
      //  loginScreen.setVisible(false);
    }
}
