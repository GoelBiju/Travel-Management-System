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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Goel
 */
public class APIConnection {
    
    public static ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
    
    public static Object[] getListData(String endPoint, Class tableClass)
    {
        Object[] object = null;
        try 
        {
		String uri = "http://web.socem.plymouth.ac.uk/IntProj/PRCS252E/api/" + endPoint;
		URL url = new URL(uri);
		object = (Object[]) mapper.readValue(url, tableClass);
	} 
        catch (Exception e)
        {
             e.printStackTrace();
        }
       return object;
    }
    
    public static Object getData(String tableName, Class tableClass, Integer id) {
        Object object = new Object();
        
        try {
            String uri = "http://web.socem.plymouth.ac.uk/IntProj/PRCS252E/api/" + tableName + "/" + id;
            URL url = new URL(uri);
            
            object = mapper.readValue(url, tableClass);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return object;
    }
    
    public static Integer putData(String endpoint, Object obj) {
        try {
            String uri = "http://web.socem.plymouth.ac.uk/IntProj/PRCS252E/api/" + endpoint;
            URL url = new URL(uri);
            
            String json = mapper.writeValueAsString(obj);
            
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);
            
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            
            try (OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream())) {
                output.write(json);
                output.flush();
            }
            
            return connection.getResponseCode();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return 400;
    }
    
    public static HashMap<String, Object> PostData (String endpoint, Object obj)
    {
        HashMap<String, Object> response = new HashMap<>();
        try {
            // URL for the API
            String uri = "http://web.socem.plymouth.ac.uk/IntProj/PRCS252E/api/" + endpoint;
            URL url = new URL(uri);
            
            // Build a JSON string to post
            String json = mapper.writeValueAsString(obj); // <-- This only works if using Jackson to parse JSON
            
            System.out.println(json);
            
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            //connection.setRequestProperty("Authorization", /* AUTH CONTENT */ "");
            
            try (OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream())) {
                output.write(json);
                output.flush();
            }
            
            System.out.println(connection.getResponseMessage());
            
            response.put("responseCode", connection.getResponseCode());
            
            if (connection.getResponseCode() >= 200 && connection.getResponseCode() < 300) {
                response.put("responseMessage", new BufferedReader(new InputStreamReader((connection.getInputStream()))).readLine());
            } else {
                response.put("responseMessage", null);
            }
            
            return response;
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        response.put("responseCode", 400);
        response.put("responseMessage", null);
        
        return response;
    
    }
    
    public static int DeleteData(String endPoint)
    {
        try {
            String uri = "http://web.socem.plymouth.ac.uk/IntProj/PRCS252E/api/" + endPoint;
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            
            return connection.getResponseCode();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 400;
    }
    
    /**
     * Example showing how to retrieve a single Customer object as a test 
     * and convert from JSON to a Java object.
     * @return Customer object mapped from json data in the database
     */
//    public static Object getCustomer() {
//        Object object = new Object();
//        
//        try {
//            String uri = "http://web.socem.plymouth.ac.uk/IntProj/PRCS252E/api/customers/11";
//            URL url = new URL(uri);
//        
//            object = mapper.readValue(url, Customer.class);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        
//        
//        return object;
//    }
    
    
    public static void main(String[] args) {
        System.out.println("APIConnection test.");
        
        //Customer customer = (Customer) getCustomer();
        
        //System.out.println("First Name: " + customer.firstName);
        
        //Employee[] employees = (Employee[]) GetData("employees", Employee[].class);
        
        //for (Employee e:employees)
        //{
        //    System.out.println(e.getFirstName());
        //    System.out.println(e.getLastName());
        //}

        
    }
    
}

