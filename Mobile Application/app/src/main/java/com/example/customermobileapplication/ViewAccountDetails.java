package com.example.customermobileapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.customermobileapplication.Utilities.APIConnection;
import com.example.customermobileapplication.Utilities.APIResponse;
import com.example.customermobileapplication.Utilities.VolleyCallback;

import org.json.JSONException;

import java.net.HttpURLConnection;

public class ViewAccountDetails extends AppCompatActivity {

    APIConnection apiConnection;

    private TextView firstNameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_account_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // initialise api
        apiConnection = new APIConnection(getApplicationContext(), getResources().getString(R.string.api_base_url));

        firstNameInput = findViewById(R.id.firstNameInput);

        getAccountInformation();
    }

    private void getAccountInformation() {

        apiConnection.getSingleApiRequest("customers/11", new VolleyCallback() {
            @Override
            public void onSuccess(APIResponse response) {
                try {
                    firstNameInput.setText(response.getSingleResponse().get("firstName").toString());
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
