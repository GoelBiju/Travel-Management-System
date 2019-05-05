package com.example.customermobileapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.constraint.solver.widgets.Helper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customermobileapplication.BindingModels.BookingCreationBindingModel;
import com.example.customermobileapplication.Model.Booking;
import com.example.customermobileapplication.Model.Journey;
import com.example.customermobileapplication.Model.Stop;
import com.example.customermobileapplication.Utilities.API.APIConnection;
import com.example.customermobileapplication.Utilities.API.APIResponse;
import com.example.customermobileapplication.Utilities.API.Config;
import com.example.customermobileapplication.Utilities.API.CustomCallback;
import com.example.customermobileapplication.Utilities.API.Helpers;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BookingActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener {

    //
    private APIConnection apiConnection;

    //
    private SharedPreferences pref;

    //
    private int customerId;
    private int journeyId;

    private int customerDepartureStopId;
    private int customerArrivalStopId;

    //EditText editTextAmount;
    String amount = "";

    //
    private TextView textViewJourneyId;
    private TextView textViewDepartureStation;
    private TextView textViewDepartureTime;
    private TextView textViewArrivalStation;
    private TextView textViewArrivalTime;
    private TextView textViewCustomerDepartureStop;
    private TextView textViewCustomerArrivalStop;

    private TextView textViewAmountToPay;

    // TODO: NumberPicker.
    private NumberPicker seniorsPicker;
    private NumberPicker adultsPicker;
    private NumberPicker childrenPicker;
    private NumberPicker infantsPicker;

    Button buttonPayNow;

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.UK);

    public static final int PAYPAL_REQUEST_CODE = 7171;

    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(Config.PAYPAL_CLIENT_ID);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // API Connection.
        apiConnection = new APIConnection(getApplicationContext(), getResources().getString(R.string.api_base_url));

        //
        pref = getApplicationContext().getSharedPreferences("userDetails", MODE_PRIVATE);
        customerId = pref.getInt("customerId", 0);

        // Get the journey id passed on.
        Intent prevIntent = getIntent();
        journeyId = prevIntent.getIntExtra("journeyId", 0);
        customerDepartureStopId = prevIntent.getIntExtra("departureStopId", 0);
        customerArrivalStopId = prevIntent.getIntExtra("arrivalStopId", 0);
        Log.d("Response", "Passed on Journey ID: " + journeyId);

        // Start the PayPal service.
        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);

        // Bind all fields and set them up for use.
        bindAndSetupViews();

        // Handle booking event.
        buttonPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceedToPayment();
            }
        });

        // Load the journey and stop information into the booking activity.
        loadJourneyInformation();
        loadStopInformation();
    }


    @Override
    protected void onResume() {
        super.onResume();

        // Reload journey and stop information if the activity is resumed.
        loadJourneyInformation();
        loadStopInformation();
    }

    private void bindAndSetupViews() {

        //
        textViewJourneyId = findViewById(R.id.textViewJourneyId);
        textViewDepartureStation = findViewById(R.id.textViewDepartureStation);
        textViewDepartureTime = findViewById(R.id.textViewDepartureTime);
        textViewArrivalStation = findViewById(R.id.textViewArrivalStation);
        textViewArrivalTime = findViewById(R.id.textViewArrivalTime);
        textViewCustomerDepartureStop = findViewById(R.id.textViewCustomerDepartureStop);
        textViewCustomerArrivalStop = findViewById(R.id.textViewCustomerArrivalStop);

        textViewAmountToPay = findViewById(R.id.textViewAmountToPay);

        //
        adultsPicker = findViewById(R.id.numberPickerAdults);
        seniorsPicker = findViewById(R.id.numberPickerSeniors);
        childrenPicker = findViewById(R.id.numberPickerChildren);
        infantsPicker = findViewById(R.id.numberPickerInfants);

        //
        buttonPayNow = (Button)findViewById(R.id.buttonConfirmBooking);
        //editTextAmount = (EditText)findViewById(R.id.editAmount);

        //
        textViewAmountToPay.setText("0");

        //
        adultsPicker.setMinValue(0);
        seniorsPicker.setMinValue(0);
        childrenPicker.setMinValue(0);
        infantsPicker.setMinValue(0);

        adultsPicker.setMaxValue(20);
        seniorsPicker.setMaxValue(10);
        childrenPicker.setMaxValue(10);
        infantsPicker.setMaxValue(5);

        adultsPicker.setWrapSelectorWheel(false);
        seniorsPicker.setWrapSelectorWheel(false);
        childrenPicker.setWrapSelectorWheel(false);
        infantsPicker.setWrapSelectorWheel(false);

        // Set fixed fares for now.
        // TODO: Fares and prices should be API request and select should be done before-hand.
        adultsPicker.setOnValueChangedListener(this);
        seniorsPicker.setOnValueChangedListener(this);
        childrenPicker.setOnValueChangedListener(this);
        infantsPicker.setOnValueChangedListener(this);
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        double totalToPay = (adultsPicker.getValue() * 5) + (seniorsPicker.getValue() * 2.5) +
                (childrenPicker.getValue() * 3) + (infantsPicker.getValue());
        textViewAmountToPay.setText(String.format("%.2f", totalToPay));
    }

    private void loadJourneyInformation() {

        apiConnection.getCustomJsonObject("journeys/" + this.journeyId, Journey.class, new CustomCallback() {
            @Override
            public void onSuccess(Object responseObject) {
                Log.d("Response", "Successfully received journey object.");

                // Get the journey object.
                Journey journey = (Journey) responseObject;
                Log.d("Response", "Journey object received: " + journey.getJourneyId());

                // Update the journey information on the booking activity textviews.
                textViewJourneyId.setText(Integer.toString(journey.getJourneyId()));
                textViewDepartureStation.setText(journey.getRoute().getDepartureStation());
                textViewDepartureTime.setText(Helpers.formatDateTime(journey.getDepartureDateTime()));
                textViewArrivalStation.setText(journey.getRoute().getArrivalStation());
                textViewArrivalTime.setText(Helpers.formatDateTime(journey.getArrivalDateTime()));
            }

            @Override
            public void onFailure(APIResponse errorResponse) {
                // Show alert dialog with the error information.
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(BookingActivity.this);
                alertDialogBuilder.setMessage("The journey you have selected is no longer valid or " +
                        "there was an error fetching details in order to make a booking.")
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
                alert.setTitle("Journey Error");
                alert.show();
            }
        });
    }


    private void loadStopInformation() {

        apiConnection.getCustomJsonObject("stops/" + this.customerDepartureStopId, Stop.class, new CustomCallback() {
            @Override
            public void onSuccess(Object responseObject) {
                Log.d("Response", "Successfully received stop object.");

                Stop departureStop = (Stop) responseObject;
                Log.d("Response", "Stop object received: " + departureStop.getStopId());

                textViewCustomerDepartureStop.setText(departureStop.getStopName());
            }

            @Override
            public void onFailure(APIResponse errorResponse) {
                Log.d("Response", "Failed to retrieve stop with id: " + customerDepartureStopId);
            }
        });

        apiConnection.getCustomJsonObject("stops/" + this.customerArrivalStopId, Stop.class, new CustomCallback() {
            @Override
            public void onSuccess(Object responseObject) {
                Log.d("Response", "Successfully received stop object.");

                Stop arrivalStop = (Stop) responseObject;
                Log.d("Response", "Stop object received: " + arrivalStop.getStopId());

                textViewCustomerArrivalStop.setText(arrivalStop.getStopName());
            }

            @Override
            public void onFailure(APIResponse errorResponse) {
                Log.d("Response", "Failed to retrieve stop with id: " + customerArrivalStopId);
            }
        });
    }


    @Override
    protected void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }

    private void proceedToPayment() {
        // Show alert dialog with the error information.
        amount = textViewAmountToPay.getText().toString();
        if (Double.parseDouble(amount) > 0) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(BookingActivity.this);
            alertDialogBuilder.setMessage("Pay now via PayPal for your booking? \n\nPlease ensure that booking details are accurate.")
                    .setPositiveButton("Pay Now", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            processPayment();
                        }
                    })
                    .setNegativeButton("Go Back", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setCancelable(false);

            AlertDialog alert = alertDialogBuilder.create();
            alert.setTitle("Confirm Booking");
            alert.show();
        } else {
            Toast.makeText(getApplicationContext(), "Please select passengers for your booking.", Toast.LENGTH_SHORT).show();
        }
    }

    private void processPayment() {
        // Get the amount from the final amount textview.
        //amount = editTextAmount.getText().toString();
        amount = textViewAmountToPay.getText().toString();
        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(String.valueOf(amount)), "GBP",
                "Payment for your booking.", PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payPalPayment);
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PAYPAL_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirmation != null) {
                    Log.d("Response", "PayPal confirmation received.");
                    try {
                        // TODO: Create booking record by POST to the API with booking object and details.
                        JSONObject paymentConfirmation = confirmation.toJSONObject();
                        String paymentDetails = paymentConfirmation.toString(4);

                        processBooking(paymentConfirmation);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.d("Response", "PayPal confirmation was null.");
                }
            } else if (resultCode == Activity.RESULT_CANCELED){
                Toast.makeText(getApplicationContext(), "Cancelled payment.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Unknown response result code: " + resultCode, Toast.LENGTH_SHORT).show();
            }
        } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            Toast.makeText(this, "Response error - Invalid result from PayPal payment.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Request error: " + resultCode, Toast.LENGTH_SHORT).show();
        }
    }


    /**
     *
     * @param paymentConfirmation
     */
    private void processBooking(JSONObject paymentConfirmation) {
        //
        if (this.customerId > 0) {
            pref = getApplicationContext().getSharedPreferences("userDetails", MODE_PRIVATE);

            // Create the booking binding model.
            BookingCreationBindingModel customerBooking = new BookingCreationBindingModel();
            customerBooking.setCustomerId(this.customerId);
            customerBooking.setJourneyId(this.journeyId);
            customerBooking.setDepartingStopId(this.customerDepartureStopId);
            customerBooking.setArrivalStopId(this.customerArrivalStopId);

            // Get the create time from the payment confirmation response.
            Log.d("Response", paymentConfirmation.toString());
            try {
                Log.d("Response", "Create Time: " + paymentConfirmation.getJSONObject("response").getString("create_time"));
                String createTime = paymentConfirmation.getJSONObject("response").getString("create_time");
                Date bookedDateTime = Helpers.toAPIDateTime(createTime);

                //customerBooking.setBookedDateTime(new Date());
                customerBooking.setBookedDateTime(bookedDateTime);
            } catch (JSONException e) {
                e.printStackTrace();

                // In the event of an exception getting the create time from the PayPal API.
                // then just use the current time.
                customerBooking.setBookedDateTime(new Date());
            }

            // Get the PayPal transaction id in the event that the booking is altered or refunded(? - Need to confirm API)
            try {
                Log.d("Response", paymentConfirmation.getJSONObject("response").getString("id"));
                customerBooking.setPaymentId(paymentConfirmation.getJSONObject("response").getString("id"));
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("Response","Payment Id was not found in the JSON Object.");
                customerBooking.setPaymentId("N/A");
            }

            customerBooking.setNumOfSeniors(seniorsPicker.getValue());
            customerBooking.setNumOfAdults(adultsPicker.getValue());
            customerBooking.setNumOfChildren(childrenPicker.getValue());
            customerBooking.setNumOfInfants(infantsPicker.getValue());
            customerBooking.setAmountPaid(Float.parseFloat(textViewAmountToPay.getText().toString()));

            // Send the booking to the API.
            sendBooking(customerBooking);

            Log.d("Response", "Sent booking to API.");
        } else {
            Log.d("Response", "Unable to process booking as customer id was not found.");
        }
    }

    private void sendBooking(BookingCreationBindingModel bookingData) {

        // Call the api to add the booking.
        apiConnection.postCustomJsonObject("bookings", bookingData, Booking.class, new CustomCallback() {
            @Override
            public void onSuccess(Object responseObject) {
                //
                Booking confirmedBooking = (Booking) responseObject;

                Log.d("Response", "Confirmed booking with booking reference: " + confirmedBooking.getBookingReference());
                Toast.makeText(getApplicationContext(), "Booking confirmed with booking reference: " +
                        confirmedBooking.getBookingReference(), Toast.LENGTH_SHORT).show();

                // Start the Booking Details activity to show confirmed information.
                startActivity(new Intent(BookingActivity.this, BookingDetailsActivity.class)
                        .putExtra("bookingReference", confirmedBooking.getBookingReference())
                );

                finish();
            }

            @Override
            public void onFailure(APIResponse errorResponse) {
                Log.d("Response", "Unable to post booking to API.");
            }
        });
    }
}
