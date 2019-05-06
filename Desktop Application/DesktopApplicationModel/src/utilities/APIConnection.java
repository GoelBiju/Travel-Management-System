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

import datamodel.BindingModels.LoginBindingModel;
import java.net.URL;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import datamodel.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Goel
 */
public class APIConnection {
    
    private static APIConnection apiInstance;
    
    private String accessToken;
    
    private final ObjectMapper mapper;
    
    private final String baseUrl = "http://web.socem.plymouth.ac.uk/IntProj/PRCS252E/";
    private final String apiBaseUrl = baseUrl + "api/";
    
    private final String baseTestUrl = "http://localhost:60019/";
    private final String apiBaseTestUrl = baseTestUrl + "api/";
        
    private APIConnection() {
        
        // TODO: Ensure that the enums are written and read correctly.
        this.mapper = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
//                .enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
//                .enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        
        this.accessToken = "";
    }
    
    
    public static APIConnection getInstance() {
        if (apiInstance == null) {
            apiInstance = new APIConnection();
        }
        return apiInstance;
    }
    
    
    public boolean login(LoginBindingModel loginModel){
        try
        {
            String urlParameters = "grant_type=password&username=" + loginModel.getEmployeeID() + 
                    "&password=" + loginModel.getPassword() + "&login_type=" + loginModel.getLoginType();

            byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
            int postDataLength = postData.length;

            String uri = this.baseUrl + "token";
            System.out.println("Login URL: " + uri);
            
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
            connection.setRequestProperty("charset", "utf-8");
            connection.setRequestProperty("Content-Length", Integer.toString(postDataLength ));
            connection.setUseCaches(false);

            try(DataOutputStream wr = new DataOutputStream(connection.getOutputStream())){
                wr.write(postData);
            }
            
            System.out.println(connection.getResponseCode());
            
            // Read the response body.
            if (connection.getResponseCode() >= 200 && connection.getResponseCode() < 300) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder sb = new StringBuilder();
                    String line;
                    
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    
                    // Convert the response object to the Java object.
                    TokenResponse tokenResponse = this.mapper.readValue(sb.toString(), TokenResponse.class);
                    
                    // Store the access token returned.
                    this.accessToken = tokenResponse.getAccessToken();
                    // System.out.println(tokenResponse.getAccessToken());
                    return true;
                }
            }
            
            return false;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return false;
    }

    
    public ArrayList getListData(String endPoint, TypeReference typeReference)
    {
        ArrayList objectList = null;
        try {
            String uri = this.apiBaseUrl + endPoint;
            System.out.println(uri);
            
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + this.accessToken);
            
            System.out.println(connection.getResponseCode());
            
            // Get the json string.
            if (connection.getResponseCode() >= 200 && connection.getResponseCode() < 300) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder sb = new StringBuilder();
                    String line;
                    
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    
                    System.out.println(sb.toString());
                    
                    // Convert the response object to the Java object.
                    objectList = this.mapper.readValue(sb.toString(), typeReference);
                }
            }
	} catch (Exception e) {
             e.printStackTrace();
        }
        
       return objectList;
    }
    
    
    public Object getData(String tableName, Class tableClass, String id) {
        
        Object object = new Object();
        
        try {
            String uri = this.apiBaseUrl + tableName +  "/" + id;
            System.out.println(uri);
            
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + this.accessToken);
            
            // Get the json string.
            if (connection.getResponseCode() >= 200 && connection.getResponseCode() < 300) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder sb = new StringBuilder();
                    String line;
                    
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    
                    // Convert the response object to the Java object.
                    object = this.mapper.readValue(sb.toString(), tableClass);
                }
            }
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return object;
    }
    
    
    public Integer putData(String endpoint, Object obj) {
        try {
            String uri = this.apiBaseUrl + endpoint;
            URL url = new URL(uri);
            
            String json = mapper.writeValueAsString(obj);
            
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);
            
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("authorization", "Bearer " + this.accessToken);
            
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
    
    
    public HashMap<String, Object> postData (String endpoint, Object obj)
    {
        HashMap<String, Object> response = new HashMap<>();
        try {
            // URL for the API
            String uri = this.apiBaseUrl + endpoint;
            URL url = new URL(uri);
            
            // Build a JSON string to post
            String json = mapper.writeValueAsString(obj); // <-- This only works if using Jackson to parse JSON
            
            System.out.println(json);
            
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + this.accessToken);
            
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
    
    
    public int deleteData(String endPoint)
    {
        try {
            String uri = this.apiBaseUrl + endPoint;
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + this.accessToken);
            
            return connection.getResponseCode();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 400;
    }
    
    
    public Coach getCoachData (String coachId){
        
        Coach coach = (Coach) getData("coaches", Coach.class, coachId);
        System.out.println("Returned Coach ID: " + coach.getCoachId());
        System.out.println("Returned Coach Make: " + coach.getCoachMake());
        System.out.println("Returned Coach Model: " + coach.getCoachModel());
        System.out.println("Returned Coach Registration Plate: " + coach.getRegistrationPlate());
        System.out.println("Returned Coach Capacity: " + coach.getCoachCapacity());
        System.out.println("Returned Coach Is Active: " + coach.isAvailable());
        
        return coach;
    }
    
    /**
     * Example showing how to retrieve a single Customer object as a test 
     * and convert from JSON to a Java object.
     * @param args
     * @return Customer object mapped from json data in the database
     */
//    public static Object getCustomer() {
//        Object object = new Object();
//        
//        try {
//           String uri = "http://web.socem.plymouth.ac.uk/IntProj/PRCS252E/api/customers/12";
//           URL url = new URL(uri);
//        
//           object = mapper.readValue(url, Customer.class);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        
//        
//        return object;
//    }
    
    
//    public static void main(String[] args) {
//        
//        APIConnection api = APIConnection.getInstance();
//        LoginBindingModel loginModel = new LoginBindingModel();
//        loginModel.setEmployeeID("");
//        loginModel.setPassword("");
//        loginModel.setLoginType("employee");
//        
//        int response = api.login(loginModel);
//        System.out.println(response);      
//
//        Coach coach;
//        coach = (Coach) api.getCoachData("6");
//        System.out.println(coach.getCoachCapacity());
//        
//        Employee employees = (Employee) api.getData("employees", Employee.class, "");
//        System.out.println(employees.getFirstName());
//        System.out.println(employees.getLastName());
//        
//        TypeReference<List<Journey>> typeReference = new TypeReference<List<Journey>>() {};
//        ArrayList<Journey> journeys = (ArrayList<Journey>) api.getListData("journeys", typeReference);
//        
//        Journey j = journeys.get(0);
//        System.out.println(j.getJourneyId());
//    }
}
