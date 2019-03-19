package com.example.customermobileapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.customermobileapplication.Utilities.APIConnection;
import com.example.customermobileapplication.Utilities.APIResponse;
import com.example.customermobileapplication.Utilities.VolleyCallback;

import org.json.JSONObject;
import org.json.JSONException;

import java.net.HttpURLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class CreateAccountActivity extends Activity implements View.OnClickListener {

    // TODO: Validate fields which have constraints in database.
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

    // Progress dialog.
    ProgressDialog progressDialog;

    //
    APIConnection apiConnection;

    // API url to create an account for customers.
    //static final String LOCAL_API_URL = "http://192.168.0.202/api/api/customers";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        //
        apiConnection = new APIConnection(getApplicationContext(), getResources().getString(R.string.api_base_url));

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

        // Bind progress dialog (this is deprecated).
        progressDialog = new ProgressDialog(this);
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
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                editTextDateOfBirth.setText(day + "/" + (month + 1) + "/" + year);
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
        String addressLineOne = editTextAddressLineOne.getText().toString();
        String postCode = editTextPostCode.getText().toString();
        String phoneNumber = editTextPhoneNumber.getText().toString();
        String emailAddress = editTextEmailAddress.getText().toString();
        String password = editTextPassword.getText().toString();

        // Check that all values have been entered.
        if (!firstName.equals("") && !lastName.equals("") && !dateOfBirth.equals("") && !postCode.equals("")
                && !addressLineOne.equals("") && !phoneNumber.equals("") && !emailAddress.equals("")
                && !password.equals("") && !editTextConfirmPassword.getText().toString().equals("")) {

            // Ensure that the password and the password confirmation matches.
            if (password.equals(editTextConfirmPassword.getText().toString())) {
                // Make a POST request to the Customers endpoint in the Web API
                // in order to send the details to create a new customer record.
                registerCustomer();
            } else {
                Toast.makeText(this, getResources().getString(R.string.passwordsMustBeSame), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, getResources().getString(R.string.fieldsEmpty), Toast.LENGTH_SHORT).show();
        }
    }


    /**
     *
     */
    private void registerCustomer() {

        // Get the values of the entered fields.
        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String dateOfBirth = editTextDateOfBirth.getText().toString();
        String addressLineOne = editTextAddressLineOne.getText().toString();
        String addressLineTwo = editTextAddressLineTwo.getText().toString();
        String postCode = editTextPostCode.getText().toString().toUpperCase();
        String phoneNumber = editTextPhoneNumber.getText().toString();
        String emailAddress = editTextEmailAddress.getText().toString();
        String password = editTextPassword.getText().toString();

        // Create the JSONObject to send in the POST request body.
        JSONObject postData = new JSONObject();
        try {
            postData.put("firstName", firstName);
            postData.put("lastName", lastName);

            // Parse the date and put it in the correct format to send to the Web API.
            try {
                Date initDate = new SimpleDateFormat("dd/mm/yyyy", Locale.UK).parse(dateOfBirth);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.UK);
                postData.put("dateOfBirth", formatter.format(initDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            postData.put("postCode", postCode);
            postData.put("addressLineOne", addressLineOne);
            postData.put("addressLineTwo", addressLineTwo);
            postData.put("mobileNumber", phoneNumber);
            postData.put("emailAddress", emailAddress);
            postData.put("customerPassword", password);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("Response", postData.toString());


        // Show progress dialog.
        progressDialog.setMessage("Registering Account");
        progressDialog.show();

        // Send the create account request.
        apiConnection.postSingleApiRequest("customers", postData, new VolleyCallback() {

            @Override
            public void onSuccess(APIResponse response) {
                try {
                    Log.d("Response", "Created user with id:" + response.getSingleResponse().get("customerId"));

                    // Show alert dialog with the registered information.
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CreateAccountActivity.this);
                    alertDialogBuilder.setMessage("Your account has been registered under the email address: " +
                            response.getSingleResponse().get("emailAddress") +
                            "\n\nYou can now proceed to login with your email address and the password you registered with.")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Return to the login screen.
                                    Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(startIntent);
                                }
                            })
                            .setCancelable(false);

                    AlertDialog alert = alertDialogBuilder.create();
                    alert.setTitle("Create Account");

                    // Hide the dialog box and show the alert.
                    progressDialog.hide();
                    alert.show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(APIResponse response) {
                Log.d("Error.Response", response.toString());

                progressDialog.hide();

                // Display the error to the user.
                // TODO: Place important toast messages in an alert dialog.
                switch (response.getResponseStatusCode()) {
                    case HttpURLConnection.HTTP_INTERNAL_ERROR:
                        Toast.makeText(getApplicationContext(), "An internal server error occurred whilst processing your request.", Toast.LENGTH_SHORT).show();
                        break;

                    case HttpURLConnection.HTTP_BAD_REQUEST:
                        Toast.makeText(getApplicationContext(), "The request was not made in the expected format.", Toast.LENGTH_SHORT).show();
                        break;

                    case HttpURLConnection.HTTP_CONFLICT:
                        Toast.makeText(getApplicationContext(), "An account is already registered with this email address.", Toast.LENGTH_SHORT).show();
                        break;

                    case HttpURLConnection.HTTP_NOT_FOUND:
                        Toast.makeText(getApplicationContext(), "There registered account record could not be found or there was error registering your details..", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}
