/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import GUIView.ApplicationFrame;
import GUIView.Shifts.ShiftsPanel;
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
    
    public ShiftsController(ApplicationFrame parent, ShiftsPanel view, 
            ShiftActions model, String employeeId) {
        this.viewParent = parent;
        this.view = view;
        this.model = model;
        
        this.employeeId = employeeId;
        this.driverShifts = new ArrayList<>();
        
        this.initialiseController();
        this.switchPanels();
    }
    
    private void initialiseController() {
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
 
    
    public void startHomePanel() {
        
        
    }
}
