/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import java.util.Date;

/**
 *
 * @author Will
 */
public class Booking {
    
    private int bookingReference;
    
    private int customerId;
    
    private Journey journey;
    
    private Stop departingStop;
    
    private Stop arrivalStop;
    
    private Date bookedDateTime;
    
    private int passengersSenior;
    
    private int passengersAdult;
    
    private int passengersTeenager;
    
    private int passengersInfant;
    
    private double amountPaid;
    
    private String status;
}
