package com.example.customermobileapplication.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customermobileapplication.Adapter.BookingsAdapter;
import com.example.customermobileapplication.Model.Booking;
import com.example.customermobileapplication.R;
import com.example.customermobileapplication.Utilities.API.APIConnection;
import com.example.customermobileapplication.Utilities.API.APIResponse;
import com.example.customermobileapplication.Utilities.API.VolleyCallback;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CompletedBookingsFragment extends Fragment {

    private APIConnection apiConnection;

    private SharedPreferences pref;
    private int customerId;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private TextView textViewNoData;

    private List<Booking> listItems;

    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.active_bookings_fragment, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //
        pref = getActivity().getSharedPreferences("userDetails", Context.MODE_PRIVATE);
        customerId = pref.getInt("customerId", 0);

        progressDialog = new ProgressDialog(getActivity());

        apiConnection = new APIConnection(getActivity(), getResources().getString(R.string.api_base_url));

        //
        recyclerView = (RecyclerView) view.findViewById(R.id.activeBookingsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        textViewNoData = (TextView) view.findViewById(R.id.textViewNoData);

        loadCompletedBookings();
    }

    private void loadCompletedBookings() {

        if (customerId > 0) {
            //
            progressDialog.setMessage("Loading your active bookings...");
            progressDialog.show();

            apiConnection.getManyApiRequest("bookings/customer/" + this.customerId, new VolleyCallback() {
                @Override
                public void onSuccess(APIResponse response) {
                    Log.d("Response", "Bookings received successfully:" + response.getResponse().toString());

                    progressDialog.hide();

                    List<Booking> bookings = new ArrayList<>();
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

                    System.out.println("Response: " + response.getResponse());
                    for (JSONObject jsonObject : response.getResponse()) {
                        try {
                            Booking booking = gson.fromJson(jsonObject.toString(), Booking.class);

                            Log.d("Response", "Booking status: " + booking.getStatus());

                            if (booking.getStatus().equals("Complete")) {
                                Log.d("Response", "Added booking: " + booking.getBookingReference());
                                bookings.add(booking);
                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                            Log.d("Response", "Failed to convert json object: " + jsonObject);
                        }
                    }

                    // Show bookings as long as we receive some from the API.
                    if (!bookings.isEmpty()) {
                        //
                        adapter = new BookingsAdapter(bookings, getActivity());
                        ((BookingsAdapter) adapter).setActiveBookings(false);
                        recyclerView.setAdapter(adapter);
                        Log.d("Response", "Set adapter.");
                    } else {
                        textViewNoData.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(APIResponse response) {

                    progressDialog.hide();

                    Toast.makeText(getActivity(), "Failed to retrieve active bookings.", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
