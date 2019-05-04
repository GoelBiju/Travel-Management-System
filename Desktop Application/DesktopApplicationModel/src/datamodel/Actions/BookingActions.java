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
import datamodel.BookingStatusEnum;
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
    
    public ArrayList<Booking> getBookingsByJourney (Integer journeyId){
        
        ArrayList<Booking> customerBookings = (ArrayList<Booking>) apiConnection.getListData("bookings/journey/" + journeyId, new TypeReference<List<Booking>>(){});
        
        return customerBookings;
    }   
    
    public boolean updateBookingStatus(Booking booking, BookingStatusEnum status){
        
        BookingUpdateBindingModel updateBooking = new BookingUpdateBindingModel();
        
        if(status == BookingStatusEnum.CHECKED_IN){
            updateBooking.setBookingReference(booking.getBookingReference());
            updateBooking.setStatus("Checked-In");
        }
        else if(status == BookingStatusEnum.COMPLETE){
            updateBooking.setBookingReference(booking.getBookingReference());
            updateBooking.setStatus("Complete");
        }
        
        int response = this.apiConnection.putData("bookings/" + booking.getBookingReference(), updateBooking);
        System.out.println(response);
        return true;
    }
   
    
//    public static void main(String[] args) {
//        
//        BookingActions actions = new BookingActions();
//        LoginBindingModel loginModel = new LoginBindingModel();
//        loginModel.setEmployeeID("D1006");
//        loginModel.setPassword("testpassword123");
//        loginModel.setLoginType("employee");
//        
//        int response = actions.apiConnection.login(loginModel);
//        System.out.println(response);      
//
//        Booking booking = actions.getBookingsByJourney(3).get(0);
//        System.out.println(booking.getJourney().getJourneyId());
//        
//        actions.updateBookingStatus(booking, BookingStatus.COMPLETE);
//    }
}
