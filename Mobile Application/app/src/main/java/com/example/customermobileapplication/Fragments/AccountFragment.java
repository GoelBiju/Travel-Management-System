package com.example.customermobileapplication.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customermobileapplication.LoginActivity;
import com.example.customermobileapplication.Model.Customer;
import com.example.customermobileapplication.R;
import com.example.customermobileapplication.Utilities.API.APIConnection;
import com.example.customermobileapplication.Utilities.API.APIResponse;
import com.example.customermobileapplication.Utilities.API.CustomCallback;
import com.example.customermobileapplication.Utilities.API.Helpers;
import com.example.customermobileapplication.Utilities.API.VolleyCallback;

import org.json.JSONException;

public class AccountFragment extends Fragment {

    // APIConnection.
    private APIConnection apiConnection;

    // Bindable TextViews.
    private TextView firstNameInput;
    private TextView lastNameInput;
    private TextView customerIDInput;
    private TextView dateOfBirthInput;
    private TextView addressLine1Input;
    private TextView addressLine2Input;
    private TextView postCodeInput;
    private TextView mobileNumberInput;
    private TextView emailInput;

    // User details in SharedPreferences.
    private SharedPreferences pref;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.activity_view_account_details, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialise the API.
        apiConnection = new APIConnection(getActivity(), getResources().getString(R.string.api_base_url));

        // Initialise SharedPreferences.
        pref = getActivity().getSharedPreferences("userDetails", Context.MODE_PRIVATE);

        // Bind TextViews to the layout.
        firstNameInput = view.findViewById(R.id.firstNameInput);
        lastNameInput = view.findViewById(R.id.lastNameInput);
        //customerIDInput = view.findViewById(R.id.customerIDInput);
        dateOfBirthInput = view.findViewById(R.id.dateOfBirthInput);
        addressLine1Input = view.findViewById(R.id.addressLine1Input);
        addressLine2Input = view.findViewById(R.id.addressLine2Input);
        postCodeInput = view.findViewById(R.id.postcodeInput);
        mobileNumberInput = view.findViewById(R.id.phoneNumberInput);
        emailInput = view.findViewById(R.id.emailInput);

        // Bind the log out button.
        // Cannot use findViewById directly, call view.findViewById/getView()? to use it.
        view.findViewById(R.id.buttonLogOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // No getApplicationContext() in fragment, instead use getActivity().
                Toast.makeText(getActivity(), "You are logged out.", Toast.LENGTH_SHORT).show();

                // Clear the SharedPreferences.
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.apply();

                // Clear the apiConnection access token.
                apiConnection.clearPreferences();

                // Return to login screen.
                Intent startIntent = new Intent(getActivity(), LoginActivity.class);
                startActivity(startIntent);

                // Finish this fragment.
                getActivity().finish();
            }
        });

        // Get the user details from the API.
        getAccountInformation();
    }


    /**
     *
     */
    private void getAccountInformation() {

        int customerId = pref.getInt("customerId", 0);

        if (customerId > 0) {

            apiConnection.getCustomJsonObject("customers/" + customerId, Customer.class ,
                    new CustomCallback() {
                        @Override
                        public void onSuccess(Object responseObject) {
                            // Get Customer object from response.
                            Customer accountDetails = (Customer) responseObject;

                            firstNameInput.setText(accountDetails.getFirstName());

                            lastNameInput.setText(accountDetails.getLastName());

                            //customerIDInput.setText(accountDetails.getCustomerId());

                            dateOfBirthInput.setText(Helpers.formatDateOnly(accountDetails.getDateOfBirth()));

                            addressLine1Input.setText(accountDetails.getAddressLineOne());

                            addressLine2Input.setText(accountDetails.getAddressLineTwo());

                            postCodeInput.setText(accountDetails.getPostCode());

                            mobileNumberInput.setText(accountDetails.getMobileNumber());

                            emailInput.setText(accountDetails.getEmailAddress());
                        }

                        @Override
                        public void onFailure(APIResponse errorResponse) {
                            // Handle if error from the API.
                            Toast.makeText(getActivity(), "Error Response: " + errorResponse.toString(), Toast.LENGTH_LONG).show();
                        }
                    });

//            apiConnection.getSingleApiRequest("customers/" + this.customerId, new VolleyCallback() {
//                @Override
//                public void onSuccess(APIResponse response) {
//                    try {
//
//                        //retrieve information
//
//                        firstNameInput.setText(response.getSingleResponse().get("firstName").toString());
//
//                        lastNameInput.setText(response.getSingleResponse().get("lastName").toString());
//
//                        customerIDInput.setText(response.getSingleResponse().get("customerId").toString());
//
//                        dateOfBirthInput.setText(response.getSingleResponse().get("dateOfBirth").toString());
//
//                        addressLine1Input.setText(response.getSingleResponse().get("addressLineOne").toString());
//
//                        addressLine2Input.setText(response.getSingleResponse().get("addressLineTwo").toString());
//
//                        postCodeInput.setText(response.getSingleResponse().get("postCode").toString());
//
//                        mobileNumberInput.setText(response.getSingleResponse().get("mobileNumber").toString());
//
//                        emailInput.setText(response.getSingleResponse().get("emailAddress").toString());
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void onFailure(APIResponse response) {
//                    firstNameInput.setText("fail");
//                }
//            });
        } else {
            Toast.makeText(getActivity(), "No user details could be found.", Toast.LENGTH_SHORT).show();
        }
    }
}
