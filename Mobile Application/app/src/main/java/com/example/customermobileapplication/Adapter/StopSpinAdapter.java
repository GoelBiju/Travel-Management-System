package com.example.customermobileapplication.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.customermobileapplication.Model.Stop;

import java.util.ArrayList;

public class StopSpinAdapter extends ArrayAdapter<Stop> {

    private Context context;

    private ArrayList<Stop> stops;

    public StopSpinAdapter(Context context, int textViewResourceId, ArrayList<Stop> stops) {
        super(context, textViewResourceId, stops);

        this.context = context;
        this.stops = stops;
    }

    @Override
    public int getCount() {
        return stops.size();
    }

    @Override
    public Stop getItem(int position) {
        return stops.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.BLACK);

        label.setText(stops.get(position).getStopName());

        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(stops.get(position).getStopName());

        return label;
    }
}
