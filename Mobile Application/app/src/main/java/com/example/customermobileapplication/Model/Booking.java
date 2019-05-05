package com.example.customermobileapplication.Model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Booking {

    @SerializedName("bookingReference")
    public int bookingReference;

    @SerializedName("customerId")
    public int customerId;

    @SerializedName("journey")
    public Journey journey;

    @SerializedName("departingStop")
    public Stop departingStop;

    @SerializedName("arrivalStop")
    public Stop arrivalStop;

    @SerializedName("bookedDateTime")
    public Date bookedDateTime;

    @SerializedName("passengersSenior")
    public int numOfSeniors;

    @SerializedName("passengersAdult")
    public int numOfAdults;

    @SerializedName("passengersChildren")
    public int numOfChildren;

    @SerializedName("passengersInfant")
    public int numOfInfants;

    @SerializedName("amountPaid")
    public float amountPaid;

    @SerializedName("status")
    public String status;

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

    public int getNumOfSeniors() {
        return numOfSeniors;
    }

    public void setNumOfSeniors(int numOfSeniors) {
        this.numOfSeniors = numOfSeniors;
    }

    public int getNumOfAdults() {
        return numOfAdults;
    }

    public void setNumOfAdults(int numOfAdults) {
        this.numOfAdults = numOfAdults;
    }

    public int getNumOfChildren() {
        return numOfChildren;
    }

    public void setNumOfChildren(int numOfChildren) {
        this.numOfChildren = numOfChildren;
    }

    public int getNumOfInfants() {
        return numOfInfants;
    }

    public void setNumOfInfants(int numOfInfants) {
        this.numOfInfants = numOfInfants;
    }

    public float getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(float amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
