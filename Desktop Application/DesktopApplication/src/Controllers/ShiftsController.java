/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import GUIView.ApplicationFrame;
import GUIView.HomePanel;
import GUIView.LoginPanel;
import GUIView.Shifts.ShiftsPanel;
import datamodel.Actions.EmployeeActions;
import datamodel.Actions.ShiftActions;
import datamodel.Shift;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JLayeredPane;

/**
 *
 * @author Goel
 */
public class ShiftsController {
    
    private ApplicationFrame viewParent;
    private ShiftsPanel view;
    private ShiftActions model;
    
    private String employeeId;
    private ArrayList<Shift> driverShifts;
    
    private EmployeeActions employeeActions;
    
    public ShiftsController(ApplicationFrame parent, ShiftsPanel view, 
            ShiftActions model, String employeeId) {
        this.viewParent = parent;
        this.view = view;
        this.model = model;
        
        this.employeeId = employeeId;
        this.driverShifts = new ArrayList<>();
        
        this.employeeActions = EmployeeActions.getInstance();
        
        this.initialiseController();
        this.switchPanels();
    }
    
    private void initialiseController() {
        // Bind buttons to methods.
        this.view.getStartShiftButton().addActionListener(e -> startShift());
        this.view.getSignOutButton().addActionListener(e -> signOut());
        
        this.loadDriverShifts();
    }
    
    public void switchPanels() {
        this.viewParent.applicationPanels.removeAll();
        this.viewParent.applicationPanels.add(view);
        this.viewParent.applicationPanels.repaint();
        this.viewParent.applicationPanels.revalidate();
    }
    
    public void loadDriverShifts() {
        
        // Get driver shifts from API.
        System.out.println("Getting all driver shifts for employee id: " + this.employeeId);
        this.driverShifts = model.getDriverShifts(this.employeeId);
        System.out.println(this.driverShifts.get(0).getStartDatetime());
        
        // 
        DefaultListModel<String> shiftsModel = new DefaultListModel<>();
        for (Shift shift : this.driverShifts) {
            shiftsModel.addElement("Journey No.: " + shift.getJourneyId() + 
                    " - Shift Start Time: " + shift.getStartDatetime() + 
                    ", End Time: " + shift.getEndDatetime());
        }
        this.view.setShiftsListModel(shiftsModel);
    }
 
    public void startShift() {
        
        // Get the shift id.
        try {
            System.out.println("Selected item index: " + this.view.getSelectedShiftIndex());
            Shift selectedShift = this.driverShifts.get(this.view.getSelectedShiftIndex());
            
            // Start the home controller.
            HomeController home = new HomeController(viewParent, new HomePanel(), selectedShift);
            
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println("Could not retrieve the shift record.");
        }
    }
    
    public void signOut() {
        
        // Reset the connection information and start the login controller.
        employeeActions.employeeSignOut();
        LoginController login = new LoginController(viewParent, new LoginPanel(), EmployeeActions.getInstance());
    }
}
