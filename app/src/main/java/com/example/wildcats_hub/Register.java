package com.example.wildcats_hub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Register extends AppCompatActivity {

    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button register = findViewById(R.id.buttonRegister);

        login = findViewById(R.id.homeButton);

        login.setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
        });

        register.setOnClickListener(view -> goLogin());
    }

    public void goLogin(){
        Intent createLogin = new Intent(this, MainActivity.class);
        startActivity(createLogin);
    }
}