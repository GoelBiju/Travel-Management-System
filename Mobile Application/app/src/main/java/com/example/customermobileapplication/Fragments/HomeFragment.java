package com.example.customermobileapplication.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.customermobileapplication.ConfirmBookingActivity;
import com.example.customermobileapplication.LoginActivity;
import com.example.customermobileapplication.R;

public class HomeFragment extends Fragment {

    //SharedPreferences pref;

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

        // Cannot use findViewById directly, call view.findViewById/getView()? to use it.
//        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                // No getApplicationContext() in fragment, instead use getActivity().
//                Toast.makeText(getActivity(), "You are logged out.", Toast.LENGTH_SHORT).show();
//
//                // Clear the SharedPreferences.
//                pref = getActivity().getSharedPreferences("userDetails", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = pref.edit();
//                editor.clear();
//                editor.apply();
//
//                // Return to login screen.
//                Intent startIntent = new Intent(getActivity(), LoginActivity.class);
//                startActivity(startIntent);
//
//                // Finish this fragment.
//                getActivity().finish();
//            }
//        });

        view.findViewById(R.id.buttonViewBookings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getActivity(), ConfirmBookingActivity.class);
                startActivity(startIntent);
            }
        });
    }
}
