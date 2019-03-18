package com.example.customermobileapplication.Utilities;

import org.json.JSONObject;

import java.util.ArrayList;


public class APIResponse {

    // If the request was completed without error.
    private boolean requestSuccessful;

    // The status code of the response.
    private int responseStatusCode;

    // The array list with responses. that was sent in response.
    private ArrayList<JSONObject> responseList;


    /**
     *
     */
    public APIResponse() {

        this.requestSuccessful = false;
        this.responseStatusCode = -1;
        this.responseList = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public boolean isRequestSuccessful() {
        return requestSuccessful;
    }


    /**
     *
     * @param requestSuccessful
     */
    public void setRequestSuccessful(boolean requestSuccessful) {
        this.requestSuccessful = requestSuccessful;
    }

    /**
     *
     * @return
     */
    public int getResponseStatusCode() {
        return responseStatusCode;
    }


    /**
     *
     * @param responseStatusCode
     */
    public void setResponseStatusCode(int responseStatusCode) {
        this.responseStatusCode = responseStatusCode;
    }


    /**
     *
     * @return
     */
    public ArrayList<JSONObject> getResponse() {
        return this.responseList;
    }

    /**
     *
     * @param responseItem
     */
    public void addResponseItem(JSONObject responseItem) {
        this.responseList.add(responseItem);
    }


    /**
     *
     * @return
     */
    public JSONObject getSingleResponse() {
        if (!this.responseList.isEmpty()) {
            return this.responseList.get(0);
        }
        return null;
    }
}
