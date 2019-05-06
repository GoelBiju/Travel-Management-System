package com.example.customermobileapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customermobileapplication.Model.Booking;
import com.example.customermobileapplication.Utilities.API.APIConnection;
import com.example.customermobileapplication.Utilities.API.APIResponse;
import com.example.customermobileapplication.Utilities.API.CustomCallback;
import com.example.customermobileapplication.Utilities.API.Helpers;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class BookingDetailsActivity extends AppCompatActivity {

    private APIConnection apiConnection;

    // Confirmation information.
    private int bookingReference;

    // Transaction information.
    private TextView textViewBookingReference;
    private TextView textViewJourneyId;
    private TextView textViewDepartingStop;
    private TextView textViewDepartureTime;
    private TextView textViewArrivalStop;
    private TextView textViewArrivalTime;
    private TextView textViewNumPassenvers;
    private TextView textViewBookedDateTime;
    private TextView textViewAmountPaid;

    private Button buttonHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        apiConnection = new APIConnection(getApplicationContext(), getResources().getString(R.string.api_base_url));

        // Get the intent.
        Intent intent = getIntent();
        bookingReference = intent.getIntExtra("bookingReference", 0);

        bindViews();

//        try {
//            JSONObject jsonObject = new JSONObject(intent.getStringExtra("PaymentDetails"));
//            showDetails(jsonObject.getJSONObject("response"), intent.getStringExtra("PaymentAmount"));
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        loadBookingInformation();
    }

    private void loadBookingInformation() {

        if (bookingReference > 0) {

            apiConnection.getCustomJsonObject("bookings/" + this.bookingReference, Booking.class, new CustomCallback() {
                @Override
                public void onSuccess(Object responseObject) {
                    Log.d("Response", "Received booking information.");

                    Booking booking = (Booking) responseObject;

                    System.out.println("Retrieved booking information for booking: " + booking.getBookingReference());

                    // Update the view with the retrieved information.
                    textViewBookingReference.setText(Integer.toString(bookingReference));
                    textViewJourneyId.setText(Integer.toString(booking.getJourney().getJourneyId()));
                    textViewDepartingStop.setText(booking.getDepartingStop().getStopName());
                    textViewArrivalStop.setText(booking.getArrivalStop().getStopName());

                    //                textViewDepartureTime.setText();
                    //                textViewArrivalTime.setText();

                    int numberOfPassengers = booking.getNumOfAdults() + booking.getNumOfSeniors() +
                            booking.getNumOfChildren() + booking.getNumOfInfants();
                                    Log.d("Response", "Number of passengers: " + numberOfPassengers);
                    textViewNumPassenvers.setText(Integer.toString(numberOfPassengers));
                    textViewBookedDateTime.setText(Helpers.formatDateTime(booking.getBookedDateTime()));
                    textViewAmountPaid.setText(String.format("%.2f", booking.getAmountPaid()));
                }

                @Override
                public void onFailure(APIResponse errorResponse) {
                    Log.d("Response", "Unable to retrieve booking record for: " + bookingReference);

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                    alertDialogBuilder.setMessage("The booking you have selected is no longer valid or " +
                            "there was an error fetching the details for this booking.")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Return to the login screen.
                                    Intent startIntent = new Intent(getApplicationContext(), NavigationActivity.class);
                                    startActivity(startIntent);

                                    // Finish this activity.
                                    finish();
                                }
                            })
                            .setCancelable(false);

                    AlertDialog alert = alertDialogBuilder.create();
                    alert.setTitle("Booking Error");
                    alert.show();
                }
            });
        }
    }

    private void bindViews() {

        textViewBookingReference = (TextView) findViewById(R.id.textViewBookingReference);
        textViewJourneyId = (TextView) findViewById(R.id.textViewJourneyId);
        textViewDepartingStop = (TextView) findViewById(R.id.textViewDepartingStop);
        textViewArrivalStop = (TextView) findViewById(R.id.textViewArrivalStop);

        // TODO: Change Bookings controller in API to get expected arrival time from route-stops.
        textViewDepartureTime = (TextView) findViewById(R.id.textViewDepartureTime);
        textViewDepartureTime = (TextView) findViewById(R.id.textViewArrivalTime);

        textViewNumPassenvers = (TextView) findViewById(R.id.textViewNumPassengers);
        textViewBookedDateTime = (TextView) findViewById(R.id.textViewBookedDateTime);
        textViewAmountPaid = (TextView) findViewById(R.id.textViewAmountPaid);

        buttonHome = (Button) findViewById(R.id.buttonHome);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NavigationActivity.class));

                finish();
            }
        });
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
