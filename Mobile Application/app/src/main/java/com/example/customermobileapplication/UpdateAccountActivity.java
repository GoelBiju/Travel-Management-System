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

import com.example.customermobileapplication.BindingModels.CustomerRegistrationBindingModel;
import com.example.customermobileapplication.Model.Customer;
import com.example.customermobileapplication.Utilities.API.APIConnection;
import com.example.customermobileapplication.Utilities.API.APIResponse;
import com.example.customermobileapplication.Utilities.API.CustomCallback;
import com.example.customermobileapplication.Utilities.API.VolleyCallback;

import org.json.JSONObject;
import org.json.JSONException;

import java.net.HttpURLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;



public class UpdateAccountActivity extends Activity {

    APIConnection apiConnection;

    private EditText firstName;
    private EditText lastName;
    private EditText addressLineOne;
    private EditText addressLineTwo;
    private EditText postCode;
    private EditText mobileNumber;
    private EditText emailAddress;
    private EditText password;
    private EditText passwordConfirmation;



}
