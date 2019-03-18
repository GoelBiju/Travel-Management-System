package com.example.customermobileapplication.Utilities;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class APIResponse {

    // The resource that was called.
    private String resourceName;

    // If the request was completed without error.
    private boolean requestSuccessful;

    // The status code of the response.
    private int responseStatusCode;

    // The array list with responses. that was sent in response.
    private ArrayList<JSONObject> responseList;


    public APIResponse() {
        this.resourceName = "";
        this.requestSuccessful = false;
        this.responseStatusCode = -1;
        this.responseList = new ArrayList<>();
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public boolean isRequestSuccessful() {
        return requestSuccessful;
    }

    public void setRequestSuccessful(boolean requestSuccessful) {
        this.requestSuccessful = requestSuccessful;
    }

    public int getResponseStatusCode() {
        return responseStatusCode;
    }

    public void setResponseStatusCode(int responseStatusCode) {
        this.responseStatusCode = responseStatusCode;
    }

    public ArrayList<JSONObject> getResponse() {
        return this.responseList;
    }

    public void addResponseItem(JSONObject responseItem) {
        this.responseList.add(responseItem);
    }
}
