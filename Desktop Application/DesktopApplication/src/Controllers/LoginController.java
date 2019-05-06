/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import GUIView.ApplicationFrame;
import GUIView.Coach.CoachView;
import GUIView.LoginPanel;
import GUIView.Shifts.ShiftsPanel;
import datamodel.Actions.EmployeeActions;
import datamodel.Actions.ShiftActions;
import datamodel.BindingModels.LoginBindingModel;
import java.util.HashMap;
import javax.swing.JOptionPane;
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
        
        //this.viewParent.applicationPanels.setVisible(true);
    }
    
    public void loginRequest(){
        
        String employeeId = this.view.getEmployeeId();
        String password = this.view.getPassword();
        
        LoginBindingModel loginModel = new LoginBindingModel();

        // Call the login request as an employee.
        loginModel.setEmployeeID(employeeId);
        loginModel.setPassword(password);
        loginModel.setLoginType("employee");
        
        boolean validated = model.employeeLogin(loginModel);
        if (validated) {
            // TODO: Create the ShiftsController and pass in the relevant variables.  
            ShiftActions shiftActions = ShiftActions.getInstance();
            
            ShiftsController shifts = new ShiftsController(this.viewParent, new ShiftsPanel(), 
                    shiftActions, employeeId);
        } else {
            JOptionPane.showMessageDialog(this.view, "The employee credentials you entered are incorrect.", "NationalCoach - Login", JOptionPane.ERROR_MESSAGE);
        }
    }
}
