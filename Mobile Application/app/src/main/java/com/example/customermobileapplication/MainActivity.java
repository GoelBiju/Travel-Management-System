package com.example.customermobileapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.customermobileapplication.Model.Customer;
import com.example.customermobileapplication.Utilities.APIConnection;
import com.example.customermobileapplication.Utilities.APIResponse;
import com.example.customermobileapplication.Utilities.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

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

    //static final String LOCAL_API_URL = "http://192.168.0.202/api/api/customers/login";

    // Send the request.
    //private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup the API Connection class.
        apiConnection = new APIConnection(getApplicationContext(), getResources().getString(R.string.api_base_url));

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

        // Initialise the requestQueue.
        //requestQueue = Volley.newRequestQueue(this);
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

        // Emsure that the email address and the password are not empty..
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
        String emailAddress = editTextEmailAddress.getText().toString();
        String password = editTextPassword.getText().toString();

        // TODO: Password is currently sent as plain-text, this should be hashed.
        // Create the JSONObject to send in the POST request body.
        JSONObject postData = new JSONObject();
        try {
            postData.put("emailAddress", emailAddress);
            postData.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("Response", postData.toString());

        // TODO: Show progress dialog in login method.
        progressDialog.setMessage("Logging In...");
        progressDialog.show();


        // Send a login request.
        apiConnection.postSingleApiRequest("customers/login", postData, new VolleyCallback() {

            @Override
            public void onSuccess(APIResponse response) {

                Log.d("Response", "Request was successful.");

                progressDialog.hide();

                Intent startIntent = new Intent(getApplicationContext(), HomeActivity.class);

                try{
                    int customerId = Integer.parseInt(response.getSingleResponse().getString("customerId"));
                    // Pass the customer Id to the Home page.
                    startIntent.putExtra("customerId", customerId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                startActivity(startIntent);

                try {
                    Toast.makeText(getApplicationContext(),  "Successfully logged in as: " +
                            response.getSingleResponse().getString("emailAddress"), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(APIResponse response) {

                Log.d("Response", "Request was unsuccessful.");

                progressDialog.hide();

                Toast.makeText(getApplicationContext(),  "Status Code: " + Integer.toString(response.getResponseStatusCode())
                        + ", Message: " + response.getResponse() + ".", Toast.LENGTH_LONG).show();
            }
        });
    }
}
