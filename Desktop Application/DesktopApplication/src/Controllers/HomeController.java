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
import datamodel.Booking;
import datamodel.Journey;
import datamodel.RouteStops;
import datamodel.Shift;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.DefaultListModel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import utilities.Helpers;

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
    
    private RouteActions routeModel;
    
    private Shift currentShift;
        
    private Journey currentJourney;
   
    private JourneyController journeyController;
    
    private CoachController coachController;
    
    
    private Queue<RouteStops> routeStopsQueue;
    
    private int customersOnBoard;
    
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
        this.routeModel = RouteActions.getInstance();
        
        this.routeStopsQueue = new LinkedList();
        this.customersOnBoard = 0;
        
        this.initialiseController();
        
        // Set initially the home view and within it the journey panel.
        this.switchPanels(this.viewParent.applicationPanels, this.homeView);
        this.switchPanels(this.homeView.getLayeredPane(), this.journeyView);
        
        // TODO: Load shift and journey information.
        new Thread(new Runnable() {
           public void run() {
               update();
           } 
        }).start();
        
        
        // Load journey controller.
        this.journeyController = new JourneyController(this.homeView, journeyView, this.currentJourney, routeStopsQueue);
        
        // TODO: Load coach controller.
//        this.coachController = new CoachController();
        
    }
    
    private void initialiseController() {

        //
        bindMethods();

        //
        loadCurrentJourneyInformation();
        
        //
        updateRouteStops();
    }
    
    private void bindMethods() {
        // Bind methods
        this.homeView.getJourneyButton().addActionListener(
                e -> switchPanels(homeView.getLayeredPane(), journeyView));
        this.homeView.getCoachButton().addActionListener(
                e -> switchPanels(homeView.getLayeredPane(), coachView));
        this.homeView.getSignOutButton().addActionListener(e -> signOut());
    }
    
    private void loadCurrentJourneyInformation() {
        // Get the journey object.
        this.currentJourney = journeyModel.getJourney(this.currentShift.getJourneyId());
        
        //
        
        
        // Retrieve all bookings for this journey. 
        ArrayList<Booking> bookings = this.bookingModel.getBookingsByJourney(this.currentShift.getJourneyId());
        this.homeView.getTotalBookingsLabel().setText(Integer.toString(bookings.size()));
     
        // Updare the number of customers on-board.
        this.homeView.getNumCustomersLabel().setText(Integer.toString(this.customersOnBoard));
        
        //
        this.homeView.getRemainingStopsLabel().setText(Integer.toString(this.routeStopsQueue.size()));
        
        //
        this.homeView.getDepartureTimeLabel().setText(Helpers.formatDateTime(this.currentJourney.getDepartureDateTime()));
        
        //
        this.homeView.getArrivalTimeLabel().setText(Helpers.formatDateTime(this.currentJourney.getArrivalDateTime()));
    }
    
    private void updateRouteStops() {
        //
        this.routeStopsQueue = new LinkedList();
        
        // Get the route for this journey by route id.
        ArrayList<RouteStops> route = this.routeModel.getRouteStops(this.currentJourney.getRouteId());
        
        if (route != null || route.size() > 0) {
            // Sort the routes.
            Collections.sort(route, (rs1, rs2) -> rs1.getPositionInRoute() - rs2.getPositionInRoute());

            // Enqueue the route stops and add to the list model.
            for (RouteStops routeStop : route) {

                // Get the route stop based on the position.
                System.out.println("Current route stop pos: " + routeStop.getPositionInRoute());

                // Add stop to queue.
                this.routeStopsQueue.add(routeStop);   
            }
            
            // Update the stop count.
            this.homeView.getTotalStopsLabel().setText(Integer.toString(this.routeStopsQueue.size()));
        } else {
            System.out.println("Error fetching route stops.");
        }
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
    
    public void update() {
        // Re-load the current home screen information.
        this.loadCurrentJourneyInformation();
        
        // Update the journey tabs.
        
        // Update the coach information tabs.
        
    }
}
