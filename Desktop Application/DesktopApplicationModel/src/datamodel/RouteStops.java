/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import java.util.Date;

/**
 *
 * @author vcastellani
 */
public class RouteStops {
    private int routeId;
    
    private int stopId;
    
    private int positionInRoute;
    
    private Date expectedArrivalDateTime;
    
    private Stop stop;

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getStopId() {
        return stopId;
    }

    public void setStopId(int stopId) {
        this.stopId = stopId;
    }

    public int getPositionInRoute() {
        return positionInRoute;
    }

    public void setPositionInRoute(int positionInRoute) {
        this.positionInRoute = positionInRoute;
    }

    public Date getExpectedArrivalDateTime() {
        return expectedArrivalDateTime;
    }

    public void setExpectedArrivalDateTime(Date expectedArrivalDateTime) {
        this.expectedArrivalDateTime = expectedArrivalDateTime;
    }

    public Stop getStop() {
        return stop;
    }

    public void setStop(Stop stop) {
        this.stop = stop;
    }
    
    
    
    
    
}
