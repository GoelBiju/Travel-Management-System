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
import datamodel.Booking;
import datamodel.BookingStatus;
import datamodel.CoachStatus;
import datamodel.Journey;
import datamodel.RouteStops;
import datamodel.Stop;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
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
    
    private int remainingStops;
    
    private Stop currentStop;
    
    private ArrayList<Booking> activeBookings;
    
    private ArrayList<Booking> completeBookings;
    
    
    public JourneyController(HomePanel parent, JourneyPanel view, Journey journey, Queue<RouteStops> queue) {
        this.viewParent = parent;
        this.view = view;
        
        this.journeyModel = JourneyActions.getInstance();
        this.bookingModel = BookingActions.getInstance();
        
        this.journey = journey;
        this.routeStopsQueue = queue;
        this.totalStops = 0;
        this.remainingStops = 0;
        
        this.activeBookings = new ArrayList<>();
        this.completeBookings = new ArrayList<>();
        
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
        this.view.getAcceptBookingButton().addActionListener(e -> acceptBooking());
        
        //
        this.view.getDepartFromStopButton().addActionListener(e -> departFromStop());
        
         //
        this.view.getArrivedAtStopButton().addActionListener(e -> arrivedAtStop());

        //
        this.view.getCompleteBookingButton().addActionListener(e -> completeBooking());

        //
        this.view.getEndJourneyButton().addActionListener(e -> endJourney());
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
        
        this.view.getCurrentStopDetailsPanel().setEnabled(false);
        this.view.getNextStopDetailsPanel().setEnabled(false);
    }
    
    private void startJourney() {
        
        // Disable this button.
        this.view.getStartJourneyButton().setEnabled(false);
        this.view.getEndJourneyButton().setEnabled(true);
        this.view.getDepartFromStopButton().setEnabled(true);
        this.view.getAcceptBookingButton().setEnabled(true);
        this.view.getCompleteBookingButton().setEnabled(true);
        
        //
        this.view.getCurrentStopDetailsPanel().setEnabled(true);
        this.view.getNextStopDetailsPanel().setEnabled(true);
        
        // Load all information for the next stop in the route 
        // from the queue.
        this.loadStopsFromQueue();
        
        // Load booking information relevant to the stop.
        loadStopBookings();
        
        // Update journey information and send to API.
        this.journey.setCurrentStopId(this.currentStop.getStopId());
        this.journey.setStopArrivalDateTime(new Date());
        this.journey.setCoachStatus(CoachStatus.AT_STOP);
        
        this.journeyModel.updateJourneyInformation(journey);
    }
    
    
    private void acceptBooking() {
        
        // Get the currently selected booking and change it's status to check-in.
        Booking selectedBooking = this.activeBookings.get(this.view.getActiveBookingsList().getSelectedIndex());
        System.out.println("Selected booking in active list: " + selectedBooking.getBookingReference());
        this.bookingModel.updateBookingStatus(selectedBooking, BookingStatus.CHECKED_IN);
        
        loadStopBookings();
        
        if ((this.activeBookings.size() == 0) && (this.completeBookings.size() == 0)
                && (this.currentStop.getStopId() == this.journey.getRoute().getDepartureStationId()) 
                && (this.journey.getCoachStatus() == CoachStatus.AT_STOP)) {
            // 
            this.journey.setDepartureDateTime(new Date());
            this.journey.setCoachStatus(CoachStatus.DEPARTED);
            
            this.journeyModel.updateJourneyInformation(journey);
        }
    }


//    
    private void departFromStop() {
       
        if (this.activeBookings.size() == 0) {
        
            //
            this.view.getDepartFromStopButton().setEnabled(false);
            this.view.getArrivedAtStopButton().setEnabled(true);

            //
            this.view.getAcceptBookingButton().setEnabled(false);
            this.view.getCompleteBookingButton().setEnabled(false);

            //
            this.journey.setStopArrivalDateTime(null);
            this.journey.setStopDepartedDateTime(new Date());
            this.journey.setCoachStatus(CoachStatus.ON_ROUTE);
            this.journeyModel.updateJourneyInformation(journey);
            
        
            //
            this.loadStopsFromQueue();
        } else {
            JOptionPane.showMessageDialog(this.view, "Please ensure that all passengers"
                    + " boarding at this stop have been accepted/checked-in.",
                        "NationalCoach - Journey", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void arrivedAtStop() {
        
        this.view.getDepartFromStopButton().setEnabled(true);
        this.view.getArrivedAtStopButton().setEnabled(false);
        
        //
        this.view.getAcceptBookingButton().setEnabled(true);
        this.view.getCompleteBookingButton().setEnabled(true);
        
        //
        this.journey.setCurrentStop(this.currentStop);
        this.journey.setStopArrivalDateTime(new Date());
        this.journey.setStopDepartedDateTime(null);
        this.journey.setCoachStatus(CoachStatus.AT_STOP);
        this.journeyModel.updateJourneyInformation(journey);     
        
        if (this.routeStopsQueue.peek() == null) {
            //
            this.view.getDepartFromStopButton().setEnabled(false);
            this.view.getArrivedAtStopButton().setEnabled(false);
            
            // 
            this.journey.setArrivalDateTime(new Date());
            this.journey.setDepartureDateTime(null);
            this.journey.setCoachStatus(CoachStatus.ARRIVED);
            
            this.journeyModel.updateJourneyInformation(journey);
        }
    }
    
    private void completeBooking() {
        
        // Get the currently selected booking and change it's status to check-in.
        Booking selectedBooking = this.completeBookings.get(this.view.getCompletedBookingsList().getSelectedIndex());
        System.out.println("Selected booking in completed list: " + selectedBooking.getBookingReference());
        this.bookingModel.updateBookingStatus(selectedBooking, BookingStatus.COMPLETE);
        
        loadStopBookings();
    }
    
    private void endJourney() {
        
        //
        if (this.completeBookings.size() > 0) {
            JOptionPane.showMessageDialog(this.view, "Please ensure that all passengers"
                    + " have left the coach and have completed their booking.",
                        "NationalCoach - Journey", JOptionPane.ERROR_MESSAGE);
        } else {
            this.journey.setCoachStatus(CoachStatus.COMPLETE);
            this.journeyModel.updateJourneyInformation(journey);
            
            this.view.setEnabled(false);
            
            JOptionPane.showMessageDialog(this.view, "You can now sign-out from your shift.",
                        "NationalCoach - Journey", JOptionPane.OK_OPTION);
        }
    }
    
    private void loadStopsFromQueue() {
        
        // Load the first stop in the list (as the starting stop).
        RouteStops routeStop = this.routeStopsQueue.poll();
        
        if (routeStop != null) {
            Stop stop = routeStop.getStop();
            this.currentStop = stop;
            
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
            } else {
                this.view.getNextStopNameLabel().setText("None");
                this.view.getNextStopPostcodeLabel().setText("None");
                this.view.getNextExpectedArrivalTimeLabel()
                        .setText("None");
                this.view.getNextStopPosLabel().setText("None");
                this.view.getNextStopDetailsPanel().setEnabled(false);
            }
        } else {
            System.out.println("The next route stop from the queue returned null.");
        }
    }
    
    /**
     * Get all the bookings for the journey and load them
     * according to if they are active or complete at the current stop.
     */
    private void loadStopBookings() {
        
        //
        ArrayList<Booking> bookings = this.bookingModel.getBookingsByJourney(this.journey.getJourneyId());
        
        DefaultListModel<String> activeBookingsModel = new DefaultListModel<>();
        DefaultListModel<String> completeBookingModel = new DefaultListModel<>();
        for (Booking booking : bookings) {
            System.out.println("Booking status: " + booking.getStatus() + ", Departing Stop: " + booking.getDepartingStop().getStopId());
            System.out.println("Current stop: " + this.currentStop.getStopId());
            
            if (booking.getStatus().equals("Confirmed") && (booking.getDepartingStop().getStopId() == this.currentStop.getStopId())) {
                System.out.println("Check-in at stop: " + booking.getBookingReference());
                
                int totalPassengersInBooking = booking.getPassengersAdult() + booking.getPassengersSenior() +
                        booking.getPassengersInfant() + booking.getPassengersChildren();
                
                activeBookingsModel.addElement(booking.getBookingReference() + " - Travelling to: " 
                        + booking.getArrivalStop().getStopName() + ", Total Passengers: " + 
                        totalPassengersInBooking);
                
                this.activeBookings.add(booking);
                System.out.println("Added check-in booking: " + booking.getBookingReference());
            } else if (booking.getStatus().equals("Checked-in") && (booking.getArrivalStop().getStopId() == this.currentStop.getStopId())) {
                System.out.println("Complete at stop: " + booking.getBookingReference());
                
                int totalPassengersInBooking = booking.getPassengersAdult() + booking.getPassengersSenior() +
                        booking.getPassengersInfant() + booking.getPassengersChildren();
                
                completeBookingModel.addElement(booking.getBookingReference() + " - Num. passengers leaving: " + totalPassengersInBooking);
                
                this.completeBookings.add(booking);
                System.out.println("Added complete booking: " + booking.getBookingReference());
            }
        }
        this.view.getActiveBookingsList().setModel(activeBookingsModel);
        this.view.getCompletedBookingsList().setModel(completeBookingModel);
    }
}
