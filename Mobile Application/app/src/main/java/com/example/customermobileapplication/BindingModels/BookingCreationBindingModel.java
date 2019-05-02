package com.example.customermobileapplication.BindingModels;

import com.example.customermobileapplication.Model.Journey;
import com.example.customermobileapplication.Model.Stop;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class BookingCreationBindingModel {

    @SerializedName("customerId")
    public int customerId;

    @SerializedName("journeyId")
    public int journeyId;

    @SerializedName("departingStopId")
    public int departingStopId;

    @SerializedName("arrivalStopId")
    public int arrivalStopId;

    @SerializedName("bookedDateTime")
    public Date bookedDateTime;

    @SerializedName("paymentId")
    public String paymentId;

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

    public int getDepartingStopId() {
        return departingStopId;
    }

    public void setDepartingStopId(int departingStopId) {
        this.departingStopId = departingStopId;
    }

    public int getArrivalStopId() {
        return arrivalStopId;
    }

    public void setArrivalStopId(int arrivalStopId) {
        this.arrivalStopId = arrivalStopId;
    }

    public Date getBookedDateTime() {
        return bookedDateTime;
    }

    public void setBookedDateTime(Date bookedDateTime) {
        this.bookedDateTime = bookedDateTime;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
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
}
