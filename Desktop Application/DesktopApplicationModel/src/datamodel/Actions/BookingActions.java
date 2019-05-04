/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.Actions;

import datamodel.Booking;
import utilities.APIConnection;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import datamodel.BindingModels.BookingUpdateBindingModel;
import datamodel.BindingModels.LoginBindingModel;
import datamodel.BookingStatus;
import datamodel.Customer;

/**
 *
 * @author Goel
 */
public class BookingActions {
    
    private APIConnection apiConnection;
    
    public BookingActions() {
        
        this.apiConnection = APIConnection.getInstance();
    }
    
    /**
     * 
     * @param bookingId
     * @return 
     */
    public Booking getBooking(Integer bookingId) {
        
        Booking customerBooking = (Booking) apiConnection.getData("bookings", Booking.class, bookingId.toString());
        
        return customerBooking;
    }
    
    /**
     * 
     * @param journeyId
     * @return 
     */
    public ArrayList<Booking> getBookingsByJourney (Integer journeyId){
        
        ArrayList<Booking> customerBookings = (ArrayList<Booking>) apiConnection.getListData("bookings/journey/" + journeyId, new TypeReference<List<Booking>>(){});
        
        return customerBookings;
    }
    
    
    /**
     * 
     * @param booking
     * @param status
     * @return 
     */
    public boolean updateBookingStatus(Booking booking, BookingStatus status){
        
        BookingUpdateBindingModel updateBooking = new BookingUpdateBindingModel();
        
        if(status == BookingStatus.CHECKED_IN){
            updateBooking.setBookingReference(booking.getBookingReference());
            updateBooking.setStatus("Checked-In");
        } else if (status == BookingStatus.COMPLETE){
            updateBooking.setBookingReference(booking.getBookingReference());
            updateBooking.setStatus("Complete");
        }
        
        int response = this.apiConnection.putData("bookings/" + booking.getBookingReference(), updateBooking);
        System.out.println(response);
        return true;
    }
   
    
    public static void main(String[] args) {
        
        BookingActions actions = new BookingActions();
        
        //
        LoginBindingModel loginModel = new LoginBindingModel();
        loginModel.setEmployeeID("D1006");
        loginModel.setPassword("testpassword123");
        loginModel.setLoginType("employee");
        
        boolean response = actions.apiConnection.login(loginModel);
        System.out.println(response);      

        //
        Booking booking = actions.getBookingsByJourney(3).get(0);
        System.out.println(booking.getJourney().getJourneyId());
        
        //
        actions.updateBookingStatus(booking, BookingStatus.COMPLETE);
        
        // 
        System.out.println(actions.getBooking(booking.getBookingReference()).getBookedDateTime());
    }
}
