package com.example.customermobileapplication.Model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Booking {

    @SerializedName("bookingReference")
    public int bookingReference;

    @SerializedName("customerId")
    public int customerId;

    @SerializedName("journeyId")
    public int journeyId;

    @SerializedName("departingStop")
    public int departingStop;

    @SerializedName("arrivalStop")
    public int arrivalStop;

    @SerializedName("bookedDateTime")
    public Date bookedDateTime;

    @SerializedName("passengersSenior")
    public int numOfSeniors;

    @SerializedName("passengersAdult")
    public int numOfAdults;

    @SerializedName("passengersTeenager")
    public int numOfTeenagers;

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

    public int getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(int journeyId) {
        this.journeyId = journeyId;
    }

    public int getDepartingStop() {
        return departingStop;
    }

    public void setDepartingStop(int departingStop) {
        this.departingStop = departingStop;
    }

    public int getArrivalStop() {
        return arrivalStop;
    }

    public void setArrivalStop(int arrivalStop) {
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

    public int getNumOfTeenagers() {
        return numOfTeenagers;
    }

    public void setNumOfTeenagers(int numOfTeenagers) {
        this.numOfTeenagers = numOfTeenagers;
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
