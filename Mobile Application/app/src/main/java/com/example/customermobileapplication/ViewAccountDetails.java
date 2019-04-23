package com.example.customermobileapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.customermobileapplication.Utilities.API.APIConnection;
import com.example.customermobileapplication.Utilities.API.APIResponse;
import com.example.customermobileapplication.Utilities.API.VolleyCallback;

import org.json.JSONException;

public class ViewAccountDetails extends AppCompatActivity {

    APIConnection apiConnection;

    int customerId;

    private TextView firstNameInput;
    private TextView lastNameInput;
    private TextView customerIDInput;
    private TextView dateOfBirthInput;
    private TextView addressLine1Input;
    private TextView addressLine2Input;
    private TextView postCodeInput;
    private TextView phoneNumberInput;
    private TextView emailInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_account_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // initialise api
        apiConnection = new APIConnection(getApplicationContext(), getResources().getString(R.string.api_base_url));

        //fields

        firstNameInput = findViewById(R.id.firstNameInput);

        lastNameInput = findViewById(R.id.lastNameInput);

        //customerIDInput = findViewById(R.id.customerIDInput);

        dateOfBirthInput = findViewById(R.id.dateOfBirthInput);

        addressLine1Input = findViewById(R.id.addressLine1Input);

        addressLine2Input = findViewById(R.id.addressLine2Input);

        postCodeInput = findViewById(R.id.postcodeInput);

        phoneNumberInput = findViewById(R.id.phoneNumberInput);

        emailInput = findViewById(R.id.emailInput);

        Intent getCustomerId = getIntent();
        customerId = getCustomerId.getIntExtra("customerId", 0);

        getAccountInformation();
    }

    private void getAccountInformation() {

        apiConnection.getSingleApiRequest("customers/" + this.customerId, new VolleyCallback() {
            @Override
            public void onSuccess(APIResponse response) {
                try {

                    //retrieve information

                    firstNameInput.setText(response.getSingleResponse().get("firstName").toString());

                    lastNameInput.setText(response.getSingleResponse().get("lastName").toString());

                    customerIDInput.setText(response.getSingleResponse().get("customerId").toString());

                    dateOfBirthInput.setText(response.getSingleResponse().get("dateOfBirth").toString());

                    addressLine1Input.setText(response.getSingleResponse().get("addressLineOne").toString());

                    addressLine2Input.setText(response.getSingleResponse().get("addressLineTwo").toString());

                    postCodeInput.setText(response.getSingleResponse().get("postCode").toString());

                    phoneNumberInput.setText(response.getSingleResponse().get("mobileNumber").toString());

                    emailInput.setText(response.getSingleResponse().get("emailAddress").toString());

                } catch(JSONException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(APIResponse response) {
                firstNameInput.setText("fail");
            }
        });
    }
}
