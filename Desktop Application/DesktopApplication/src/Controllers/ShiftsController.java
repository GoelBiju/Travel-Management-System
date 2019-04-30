/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import GUIView.Shifts.ShiftsPanel;

/**
 *
 * @author Goel
 */
public class ShiftsController {
    
    private ShiftsPanel shiftsPanel;
    
    
    
    public ShiftsController() {
        
        // Load data from the API.
        
    }
    
    public void setPanel(ShiftsPanel panel) {
        this.shiftsPanel = panel;
    }
}
