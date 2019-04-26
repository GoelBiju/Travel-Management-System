package com.example.customermobileapplication;

import android.content.ReceiverCallNotAllowedException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.customermobileapplication.Adapter.JourneySearchAdapter;
import com.example.customermobileapplication.Model.Journey;
import com.example.customermobileapplication.Model.JourneyListItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JourneySearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<Journey> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey_search);

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

        //adapter = new JourneySearchAdapter(listItems, this);

        recyclerView.setAdapter(adapter);

        // TODO: Load the journeys available to book from the zearch parameters.
        // Create a SearchJourneysBindingModel to send to API with station Ids, DateTime fields.
        loadJourneys()
    }

    private void loadJourneys() {
        // Get the search parameters passed.

        // Create JourneySearchBindingModel from search parameters.

        // Call the
    }
}
