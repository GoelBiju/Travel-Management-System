package com.example.customermobileapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Text fields.
    private EditText editTextEmail;
    private EditText editPassword;

    // Buttons.
    private Button loginButton;
    private Button createAccountButton;


    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind views.
        bindViews();

        // Set button actions.
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the CreateAccountActivity to allow users to register.
                Intent startIntent = new Intent(getApplicationContext(), CreateAccountActivity.class);
                startActivity(startIntent);
            }
        });

        // Proceed to process the login.
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processLogin();
            }
        });
    }


    /**
     *
     */
    private void bindViews() {
        // Bind fields.
        editTextEmail = findViewById(R.id.editTextEmail);
        editPassword = findViewById(R.id.editTextPassword);

        // Bind buttons.
        loginButton = findViewById(R.id.buttonLogin);
        createAccountButton = findViewById(R.id.buttonCreateAccount);
    }


    /**
     * This function will call the API to pass the username and password details.
     */
    public void processLogin() {

        Toast.makeText(this, getResources().getString(R.string.implement), Toast.LENGTH_SHORT).show();


    }
}
