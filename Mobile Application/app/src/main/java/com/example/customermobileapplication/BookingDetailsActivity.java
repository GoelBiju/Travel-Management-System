package com.example.customermobileapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class BookingDetailsActivity extends AppCompatActivity {

    // Transaction information.
    //TextView textId, textAmount, textStatus;

    // Confirmation information.
    private int bookingReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        //textId = (TextView)findViewById(R.id.textId);
        //textAmount = (TextView)findViewById(R.id.textAmount);
        //textStatus = (TextView)findViewById(R.id.textStatus);

        // Get the intent.
        Intent intent = getIntent();
        this.bookingReference = intent.getIntExtra("bookingReference", 0);

//        try {
//            JSONObject jsonObject = new JSONObject(intent.getStringExtra("PaymentDetails"));
//            showDetails(jsonObject.getJSONObject("response"), intent.getStringExtra("PaymentAmount"));
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }

//    private void showDetails(JSONObject response, String paymentAmount) {
//        try {
//            textId.setText(response.getString("id"));
//            textStatus.setText(response.getString("state"));
//            textAmount.setText("£" + paymentAmount);
//
//            Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
}
