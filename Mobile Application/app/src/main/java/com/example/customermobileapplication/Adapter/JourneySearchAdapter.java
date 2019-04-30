package com.example.customermobileapplication.Adapter;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customermobileapplication.BookingActivity;
import com.example.customermobileapplication.Model.Journey;
import com.example.customermobileapplication.Model.JourneyListItem;
import com.example.customermobileapplication.R;
import com.example.customermobileapplication.Utilities.API.Helpers;

import org.w3c.dom.Text;

import java.util.List;

public class JourneySearchAdapter extends RecyclerView.Adapter<JourneySearchAdapter.ViewHolder> {

    private List<Journey> listItems;
    private Context context;

    private int customerDepartureStopId;
    private int customerArrivalStopId;

    public JourneySearchAdapter(List<Journey> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Journey listItem = listItems.get(i);

        // Set journey information.
        viewHolder.textViewJourneyTitle.setText(listItem.getJourneyId() + ": " +
                listItem.getRoute().getDepartureStation() + " to " + listItem.getRoute().getArrivalStation());
        viewHolder.textViewJourneyDepartureTime.setText(Helpers.formatDateTime(listItem.getDepartureDateTime()));
        viewHolder.textViewJourneyArrivalTime.setText(Helpers.formatDateTime(listItem.getArrivalDateTime()));
        viewHolder.textViewCoachStatus.setText(listItem.getCoachStatus());

        // Open the bookings activity.
        viewHolder.linearLayoutJourneyItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Open the bookings activity to make a booking for the journey.
                // TODO: Departing stop/arriving stop for a booking is needed.
                // TODO: Pass in the customer's departing and arrival stop id's.
                v.getContext().startActivity(new Intent(context, BookingActivity.class)
                        .putExtra("journeyId", listItem.getJourneyId())
                        .putExtra("departureStopId", customerDepartureStopId)
                        .putExtra("arrivalStopId", customerArrivalStopId)
                );
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public int getCustomerDepartureStopId() {
        return customerDepartureStopId;
    }

    public void setCustomerDepartureStopId(int customerDepartureStopId) {
        this.customerDepartureStopId = customerDepartureStopId;
    }

    public int getCustomerArrivalStopId() {
        return customerArrivalStopId;
    }

    public void setCustomerArrivalStopId(int customerArrivalStopId) {
        this.customerArrivalStopId = customerArrivalStopId;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewJourneyTitle;
        public TextView textViewJourneyDepartureTime;
        public TextView textViewJourneyArrivalTime;
        public TextView textViewCoachStatus;
        public LinearLayout linearLayoutJourneyItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewJourneyTitle = (TextView) itemView.findViewById(R.id.textViewJourneyTitle);
            textViewJourneyDepartureTime = (TextView) itemView.findViewById(R.id.textViewDepartureTime);
            textViewJourneyArrivalTime = (TextView) itemView.findViewById(R.id.textViewArrivalTime);
            textViewCoachStatus = (TextView) itemView.findViewById(R.id.textViewCoachStatus);
            linearLayoutJourneyItem = (LinearLayout) itemView.findViewById(R.id.linearLayoutJourneyItem);
        }
    }
}
