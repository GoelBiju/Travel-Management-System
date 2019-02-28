package com.example.customermobileapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the create account button and when clicked make sure it directs to the
        // CreateAccountActivity
        Button buttonCreateAccount = (Button)findViewById(R.id.buttonCreateAccount);
        Button buttonLogIn = (Button)findViewById(R.id.buttonLogIn);
        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), CreateAccountActivity.class);
                startActivity(startIntent);
            }
        });

        // Proceed to process the login.
        buttonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processLogin();
            }
        });
    }


    public void processLogin() {
        Toast.makeText(this, getResources().getString(R.string.implement), Toast.LENGTH_SHORT).show();
    }
}
