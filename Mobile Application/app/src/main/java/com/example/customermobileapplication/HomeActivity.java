package com.example.customermobileapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.customermobileapplication.Model.Customer;
import com.example.customermobileapplication.Utilities.API.APIConnection;
import com.example.customermobileapplication.Utilities.API.APIResponse;
import com.example.customermobileapplication.Utilities.API.CustomCallback;
import com.example.customermobileapplication.Utilities.API.VolleyCallback;

import java.net.HttpURLConnection;
import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {

    private EditText editTextDate;
    private EditText editTextTime;
    private TextView requestText;

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    // Buttons.
    private Button viewAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Bind views.
        BindView();

        setViewActions();
        prepareDatePickerDialog();
        prepareTimePickerDialog();
    }

    private void BindView(){
        //this.viewAccountButton = findViewById(R.id.buttonViewAccount);
        this.editTextDate = findViewById(R.id.editTextDate);
        this.editTextTime = findViewById(R.id.editTextTime);
    }


    private void setViewActions() {

//        viewAccountButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent viewAccountDetailsIntent = new Intent(getApplicationContext(), ViewAccountDetailsActivity.class);
//                //viewAccountDetailsIntent.putExtra("customerId", customerId);
//                startActivity(viewAccountDetailsIntent);
//            }
//        });

        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
        editTextTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });
    }

    private void prepareDatePickerDialog() {
        // Get current date.
        Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                editTextDate.setText(day + "/" + (month + 1) + "/" + year);
                datePickerDialog.dismiss();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    private void prepareTimePickerDialog()
    {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        timePickerDialog = new TimePickerDialog(HomeActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                editTextTime.setText( hourOfDay + ":" + minute);
            }
        }, hour, minute, true );
        timePickerDialog.setTitle("Select Time");
    }
}
