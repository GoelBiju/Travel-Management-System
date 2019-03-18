package com.example.customermobileapplication.Utilities;

// TODO: Create APIConnection for RESTful methods: GET, PUT, POST and DELETE.
//       Use Volley library for now and allow a JSONObject to be mapped to the Customer model i.e. using Jackson or Gson.


import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;


public class APIConnection {

    private RequestQueueSingleton queueSingleton;

    private String API_BASE_URL;

    // Pass in the application context by this.getApplicationContext().
    public APIConnection(Context context, String apiBaseUrl) {

        // Initialise the request queue in the singleotn.
        queueSingleton = RequestQueueSingleton.getInstance(context);

        // Set up the base URL.
        API_BASE_URL = apiBaseUrl;
    }


    /**
     * This is a convencience method used to perform a GET request using Volley
     * but will only return a single JSONObject of the
     * @param resourceName
     * @return
     */
    public void getSingleApiRequest(String resourceName, final VolleyCallback callback) {

        //
        final APIResponse getResponse = new APIResponse();

        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET,
                this.API_BASE_URL + resourceName, null,

                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response", response.toString());

                        //
                        getResponse.setRequestSuccessful(true);

                        //
                        getResponse.setResponseStatusCode(HttpURLConnection.HTTP_OK);

                        //
                        getResponse.addResponseItem(response);

                        //
                        callback.onSuccess(getResponse);
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Response", error.toString());

                        //
                        getResponse.setRequestSuccessful(false);

                        //
                        getResponse.setResponseStatusCode(error.networkResponse.statusCode);

                        //
                        if (error.networkResponse.data != null) {
                            JSONObject jsonErrorObject;
                            try {
                                jsonErrorObject = new JSONObject(new String(error.networkResponse.data));
                                getResponse.addResponseItem(jsonErrorObject);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        //
                        callback.onFailure(getResponse);
                    }
                });

        //
        queueSingleton.addToRequestQueue(getRequest);
    }


    public void getManyApiRequest(String resourceName, final VolleyCallback callback) {

        //
        final APIResponse getResponse = new APIResponse();

        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET,
                this.API_BASE_URL + resourceName, null,

                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        //
                        getResponse.setRequestSuccessful(true);

                        //
                        getResponse.setResponseStatusCode(HttpURLConnection.HTTP_OK);

                        //
                        for (int i=0; i < response.length(); i++) {
                            try {
                                getResponse.addResponseItem(response.getJSONObject(i));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        //
                        callback.onSuccess(getResponse);
                    }

                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                //
                getResponse.setRequestSuccessful(false);

                //
                getResponse.setResponseStatusCode(error.networkResponse.statusCode);

                //
                if (error.networkResponse.data != null) {
                    JSONObject jsonErrorObject;
                    try {
                        jsonErrorObject = new JSONObject(new String(error.networkResponse.data));
                        Log.d("Response", jsonErrorObject.toString());
                        getResponse.addResponseItem(jsonErrorObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                //
                callback.onFailure(getResponse);
            }
        });

        //
        queueSingleton.addToRequestQueue(getRequest);
    }


    /**
     *
     * @param resourceName
     * @param postData
     * @return
     */
    public void postSingleApiRequest(String resourceName, JSONObject postData, final VolleyCallback callback) {

        // Initialise an APIResponse.
        final APIResponse postResponse = new APIResponse();

        // Create the JsonObjectRequest.
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST,
                this.API_BASE_URL + resourceName, postData,

                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response", response.toString());

                        // Set the request as successful.
                        postResponse.setRequestSuccessful(true);

                        // Set the respose code as 200.
                        postResponse.setResponseStatusCode(HttpURLConnection.HTTP_OK);

                        // Store response in the APIResponse object.
                        postResponse.addResponseItem(response);

                        // Callback function with the response.
                        callback.onSuccess(postResponse);

                    }

                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.Response", error.toString());

                //
                postResponse.setRequestSuccessful(false);

                //
                postResponse.setResponseStatusCode(error.networkResponse.statusCode);

                //
                if (error.networkResponse.data != null) {
                    JSONObject jsonErrorObject;
                    try {
                        jsonErrorObject = new JSONObject(new String(error.networkResponse.data));
                        Log.d("Response", jsonErrorObject.toString());
                        postResponse.addResponseItem(jsonErrorObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                // Callback to onFailure.
                callback.onFailure(postResponse);
            }
        });

        // Add the requeqst to the request queue in the RequestQueueSingleton.
        queueSingleton.addToRequestQueue(postRequest);
    }
}
