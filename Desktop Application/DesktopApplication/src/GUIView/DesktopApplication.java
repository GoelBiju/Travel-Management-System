/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIView;

import Controllers.LoginController;
import datamodel.Actions.EmployeeActions;

/**
 *
 * @author adbellas
 */
public class DesktopApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //
        ApplicationFrame application = new ApplicationFrame();
        
        // Instantiate the view.
        LoginPanel view = new LoginPanel();
        
        // Instantiate the model for the controller.
        EmployeeActions model = EmployeeActions.getInstance();
        
        // Instantiate the controller.
        LoginController loginController = new LoginController(application, view, model);
        
        // Set view to visible.
        application.setVisible(true);
    }
}
