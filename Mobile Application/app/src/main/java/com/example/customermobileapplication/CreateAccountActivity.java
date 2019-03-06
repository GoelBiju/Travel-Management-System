package com.example.customermobileapplication;

import android.app.Activity;
import android.app.DatePickerDialog;
//import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.Calendar;

public class CreateAccountActivity extends Activity implements View.OnClickListener {

    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextDateOfBirth;
    private EditText editTextAddressLineOne;
    private EditText editTextAddressLineTwo;
    private EditText editTextPostCode;
    private EditText editTextPhoneNumber;
    private EditText editTextEmailAddress;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;

    // Register button.
    private Button registerButton;

    // Date picker dialog.
    private DatePickerDialog datePickerDialog;

    // Progress bar.
    ProgressBar progressBar;

    // API url to create an account.
    static final String API_URL = "http://web.socem.plymouth.ac.uk/IntProj/PRCS252E/api/customers/";


    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // Bind the views with ids.
        bindViews();

        // Set listeners for views.
        setViewActions();

        // Prepare the DatePickerDialog to show calendar.
        prepareDatePickerDialog();
    }


    /**
     *
     */
    private void bindViews() {

        // Bind all fields from the layout to the class variables.
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextDateOfBirth = findViewById(R.id.editTextDateOfBirth);
        editTextAddressLineOne = findViewById(R.id.editTextAddressLineOne);
        editTextAddressLineTwo = findViewById(R.id.editTextAddressLineTwo);
        editTextPostCode = findViewById(R.id.editTextPostCode);
        editTextPhoneNumber = findViewById(R.id.editTextPhone);
        editTextEmailAddress = findViewById(R.id.editTextEmailAddress);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);

        // Register button view.
        registerButton = findViewById(R.id.registerButton);

        // Bind progress bar.
        progressBar = findViewById(R.id.progressBar);
    }


    /**
     *
     */
    private void setViewActions() {

        editTextDateOfBirth.setOnClickListener(this);
        registerButton.setOnClickListener(this);
    }


    /**
     *
     */
    private void prepareDatePickerDialog() {
        // Get current date.
        Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                editTextDateOfBirth.setText(dayOfMonth + "/" + month + "/" + year);
                datePickerDialog.dismiss();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }


    /**
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // Show the Calendar if the date of birth is selected.
            case R.id.editTextDateOfBirth:
                datePickerDialog.show();
                break;

            // Proceed with the registration.
            case R.id.registerButton:
                processRegistration();
                break;
        }
    }


    /**
     *
     */
    private void processRegistration() {

        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String dateOfBirth = editTextDateOfBirth.getText().toString();
        String postCode = editTextPostCode.getText().toString();
        String addressLineOne = editTextAddressLineOne.getText().toString();
        String addressLineTwo = editTextAddressLineTwo.getText().toString();
        String phoneNumber = editTextPhoneNumber.getText().toString();
        String emailAddress = editTextPostCode.getText().toString();
        String password = editTextPassword.getText().toString();
        String confirmPassword = editTextConfirmPassword.getText().toString();

//        // Check that all values have been entered.
//        if (!firstName.equals("") && !lastName.equals("") && !dateOfBirth.equals("") && !postCode.equals("")
//                && !addressLineOne.equals("") && !phoneNumber.equals("") && !emailAddress.equals("")
//                && !password.equals("") && !confirmPassword.equals("")) {
//
//            // Ensure that the password and the password confirmation matches.
//            if (password.equals(confirmPassword)) {
//                // Process the registration.
//                Toast.makeText(this, getResources().getString(R.string.implement), Toast.LENGTH_SHORT).show();
//
//                // Make a POST request to the Customers endpoint in the Web API.
//                // new CreateAccountTask().execute();
//            } else {
//                Toast.makeText(this, getResources().getString(R.string.passwordsMustBeSame), Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            Toast.makeText(this, getResources().getString(R.string.fieldsEmpty), Toast.LENGTH_SHORT).show();
//        }

        sendRequest();
    }


    private void sendRequest() {

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, API_URL, null,
            new Response.Listener<JSONArray>()
            {
                @Override
                public void onResponse(JSONArray response) {
                    Log.d("Response", response.toString());

                    // Create Customer objects by processing the JSON request.
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            // Current JSON object.
                            JSONObject customer = response.getJSONObject(i);

                            String id = customer.getString("customerId");
                            String firstName = customer.getString("");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },

            new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Error.Response", error.toString());
                }
            }
        );

        // Add to the request queue.
        queue.add(jsonArrayRequest);
    }

//    class CreateAccountTask extends AsyncTask<Void, Void, String> {
//
//        private Exception exception;
//
//
//        /**
//         *
//         */
//        protected void onPreExecute() {
//            progressBar.setVisibility(View.VISIBLE);
//        }
//
//
//        /**
//         *
//         * @param urls
//         * @return
//         */
//        protected String doInBackground(Void... urls) {
//
//            // Get all the field values which we will send to the API.
//            String firstName = editTextFirstName.getText().toString();
//            String lastName = editTextLastName.getText().toString();
//            String dateOfBirth = editTextDateOfBirth.getText().toString();
//            String postCode = editTextPostCode.getText().toString();
//            String addressLineOne = editTextAddressLineOne.getText().toString();
//            String addressLineTwo = editTextAddressLineTwo.getText().toString();
//            String phoneNumber = editTextPhoneNumber.getText().toString();
//            String emailAddress = editTextPostCode.getText().toString();
//            String password = editTextPassword.getText().toString();
//            String confirmPassword = editTextConfirmPassword.getText().toString();
//
//
//            // Connect to the API and process the PUT request.
//            return "";
//        }
//    }
}
