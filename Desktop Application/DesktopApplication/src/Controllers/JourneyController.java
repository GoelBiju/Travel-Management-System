/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import GUIView.HomePanel;
import GUIView.Journey.JourneyPanel;
import datamodel.Actions.BookingActions;
import datamodel.Actions.JourneyActions;
import datamodel.Journey;
import datamodel.RouteStops;
import datamodel.Stop;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.DefaultListModel;
import utilities.Helpers;

/**
 *
 * @author Goel
 */
public class JourneyController {
    
    private HomePanel viewParent;
    
    private JourneyPanel view;
    
    private JourneyActions journeyModel;
    
    private BookingActions bookingModel;
    
    private Journey journey;
    
    private Queue<RouteStops> routeStopsQueue;
    
    private int totalStops;
    
    private int remainingStop;
    
    public JourneyController(HomePanel parent, JourneyPanel view, Journey journey, Queue<RouteStops> queue) {
        this.viewParent = parent;
        this.view = view;
        
        this.journeyModel = JourneyActions.getInstance();
        this.bookingModel = BookingActions.getInstance();
        
        this.journey = journey;
        this.routeStopsQueue = queue;
        this.totalStops = 0;
        
        this.initialiseController();
    }
    
    private void initialiseController() {
        
        bindMethods();
        
        updateQueueList();
        
        loadInitialState();
    }
    
    private void bindMethods() {
        // Start the journey when pressed.
        this.view.getStartJourneyButton().addActionListener(e -> startJourney());
        
        //
//        this.view.getEndJourneyButton().addActionListener(e -> endJourney());
        
        //
//        this.view.getDepartFromStopButton().addActionListener(e -> departFromStop());
        
        //
//        this.view.getArrivedAtStopButton().addActionListener(e -> arrivedAtStop());
        
        //
//        this.view.getAcceptBookingButton().addActionListener(e -> acceptBooking());
        
        //
//        this.view.getCompleteBookingButton().addActionListener(e -> completeBooking());
    }
    
    private void updateQueueList() {
        
        //
        DefaultListModel<String> queueList = new DefaultListModel<>();
        for (RouteStops routeStop : this.routeStopsQueue) {
            queueList.addElement("Stop No: " + routeStop.getPositionInRoute() + 
                    " - " + routeStop.getStop().getStopName());
        }
        
        //
        this.totalStops = this.routeStopsQueue.size();
        
        //
        this.view.getQueueList().setModel(queueList);
    }   
    
    private void loadInitialState() {
  
        // Disable all buttons except start journey.
        this.view.getEndJourneyButton().setEnabled(false);
        this.view.getDepartFromStopButton().setEnabled(false);
        this.view.getArrivedAtStopButton().setEnabled(false);
        
        this.view.getAcceptBookingButton().setEnabled(false);
        this.view.getCompleteBookingButton().setEnabled(false);
    }
    
    private void startJourney() {
        
        // Disable this button.
        this.view.getStartJourneyButton().setEnabled(false);
        this.view.getEndJourneyButton().setEnabled(true);
        this.view.getDepartFromStopButton().setEnabled(true);
        
        // Load all information for the next stop in the route 
        // from the queue.
        this.loadStopsFromQueue();
        
        // Load booking information relevant to the stop.
        loadStopBookings();
    }
    
    
    
//    private void endJourney() {
//        
//    }
//    
//    private void departFromStop() {
//        
//    }
//    
//    private void arrivedAtStop() {
//        
//    }
//    
//    private void acceptBooking() {
//        
//    }
//    
//    private void completeBooking() {
//        
//    }
    
    private void loadStopsFromQueue() {
        
        // Load the first stop in the list (as the starting stop).
        RouteStops routeStop = this.routeStopsQueue.poll();
        
        if (routeStop != null) {
            Stop stop = routeStop.getStop();
        
            // Update the interface information.
            this.view.getCurrentStopNameLabel().setText(stop.getStopName());
            this.view.getCurrentStopPostcodeLabel().setText(stop.getStopPostcode());
            this.view.getCurrentExpectedArrivalTimeLabel()
                    .setText(Helpers.formatDateTime(routeStop.getExpectedArrivalDateTime()));
            this.view.getCurrentStopPosLabel().setText(Integer.toString(routeStop.getPositionInRoute()));
        
            // Set the selected in the jlist.
            int positionInRoute = routeStop.getPositionInRoute();
            this.view.getQueueList().setSelectedIndex(positionInRoute - 1);
            
            // Load the next stop information.
            if (positionInRoute < this.totalStops) {
                RouteStops nextRouteStop = this.routeStopsQueue.peek();
                Stop nextStop = nextRouteStop.getStop();
                
                // Update the next stop information.
                this.view.getNextStopNameLabel().setText(nextStop.getStopName());
                this.view.getNextStopPostcodeLabel().setText(nextStop.getStopPostcode());
                this.view.getNextExpectedArrivalTimeLabel()
                        .setText(Helpers.formatDateTime(nextRouteStop.getExpectedArrivalDateTime()));
                this.view.getNextStopPosLabel().setText(Integer.toString(nextRouteStop.getPositionInRoute()));
            }
        }
    }
    
    private void loadStopBookings() {
        
        //
        
    }
}
