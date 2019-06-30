/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

/**
 *
 * @author Goel
 */
public class Route {
    
    private int routeId;
    
    private int departureStationId;
    
    private String departureStation;
    
    private int arrivalStationId;
    
    private String arrivalStation;

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getDepartureStationId() {
        return departureStationId;
    }

    public void setDepartureStationId(int departureStationId) {
        this.departureStationId = departureStationId;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public int getArrivalStationId() {
        return arrivalStationId;
    }

    public void setArrivalStationId(int arrivalStationId) {
        this.arrivalStationId = arrivalStationId;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }
}
