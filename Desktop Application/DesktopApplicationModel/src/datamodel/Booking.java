/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date bookedDateTime;
    
    private int passengersSenior;
    
    private int passengersAdult;
    
    private int passengersTeenager;
    
    private int passengersInfant;
    
    private double amountPaid;
    
    private String status;

    public int getBookingReference() {
        return bookingReference;
    }

    public void setBookingReference(int bookingReference) {
        this.bookingReference = bookingReference;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Journey getJourney() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }

    public Stop getDepartingStop() {
        return departingStop;
    }

    public void setDepartingStop(Stop departingStop) {
        this.departingStop = departingStop;
    }

    public Stop getArrivalStop() {
        return arrivalStop;
    }

    public void setArrivalStop(Stop arrivalStop) {
        this.arrivalStop = arrivalStop;
    }

    public Date getBookedDateTime() {
        return bookedDateTime;
    }

    public void setBookedDateTime(Date bookedDateTime) {
        this.bookedDateTime = bookedDateTime;
    }

    public int getPassengersSenior() {
        return passengersSenior;
    }

    public void setPassengersSenior(int passengersSenior) {
        this.passengersSenior = passengersSenior;
    }

    public int getPassengersAdult() {
        return passengersAdult;
    }

    public void setPassengersAdult(int passengersAdult) {
        this.passengersAdult = passengersAdult;
    }

    public int getPassengersTeenager() {
        return passengersTeenager;
    }

    public void setPassengersTeenager(int passengersTeenager) {
        this.passengersTeenager = passengersTeenager;
    }

    public int getPassengersInfant() {
        return passengersInfant;
    }

    public void setPassengersInfant(int passengersInfant) {
        this.passengersInfant = passengersInfant;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
