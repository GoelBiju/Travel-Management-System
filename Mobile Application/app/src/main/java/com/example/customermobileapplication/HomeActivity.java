package com.example.customermobileapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customermobileapplication.Utilities.APIConnection;
import com.example.customermobileapplication.Utilities.APIResponse;

import org.w3c.dom.Text;

import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {

    private int customerId;

    private EditText editTextDate;
    private EditText editTextTime;
    private TextView requestText;
    private APIConnection apiConnection;
    private DatePickerDialog datePickerDialog;

    // Buttons.
    private Button viewAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get the intent that was sent
        Intent customerIdIntent = getIntent();
        customerId = customerIdIntent.getIntExtra("customerId", 0);

        // Bind views.
        BindView();

        setViewActions();
        prepareDatePickerDialog();
    }


    private void BindView(){
        this.viewAccountButton = findViewById(R.id.buttonViewAccount);
        this.editTextDate = findViewById(R.id.editTextDate);
    }


    private void setViewActions() {

        viewAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewAccountDetailsIntent = new Intent(getApplicationContext(), ViewAccountDetails.class);
                viewAccountDetailsIntent.putExtra("customerId", customerId);

                startActivity(viewAccountDetailsIntent);
            }
        });
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
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
}
