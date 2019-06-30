package com.example.customermobileapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.ReceiverCallNotAllowedException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.customermobileapplication.Adapter.JourneySearchAdapter;
import com.example.customermobileapplication.Adapter.StopSpinAdapter;
import com.example.customermobileapplication.Model.Journey;
import com.example.customermobileapplication.Model.JourneyListItem;
import com.example.customermobileapplication.Model.RouteStop;
import com.example.customermobileapplication.Model.Stop;
import com.example.customermobileapplication.Utilities.API.APIConnection;
import com.example.customermobileapplication.Utilities.API.APIResponse;
import com.example.customermobileapplication.Utilities.API.VolleyCallback;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JourneySearchActivity extends AppCompatActivity {

    private APIConnection apiConnection;

    private int customerDepartureStop;
    private int customerArrivalStop;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<Journey> listItems;

    private ProgressDialog progressDialog;

//    private List<Journey> journeys;
//    private boolean filteredStops;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey_search);

        progressDialog = new ProgressDialog(this);

        //
        apiConnection = new APIConnection(getApplicationContext(), getResources().getString(R.string.api_base_url));

        //
        Intent prevIntent = getIntent();
        customerDepartureStop = prevIntent.getIntExtra("departureStopId", 0);
        customerArrivalStop = prevIntent.getIntExtra("arrivalStopId", 0);

        //
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        listItems = new ArrayList<>();
//        for (int i = 0; i <= 10; i++) {
//            JourneyListItem listItem = new JourneyListItem();
//            listItem.setJourneyTitle((i + 1) + " Plymouth to Bristol");
//            listItem.setCoachStatus("Scheduled");
//
//            listItems.add(listItem);
//        }

        // Create the adapter and set it.
//        adapter = new JourneySearchAdapter(listItems, this);
//        recyclerView.setAdapter(adapter);

        // TODO: Load the journeys available to book from the zearch parameters.
        // Create a SearchJourneysBindingModel to send to API with station Ids, DateTime fields.
        // Get the search parameters passed.


//        journeys = new ArrayList<>();

//        filteredStops = false;

        loadJourneys();
    }

    private void loadJourneys() {

        // TODO: Create JourneySearchBindingModel from search parameters.

        progressDialog.setMessage("Loading Your Journeys...");
        progressDialog.show();

        // Call the API to fetch the relevant journeys.
        // Call the api to get all stops.
        apiConnection.getManyApiRequest("journeys", new VolleyCallback() {
            @Override
            public void onSuccess(APIResponse response) {

                Log.d("Response", "Journeys received successfully.");

//                progressDialog.hide();

                // Convert JSONArray response to Journeys[].
                List<Journey> journeys = new ArrayList<>();
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

                for (JSONObject jsonObject : response.getResponse()) {
                    // Convert each JSONObject to journey object.
                    try {
                        Journey journey = gson.fromJson(jsonObject.toString(), Journey.class);
//                        filterJourney(journey);

                        journeys.add(journey);
                        Log.d("Response", "Added Journey: " + journey.journeyId);
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                        Log.d("Response", "Failed to convert json object: " + jsonObject);
                    }
                }

//                progressDialog.hide();

                //
                adapter = new JourneySearchAdapter(journeys, JourneySearchActivity.this);
                ((JourneySearchAdapter) adapter).setCustomerDepartureStopId(customerDepartureStop);
                ((JourneySearchAdapter) adapter).setCustomerArrivalStopId(customerArrivalStop);
//
                recyclerView.setAdapter(adapter);
                Log.d("Response", "Set adapter.");

                progressDialog.hide();
            }

            @Override
            public void onFailure(APIResponse response) {

                progressDialog.hide();

                Toast.makeText(getApplicationContext(), "Failed to retrieve journeys.", Toast.LENGTH_LONG).show();

                startActivity(new Intent(JourneySearchActivity.this, NavigationActivity.class));

                finish();
            }
        });
    }


//    private void filterJourney(final Journey journey) {
//
//        apiConnection.getManyApiRequest("routestops/" + journey.getRouteId(), new VolleyCallback() {
//            @Override
//            public void onSuccess(APIResponse response) {
//                Log.d("Response", "Route stops received successfully.");
//
//                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
//
//                //
//                boolean routeHasDepartureStop = false;
//                boolean routeHasArrivalStop = false;
//
//                for (JSONObject jsonObject : response.getResponse()) {
//                    // Convert each JSONObject to journey object.
//                    try {
//                        RouteStop routeStop = gson.fromJson(jsonObject.toString(), RouteStop.class);
//
//                        // Check to see if the departure stop and arrival stop is present in the route.
//                        if (routeStop.getStopId() == customerDepartureStop) {
//                            routeHasDepartureStop = true;
//                        } else if (routeStop.getStopId() == customerArrivalStop) {
//                            routeHasArrivalStop = true;
//                        }
//                    } catch (JsonSyntaxException e) {
//                        e.printStackTrace();
//                        Log.d("Response", "Failed to convert json object: " + jsonObject);
//                    }
//                }
//
//                // If the route contains stops with the departure and arrival,
//                // then add it to the journey list to show.
//                if (routeHasDepartureStop && routeHasArrivalStop){
//                    journeys.add(journey);
//                    Log.d("Response", "Found route with matching departure and arrival stops.");
//                }
//            }
//
//            @Override
//            public void onFailure(APIResponse response) {
//                Log.d("Response", "Unable to receive route stops for journey: " + journey.getJourneyId());
//
//                progressDialog.hide();
//            }
//        });
//
//        Log.d("Response", "Filtered journey.");
//    }
}
