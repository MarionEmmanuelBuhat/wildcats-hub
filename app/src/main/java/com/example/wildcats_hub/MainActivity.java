package com.example.wildcats_hub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button login, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.loginButton);
        register = findViewById(R.id.createAccButton);

//        startActivity(new Intent(this, SplashScreen.class));

        login.setOnClickListener(view -> {
            startActivity(new Intent(this, HomePage.class));
        });

        register.setOnClickListener(view -> {
            startActivity(new Intent(this, Register.class));
        });
    }


}