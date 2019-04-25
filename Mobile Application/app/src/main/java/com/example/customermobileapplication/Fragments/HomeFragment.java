package com.example.customermobileapplication.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.customermobileapplication.Adapter.StopSpinAdapter;
import com.example.customermobileapplication.ConfirmBookingActivity;
import com.example.customermobileapplication.Model.Stop;
import com.example.customermobileapplication.R;
import com.example.customermobileapplication.Utilities.API.APIConnection;
import com.example.customermobileapplication.Utilities.API.APIResponse;
import com.example.customermobileapplication.Utilities.API.VolleyCallback;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeFragment extends Fragment {

    private APIConnection apiConnection;

    private Spinner spinnerDepartureStop, spinnerArrivalStop;
    private StopSpinAdapter departureAdapter, arrivalAdapter;

    private EditText editTextDate;
    private EditText editTextTime;

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.content_home, null);
    }

    // NOTE: Similar to the onCreate method of an Activity.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //
        apiConnection = new APIConnection(getActivity(), getResources().getString(R.string.api_base_url));

        //
        spinnerDepartureStop = view.findViewById(R.id.spinnerDepartureStop);
        spinnerArrivalStop = view.findViewById(R.id.spinnerArrivalStop);

        //
        editTextDate = view.findViewById(R.id.editTextDate);
        editTextTime = view.findViewById(R.id.editTextTime);

        //
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

        prepareDatePickerDialog();
        prepareTimePickerDialog();

        //
        view.findViewById(R.id.buttonViewBookings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getActivity(), ConfirmBookingActivity.class);
                startActivity(startIntent);
            }
        });


        // Load stops into the departure and arrival spinners.
        loadSpinnerOptions();
    }

    private void prepareDatePickerDialog() {
        // Get current date.
        Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
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

        timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                editTextTime.setText( hourOfDay + ":" + minute);
            }
        }, hour, minute, true );
        timePickerDialog.setTitle("Select Time");
    }

    private void loadSpinnerOptions() {

        // Call the api to get all stops.
        apiConnection.getManyApiRequest("stops", new VolleyCallback() {
            @Override
            public void onSuccess(APIResponse response) {

                // Convert JSONArray response to stops[].
                ArrayList<Stop> stops = new ArrayList<>();
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

                for (JSONObject jsonObject : response.getResponse()) {
                    // Convert each JSONObject to stop object.
                    Stop stop = gson.fromJson(jsonObject.toString(), Stop.class);
                    stops.add(stop);
                }

                //
                departureAdapter = new StopSpinAdapter(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, stops);
                arrivalAdapter = new StopSpinAdapter(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, stops);
            }

            @Override
            public void onFailure(APIResponse response) {
                Toast.makeText(getActivity().getApplicationContext(), "Failed to retrieve stops.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
