package com.example.customermobileapplication.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.customermobileapplication.BookingActivity;
import com.example.customermobileapplication.BookingDetailsActivity;
import com.example.customermobileapplication.Model.Booking;
import com.example.customermobileapplication.R;

import org.w3c.dom.Text;

import java.util.List;

public class BookingsAdapter extends RecyclerView.Adapter<BookingsAdapter.ViewHolder> {

    private List<Booking> listItems;
    private Context context;

    private boolean isActiveBookings = true;

    public BookingsAdapter(List<Booking> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    public boolean isActiveBookings() {
        return isActiveBookings;
    }

    public void setActiveBookings(boolean activeBookings) {
        isActiveBookings = activeBookings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.booking_list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Booking listItem = listItems.get(i);

        // NOTE: Ensure the datatypes match as no errors will occur when datatypes are not matched,
        //       resulting in an app crash.
        viewHolder.textViewBookingTitle.setText(listItem.getDepartingStop().getStopName() +
                " to " + listItem.getArrivalStop().getStopName());
        viewHolder.textViewJourneyId.setText(Integer.toString(listItem.getJourney().getJourneyId()));
        viewHolder.textViewDepartingStop.setText(listItem.getDepartingStop().getStopName());
        viewHolder.textViewArrivalStop.setText(listItem.getArrivalStop().getStopName());
        viewHolder.textViewAmountPaid.setText("£ "+ String.format("%.2f", listItem.getAmountPaid()));
        viewHolder.textViewBookingStatus.setText(listItem.getStatus());

        viewHolder.linearLayoutBookingItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (isActiveBookings) {
                    v.getContext().startActivity(new Intent(context, BookingDetailsActivity.class)
                            .putExtra("bookingReference", listItem.getBookingReference())
                    );
                } else {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setMessage("Would you like to repeat this booking?")
                            .setPositiveButton("Repeat Booking", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    v.getContext().startActivity(new Intent(context, BookingActivity.class)
                                        .putExtra("journeyId", listItem.getJourney().getJourneyId())
                                        .putExtra("departureStopId", listItem.getDepartingStop().getStopId())
                                        .putExtra("arrivalStopId", listItem.getArrivalStop().getStopId())
                                    );
                                }
                            })
                            .setNegativeButton("Go Back", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .setCancelable(false);

                    AlertDialog alert = alertDialogBuilder.create();
                    alert.setTitle("Repeat Booking");
                    alert.show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewBookingTitle;
        public TextView textViewJourneyId;
        public TextView textViewDepartingStop;
        public TextView textViewArrivalStop;
        public TextView textViewBookingStatus;
        public TextView textViewAmountPaid;

        public LinearLayout linearLayoutBookingItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewBookingTitle = (TextView) itemView.findViewById(R.id.textViewBookingTitle);
            textViewJourneyId = (TextView) itemView.findViewById(R.id.textViewJourneyId);
            textViewDepartingStop = (TextView) itemView.findViewById(R.id.textViewDepartingStop);
            textViewArrivalStop = (TextView) itemView.findViewById(R.id.textViewArrivalStop);
            textViewBookingStatus = (TextView) itemView.findViewById(R.id.textViewBookingStatus);
            textViewAmountPaid = (TextView) itemView.findViewById(R.id.textViewAmountPaid);

            linearLayoutBookingItem = (LinearLayout) itemView.findViewById(R.id.linearLayoutBookingItem);
        }
    }
}
