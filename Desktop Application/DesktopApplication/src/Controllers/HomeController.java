/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import GUIView.ApplicationFrame;
import GUIView.HomePanel;
import datamodel.Actions.BookingActions;
import datamodel.Actions.CoachActions;
import datamodel.Actions.JourneyActions;
import datamodel.Actions.RouteActions;
import datamodel.Shift;

/**
 *
 * @author Goel
 */
public class HomeController {
    
    private ApplicationFrame viewParent;
    
    private HomePanel view;
    
    private JourneyActions journeyModel;
    
    private BookingActions bookingModel;
    
    private Shift currentShift;
    
    public HomeController(ApplicationFrame parent, HomePanel view, Shift shift) {
        this.viewParent = parent;
        this.view = view;
    }
    
    private void initialiseController() {
        
        // Bind methods
    }
}
