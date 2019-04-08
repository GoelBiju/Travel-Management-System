package com.example.customermobileapplication.Utilities.API;

import com.example.customermobileapplication.Model.Customer;

public interface CustomCallback {

    void onSuccess(Object responseObject);

    void onFailure(APIResponse errorResponse);
}
