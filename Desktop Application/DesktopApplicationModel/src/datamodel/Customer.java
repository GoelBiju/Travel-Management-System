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
 * @author Goel
 */
public class Customer {
    
    public int customerId;
    
    public String firstName;
    
    public String lastName;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public Date dateOfBirth;
    
    public String addressLineOne;
    
    public String addressLineTwo;
    
    public String postCode;
    
    public String mobileNumber;
    
    public String emailAddress;
    
}
