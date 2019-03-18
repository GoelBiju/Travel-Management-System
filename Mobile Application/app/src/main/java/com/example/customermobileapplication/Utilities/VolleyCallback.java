package com.example.customermobileapplication.Utilities;


public interface VolleyCallback {

    /**
     *
     * @param response
     */
    void onSuccess(APIResponse response);


    /**
     *
     * @param response
     */
    void onFailure(APIResponse response);
}
