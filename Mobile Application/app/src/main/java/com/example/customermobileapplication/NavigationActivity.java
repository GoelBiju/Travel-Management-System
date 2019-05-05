package com.example.customermobileapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customermobileapplication.Fragments.AccountFragment;
import com.example.customermobileapplication.Fragments.HomeFragment;
import com.example.customermobileapplication.Fragments.MyBookingsFragment;
import com.example.customermobileapplication.Fragments.TimetableFragment;
import com.example.customermobileapplication.Model.Customer;
import com.example.customermobileapplication.Utilities.API.APIConnection;
import com.example.customermobileapplication.Utilities.API.APIResponse;
import com.example.customermobileapplication.Utilities.API.CustomCallback;

import java.net.HttpURLConnection;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private APIConnection apiConnection;

    private SharedPreferences pref;

    private TextView textViewCustomerFullName;
    private TextView textViewCustomerEmailAddress;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressDialog = new ProgressDialog(this);

        // Initialise the APIConnection.
        apiConnection = new APIConnection(getApplicationContext(), getResources().getString(R.string.api_base_url));

        // Re-direct to the login if the user details has not been saved.
        // Check if the customerId is present, if not go to login.
        pref = getApplicationContext().getSharedPreferences("userDetails", MODE_PRIVATE);
        int customerId = pref.getInt("customerId", 0);

        // Perform an apiTest to ensure that the current access token is valid.
        // TODO: If not the user should be re-directed to the login page on all requests.
        if (customerId > 0) {
            Log.d("Response", "Customer Id present.");
            apiTest(customerId);
        } else {
            Log.d("Response", "Customer Id not present, re-directing to start screen.");
            Intent startIntent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(startIntent);
            finish();
        }
    }

    private void setupAppNavigation() {
        // Automatically start on the home fragment.
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.screen_area, new HomeFragment());
        ft.commit();

        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        textViewCustomerFullName = (TextView) header.findViewById(R.id.textViewCustomerFullName);
        textViewCustomerEmailAddress = (TextView) header.findViewById(R.id.textViewCustomerEmailAddress);
    }

    public void apiTest(final int customerId) {

        progressDialog.setMessage("Loading...");
        progressDialog.show();

        apiConnection.getCustomJsonObject("customers/" + customerId, Customer.class, new CustomCallback() {
            @Override
            public void onSuccess(Object responseObject) {
                Log.d("Response", "API test successful.");

                progressDialog.hide();

                // Setup the app after success.
                setupAppNavigation();

                // Set the customer name and email address.
                Customer customer = (Customer) responseObject;
                textViewCustomerFullName.setText(customer.getFirstName() + " " + customer.getLastName());
                textViewCustomerEmailAddress.setText(customer.getEmailAddress());

                Toast.makeText(getApplicationContext(),  "Logged in successfully as: " + customer.getEmailAddress(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(APIResponse errorResponse) {
                Log.d("Response", "Request was unsuccessful.");

                progressDialog.hide();

                Log.d("Response", String.valueOf(errorResponse.getResponseStatusCode()));
                if (errorResponse.getResponseStatusCode() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                    // Re-direct to the login activity.
                    Toast.makeText(getApplicationContext(),  "Please login again to access your services.", Toast.LENGTH_LONG).show();
                    Log.d("Response", "Finishing and re-directing to login.");

                    Intent startIntent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(startIntent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),  "Status Code: " + Integer.toString(errorResponse.getResponseStatusCode())
                            + ", Message: " + errorResponse.getResponse() + ".", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Fragment fragment = null;

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            fragment = new HomeFragment();
        } else if (id == R.id.nav_my_bookings) {
            fragment = new MyBookingsFragment();
        } else if (id == R.id.nav_timetable) {
            fragment = new TimetableFragment();
        } else if (id == R.id.nav_account) {
            fragment = new AccountFragment();
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {

        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();

            // Replace the frame layout with the fragment.
            ft.replace(R.id.screen_area, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }
}
