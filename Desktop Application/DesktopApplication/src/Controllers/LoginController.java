/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import GUIView.LoginPanel;
import datamodel.Login;
import utilities.APIConnection;

/**
 *
 * @author adbellas
 */
public class LoginController {
    private LoginPanel loginPanel; //View
    private APIConnection apiConnection;
    private Login loginModel; //Model
    
    public LoginController(){
        apiConnection = new APIConnection();
    }
    
    public void setLoginPanelView(LoginPanel loginPanel){
        this.loginPanel = loginPanel;
    }
}
