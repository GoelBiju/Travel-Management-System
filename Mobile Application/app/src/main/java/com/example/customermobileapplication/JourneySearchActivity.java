package com.example.customermobileapplication;

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

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<Journey> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey_search);

        //
        apiConnection = new APIConnection(getApplicationContext(), getResources().getString(R.string.api_base_url));

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


        loadJourneys();
    }

    private void loadJourneys() {
        // Create JourneySearchBindingModel from search parameters.

        // Call the API to fetch the relevant journeys.
        // Call the api to get all stops.
        apiConnection.getManyApiRequest("journeys", new VolleyCallback() {
            @Override
            public void onSuccess(APIResponse response) {

                Log.d("Response", "Journeys received successfully.");

                // Convert JSONArray response to Journeys[].
                List<Journey> journeys = new ArrayList<>();
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

                for (JSONObject jsonObject : response.getResponse()) {
                    // Convert each JSONObject to journey object.
                    try {
                        Journey journey = gson.fromJson(jsonObject.toString(), Journey.class);
                        Log.d("Response", "Added Journey: " + journey.journeyId);
                        journeys.add(journey);
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                        Log.d("Response", "Failed to convert json object: " + jsonObject);
                    }
                }

                //
                adapter = new JourneySearchAdapter(journeys, JourneySearchActivity.this);
                recyclerView.setAdapter(adapter);
                Log.d("Response", "Set adapter.");
            }

            @Override
            public void onFailure(APIResponse response) {
                Toast.makeText(getApplicationContext(), "Failed to retrieve journeys.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
