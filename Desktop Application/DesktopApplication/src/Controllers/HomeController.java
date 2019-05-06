/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import GUIView.ApplicationFrame;
import GUIView.Coach.CoachPanel;
import GUIView.HomePanel;
import GUIView.Journey.JourneyPanel;
import GUIView.LoginPanel;
import datamodel.Actions.BookingActions;
import datamodel.Actions.CoachActions;
import datamodel.Actions.EmployeeActions;
import datamodel.Actions.JourneyActions;
import datamodel.Actions.RouteActions;
import datamodel.Journey;
import datamodel.Shift;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Goel
 */
public class HomeController {
    
    private ApplicationFrame viewParent;
    
    private HomePanel homeView;
    
    private JourneyPanel journeyView;
    
    private CoachPanel coachView;
    
    private JourneyActions journeyModel;
    
    private BookingActions bookingModel;
    
    private EmployeeActions employeeModel;
    
    private Shift currentShift;
        
    private Journey currentJourney;
   
    
    public HomeController(ApplicationFrame parent, HomePanel homeView, Shift shift) {
        this.viewParent = parent;
        this.homeView = homeView;
        this.currentShift = shift;
        System.out.println("Received Journey Id: " + this.currentShift.getJourneyId());
        
        this.journeyView = new JourneyPanel();
        this.coachView = new CoachPanel();

        this.journeyModel = JourneyActions.getInstance();
        this.bookingModel = BookingActions.getInstance();
        this.employeeModel = EmployeeActions.getInstance();
        
        this.initialiseController();
        
        // Set initially the home view and within it the journey panel.
        this.switchPanels(this.viewParent.applicationPanels, this.homeView);
        this.switchPanels(this.homeView.getLayeredPane(), this.journeyView);
        
        // TODO: Load shift and journey information.
        new Thread(new Runnable() {
           public void run() {
               loadCurrentJourney();
           } 
        }).start();
        
        // Load journey controller.
        
        
        // Load coach controller.
    }
    
    private void loadCurrentJourney() {
        // Get the journey object.
        this.currentJourney = journeyModel.getJourney(this.currentShift.getJourneyId());
        
        // Update the journey information in the home view.
        
    }
    
    private void initialiseController() {
        
        // Bind methods
        this.homeView.getJourneyButton().addActionListener(
                e -> switchPanels(homeView.getLayeredPane(), journeyView));
        this.homeView.getCoachButton().addActionListener(
                e -> switchPanels(homeView.getLayeredPane(), coachView));
        this.homeView.getSignOutButton().addActionListener(e -> signOut());
    }
    
    public void switchPanels(JLayeredPane layer, JPanel view) {
        
        // 
        layer.removeAll();
        layer.add(view);
        layer.repaint();
        layer.revalidate();
    }
    
    public void signOut() {
        
        // Alert the driver of the signout button press.
        int result = JOptionPane.showConfirmDialog(null, 
                "Are you sure you would like to sign-out from this shift?", "NationalCoach - Driver", 
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            // Reset the connection information and start the login controller.
            employeeModel.employeeSignOut();
            LoginController login = new LoginController(viewParent, new LoginPanel(), EmployeeActions.getInstance());
        }
    }
}
