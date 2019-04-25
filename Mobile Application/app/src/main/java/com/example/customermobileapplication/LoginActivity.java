package com.example.customermobileapplication;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.customermobileapplication.BindingModels.LoginBindingModel;
import com.example.customermobileapplication.Model.Customer;
import com.example.customermobileapplication.Utilities.API.APIConnection;
import com.example.customermobileapplication.Utilities.API.APIResponse;
import com.example.customermobileapplication.Utilities.API.CustomCallback;
import com.example.customermobileapplication.Utilities.API.VolleyCallback;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    // Text fields.
    private EditText editTextEmailAddress;
    private EditText editTextPassword;

    // Buttons.
    private Button loginButton;
    private Button createAccountButton;

    // Progress dialog.
    ProgressDialog progressDialog;

    // API Connection class.
    APIConnection apiConnection;

    // Get the SharedPreferences.
    SharedPreferences pref;

    //static final String LOCAL_API_URL = "http://192.168.0.202/api/api/customers/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup the API Connection class.
        apiConnection = new APIConnection(getApplicationContext(), getResources().getString(R.string.base_url));

        // Bind views.
        bindViews();

        // Set button actions.
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the CreateAccountActivity to allow users to register.
                Intent startIntent = new Intent(getApplicationContext(), CreateAccountActivity.class);
                startActivity(startIntent);
            }
        });

        // Proceed to process the login.
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processLogin();
            }
        });
    }


    /**
     *
     */
    private void bindViews() {
        // Bind fields.
        editTextEmailAddress = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        // Bind buttons.
        loginButton = findViewById(R.id.buttonLogin);
        createAccountButton = findViewById(R.id.buttonCreateAccount);

        // Bind progress dialog (this is deprecated).
        progressDialog = new ProgressDialog(this);
    }


    /**
     * This function will call the API to pass the username and password details.
     */
    public void processLogin() {

        // Ensure that the email address and the password are not empty..
        if (!editTextEmailAddress.getText().toString().equals("") &&
                !editTextPassword.getText().toString().equals("")) {

            // TODO: Validate the email address e.g. with regex to ensure that it conforms.

            // Send the login POST request to the Web API.
            sendCustomerLogin();
        } else {
            Toast.makeText(getApplicationContext(), "The email address and/or password cannot be empty.",
                    Toast.LENGTH_SHORT).show();
        }
    }


    /**
     *
     */
    private void sendCustomerLogin() {

        // TODO: Map this POST data to a Customer object.
        final String emailAddress = editTextEmailAddress.getText().toString();
        final String password = editTextPassword.getText().toString();

        // TODO: Password is currently sent as plain-text, this should be hashed.
        // Create the JSONObject to send in the POST request body.
//        JSONObject postData = new JSONObject();
//        try {
//            postData.put("emailAddress", emailAddress);
//            postData.put("password", password);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        Log.d("Response", postData.toString());


        // Create the LoginBindingModel.
        LoginBindingModel loginBindingModel = new LoginBindingModel();
        loginBindingModel.setEmailAddress(emailAddress);
        loginBindingModel.setPassword(password);

        // TODO: Show progress dialog in login method.
        progressDialog.setMessage("Logging In...");
        progressDialog.show();


        // Send a login request.
//        apiConnection.postSingleApiRequest("customers/login", postData, new VolleyCallback() {
//
//            @Override
//            public void onSuccess(APIResponse response) {
//
//                Log.d("Response", "Request was successful.");
//
//                progressDialog.hide();
//
//                Intent startIntent = new Intent(getApplicationContext(), HomeActivity.class);
//
//                try{
//                    int customerId = Integer.parseInt(response.getSingleResponse().getString("customerId"));
//                    // Pass the customer Id to the Home page.
//                    //startIntent.putExtra("customerId", customerId);
//
//                    // Place the customerId into SharedPreferences.
//                    // Create the sharedpreferences to use in the app.
//                    pref = getApplicationContext().getSharedPreferences("userDetails", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = pref.edit();
//                    editor.clear();
//                    editor.putInt("customerId", customerId);
//                    editor.apply();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                startActivity(startIntent);
//
//                try {
//                    Toast.makeText(getApplicationContext(),  "Successfully logged in as: " +
//                            response.getSingleResponse().getString("emailAddress"), Toast.LENGTH_LONG).show();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(APIResponse response) {
//
//                Log.d("Response", "Request was unsuccessful.");
//
//                progressDialog.hide();
//
//                Toast.makeText(getApplicationContext(),  "Status Code: " + Integer.toString(response.getResponseStatusCode())
//                        + ", Message: " + response.getResponse() + ".", Toast.LENGTH_LONG).show();
//            }
//        });

        //
//        apiConnection.postCustomJsonObject("customers/login", loginBindingModel,
//                Customer.class, new CustomCallback() {
//
//                    public void onSuccess(Object responseObject) {
//
//                        // Map the response object to expected.
//                        Customer customerDetails = (Customer) responseObject;
//
//                        Log.d("Response", "Request was successful.");
//
//                        progressDialog.hide();
//
//                        Intent startIntent = new Intent(getApplicationContext(), NavigationActivity.class);
//
//                        // Place the customerId into SharedPreferences.
//                        // Create the sharedpreferences to use in the app.
//                        pref = getApplicationContext().getSharedPreferences("userDetails", MODE_PRIVATE);
//                        SharedPreferences.Editor editor = pref.edit();
//                        editor.clear();
//                        // Save the customer id to use further in the app.
//                        editor.putInt("customerId", customerDetails.getCustomerId());
//                        editor.apply();
//
//                        startActivity(startIntent);
//                        Toast.makeText(getApplicationContext(), "Logged in as: " + customerDetails.getEmailAddress(),
//                                Toast.LENGTH_LONG).show();
//
//                        // Finish this activity.
//                        finish();
//                    }
//
//                    public void onFailure(APIResponse errorResponse) {
//                        Log.d("Response", "Request was unsuccessful.");
//
//                        progressDialog.hide();
//
//                        Toast.makeText(getApplicationContext(),  "Status Code: " +
//                                Integer.toString(errorResponse.getResponseStatusCode())
//                         + ", Message: " + errorResponse.getResponse() + ".", Toast.LENGTH_LONG).show();
//                    }
//                });

        // Call the login function.
        apiConnection.processLogin(getResources().getString(R.string.base_url) + "token", emailAddress, password, new VolleyCallback() {
            @Override
            public void onSuccess(APIResponse response) {
                Log.d("Response", "Request was successful.");

                progressDialog.hide();

                Intent startIntent = new Intent(getApplicationContext(), NavigationActivity.class);

                try {
                    int customerId = Integer.parseInt(response.getSingleResponse().getString("identification"));
                    String accessToken = response.getSingleResponse().getString("access_token");

                    // Place the customerId into SharedPreferences.
                    // Create the sharedpreferences to use in the app.
                    pref = getApplicationContext().getSharedPreferences("userDetails", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.clear();
                    // Save the customer id and access token to use further in the app.
                    editor.putInt("customerId", customerId);
                    //editor.putString("accessToken", accessToken);
                    editor.apply();

                    // Place the access token in the api shared preferences.
                    apiConnection.setAccessToken(accessToken);

                    startActivity(startIntent);
//                    Toast.makeText(getApplicationContext(), "Logged in successfully.",
//                            Toast.LENGTH_LONG).show();

                    // Finish this activity.
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(APIResponse response) {
                Log.d("Response", "Request was unsuccessful.");

                progressDialog.hide();

                try {
                    Toast.makeText(getApplicationContext(),  response.getSingleResponse().getString("error_description"), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
