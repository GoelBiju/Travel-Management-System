/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.BindingModels;

/**
 *
 * @author vcastellani
 */
public class BookingUpdateBindingModel {
    public int BookingReference;
    public String Status;

    public int getBookingReference() {
        return BookingReference;
    }

    public void setBookingReference(int BookingReference) {
        this.BookingReference = BookingReference;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
}
