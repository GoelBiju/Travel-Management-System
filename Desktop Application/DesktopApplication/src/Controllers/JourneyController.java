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
import datamodel.RouteStops;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.DefaultListModel;

/**
 *
 * @author Goel
 */
public class JourneyController {
    
    private HomePanel viewParent;
    
    private JourneyPanel view;
    
    private JourneyActions journeyModel;
    
    private BookingActions bookingModel;
    
    private Queue<RouteStops> routeStopsQueue;
    
    public JourneyController(HomePanel parent, JourneyPanel view, Queue<RouteStops> queue) {
        this.viewParent = parent;
        this.view = view;
        
        this.journeyModel = JourneyActions.getInstance();
        this.bookingModel = BookingActions.getInstance();
        
        this.routeStopsQueue = queue;
    
        this.initialiseController();
    }
    
    private void initialiseController() {
        
        updateQueueList();
    }
    
    private void updateQueueList() {
        
        //
        DefaultListModel<String> queueList = new DefaultListModel<>();
        for (RouteStops routeStop : this.routeStopsQueue) {
            queueList.addElement("Stop No: " + routeStop.getPositionInRoute() + 
                    " - " + routeStop.getStop().getStopName());
        }
        
        //
        this.view.getQueueList().setModel(queueList);
    }   
}
