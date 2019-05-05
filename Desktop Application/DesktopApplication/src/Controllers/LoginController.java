/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import GUIView.ApplicationFrame;
import GUIView.Coach.CoachView;
import GUIView.LoginPanel;
import datamodel.Actions.EmployeeActions;
import datamodel.BindingModels.LoginBindingModel;
import java.util.HashMap;
import utilities.APIConnection;

/**
 *
 * @author adbellas
 */
public class LoginController {
    
    private ApplicationFrame viewParent;
    private LoginPanel view; //View
    private EmployeeActions model; 
//    private LoginBindingModel loginModel; //Model

    public LoginController(ApplicationFrame parent, LoginPanel view, EmployeeActions model){
        this.viewParent = parent;
        this.view = view;
        this.model = model;
        
        this.initialiseController();
        this.switchPanels();
    }
    
    private void initialiseController() {
        this.view.getLoginBtn().addActionListener(e -> loginRequest());
    }
    
    private void switchPanels() {
        this.viewParent.applicationPanels.removeAll();
        this.viewParent.applicationPanels.add(view);
        this.viewParent.applicationPanels.repaint();
        this.viewParent.applicationPanels.revalidate();
        
        this.viewParent.applicationPanels.setVisible(true);
    }
    
    public void loginRequest(){
        
        LoginBindingModel loginModel = new LoginBindingModel();

        // Call the login request as an employee.
        loginModel.setEmployeeID(this.view.getEmployeeId());
        loginModel.setPassword(this.view.getPassword());
        loginModel.setLoginType("employee");
        
        boolean response = model.employeeLogin(loginModel);
        System.out.println(response);
        
        // TODO: Create the ShiftsController and pass in the relevant variables.
        
    }
}
