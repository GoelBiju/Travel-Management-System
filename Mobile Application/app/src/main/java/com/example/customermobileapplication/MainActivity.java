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

    // API url to create an account for customers.
    static final String CUSTOMERS_LOGIN_API_URL = "http://web.socem.plymouth.ac.uk/IntProj/PRCS252E/api/customers/login";
    //static final String CUSTOMERS_LOGIN_API_URL = "http://192.168.0.202/api/api/customers/login";

    // Send the request.
    private RequestQueue requestQueue;


    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        // TODO: RequestQueue needs a Singleton for the application instance(?)
        // Initialise the requestQueue.
        requestQueue = Volley.newRequestQueue(this);
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
                !editTextEmailAddress.getText().toString().equals("")) {

            // TODO: Validate the email address e.g. with regex to ensure that

            // Send the login POST request to the Web API.
            sendCustomerLogin();
        }
    }


    /**
     *
     */
    private void sendCustomerLogin() {

        //
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

        // Show progress dialog.
        progressDialog.setMessage("Logging In...");
        progressDialog.show();

        // Create the request.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, CUSTOMERS_LOGIN_API_URL, postData,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response", response.toString());

                        try {
                            Log.d("Response", "Received account id:" + response.getInt("customerId"));
                            Toast.makeText(getApplicationContext(), "Successfully logged in as " + response.getString("emailAddress") + ".",
                                    Toast.LENGTH_SHORT).show();

                            // Hide the dialog box.
                            progressDialog.hide();

                            // TODO: Ensure we can map the JSONObject to the Customer class.
                            //       This should be performed in the APIConnection class.
                            // Start the intent for the main page and pass in the received user object.
                            Intent startIntent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(startIntent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.Response", error.toString());

                progressDialog.hide();

                // Get the network response.
                NetworkResponse networkResponse = error.networkResponse;

                if (networkResponse != null && networkResponse.data != null) {
                    String jsonError = null;
                    try {
                        jsonError = new JSONObject(new String(networkResponse.data)).toString();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(),  "Status Code: " + Integer.toString(networkResponse.statusCode)
                            + ", Message: " + jsonError + ".", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "An error occurred but unable to get response status/message.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // POST the JSON data.
        requestQueue.add(jsonObjectRequest);
    }
}
