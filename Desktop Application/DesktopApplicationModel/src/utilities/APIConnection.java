/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

//import java.io.BufferedReader;
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.net.HttpURLConnection;
//import java.util.ArrayList;
//import java.util.Arrays;

import java.net.URL;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import datamodel.*;
import java.io.IOException;



/**
 *
 * @author Goel
 */
public class APIConnection {
    
    public static ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
    
    public static Object[] GetData(String endPoint, Class tableClass)
    {
        Object[] object = null;
        try 
        {
		String uri = ("http://web.socem.plymouth.ac.uk/IntProj/PRCS252E/api/" + endPoint);
		URL url = new URL(uri);
		object = (Object[]) mapper.readValue(url, tableClass);
	} 
        catch (Exception e)
        {
             e.printStackTrace();
        }
       return object;
    }   
    
    /**
     * Example showing how to retrieve a single Customer object as a test 
     * and convert from JSON to a Java object.
     * @return Customer object mapped from json data in the database
     */
    public static Object getCustomer() {
        Object object = new Object();
        
        try {
            String uri = "http://web.socem.plymouth.ac.uk/IntProj/PRCS252E/api/customers/11";
            URL url = new URL(uri);
        
            object = mapper.readValue(url, Customer.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        
        return object;
    }
    
    public static void main(String[] args) {
        System.out.println("APIConnection test.");
        
        Customer customer = (Customer) getCustomer();
        System.out.println("First Name: " + customer.firstName);
        
        Employee[] employees = (Employee[]) GetData("employees", Employee[].class);
        
        for (Employee e:employees)
        {
            System.out.println(e.getFirstName());
            System.out.println(e.getLastName());
        }
    }
    
}

