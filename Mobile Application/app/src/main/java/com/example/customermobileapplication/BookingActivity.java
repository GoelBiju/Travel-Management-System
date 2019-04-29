package com.example.customermobileapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customermobileapplication.Model.Journey;
import com.example.customermobileapplication.Utilities.API.APIConnection;
import com.example.customermobileapplication.Utilities.API.APIResponse;
import com.example.customermobileapplication.Utilities.API.Config;
import com.example.customermobileapplication.Utilities.API.CustomCallback;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;

public class BookingActivity extends AppCompatActivity {

    //
    private APIConnection apiConnection;

    //
    private int journeyId;

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

        // Get the journey id passed on.
        Intent prevIntent = getIntent();
        journeyId = prevIntent.getIntExtra("journeyId", 0);

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
                processPayment();
            }
        });

        // Load the journey information into the booking activity.
        loadJourneyInformation();
    }

    private void bindAndSetupViews() {

        //
        textViewJourneyId = findViewById(R.id.textViewJourneyId);
        textViewDepartureStation = findViewById(R.id.textViewDepartureStation);
        textViewDepartureTime = findViewById(R.id.textViewDepartureTime);
        textViewArrivalStation = findViewById(R.id.textViewArrivalStation);
        textViewArrivalTime = findViewById(R.id.textViewArrivalTime);
        textViewCustomerDepartureStop = findViewById(R.id.textViewCustomerDepartureStop);
        textViewCustomerDepartureStop = findViewById(R.id.textViewCustomerDepartureStop);

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
    }

    private void loadJourneyInformation() {

        apiConnection.getCustomJsonObject("journeys/" + this.journeyId, Journey.class, new CustomCallback() {
            @Override
            public void onSuccess(Object responseObject) {
                Log.d("Response", "Successfully received journey object.");

                // Get the journey object.
                Journey journey = (Journey) responseObject;

                // Update the journey information on the booking activity textviews.
                textViewJourneyId.setText(journey.getJourneyId());
            }

            @Override
            public void onFailure(APIResponse errorResponse) {

            }
        });
    }


    @Override
    protected void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
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
                    try {
                        // TODO: Create booking record by POST to the API with booking object and details.
                        String paymentDetails = confirmation.toJSONObject().toString(4);

                        startActivity(new Intent(this, BookingDetailsActivity.class)
                                .putExtra("PaymentDetails", paymentDetails)
                                .putExtra("PaymentAmount", amount)
                        );

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED){
                Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
            }
        } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
        }
    }
}
