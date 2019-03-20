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

import datamodel.Customer;



/**
 *
 * @author Goel
 */
public class APIConnection {
    
    public static ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
    
//    public static Object[] GetData(String endPoint, Class tableClass)
//    {
//        Object[] object = null;
//        try 
//        {
//		URL url = new URL("http://web.socem.plymouth.ac.uk/IntProj/PRCS252E/api" + "/" + endPoint);
//		
//		object = (Object[]) mapper.readValue(url, tableClass);
//	} 
//        catch (IOException e)
//        {
//              e.printStackTrace();
//        }
//        return object;
//    }
//    
//    public static Object getData(String tableName, Class tableClass, Integer id) {
//        Object object = new Object();
//        
//        try {
//            URL url = new URL("http://xserve.uopnet.plymouth.ac.uk/Modules/INTPROJ/PRCS251C/api/" + tableName + "/" + id);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		conn.setRequestMethod("GET");
//		conn.setRequestProperty("Accept", "application/json");
//
//		if (conn.getResponseCode() != 200) {
//			throw new RuntimeException("Failed : HTTP error code : "
//					+ conn.getResponseCode());
//		}
//
//		BufferedReader br = new BufferedReader(new InputStreamReader(
//			(conn.getInputStream())));           
//        } catch (IOException | RuntimeException ex) {
//            ex.printStackTrace();
//        }
//        
//        return object;
//    }
    
    /**
     * Example showing how to retrieve a single Customer object as a test 
     * and convert from JSON to a Java object.
     * @return 
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
    }
    
}

