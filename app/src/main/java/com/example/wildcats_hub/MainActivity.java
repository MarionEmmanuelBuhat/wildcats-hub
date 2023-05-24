package com.example.wildcats_hub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    Button login, register;
    TextInputEditText usernameEditText, passwordEditText;
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.loginButton);
        register = findViewById(R.id.createAccButton);
        usernameEditText = findViewById(R.id.username2);
        passwordEditText = findViewById(R.id.password2);

//        startActivity(new Intent(this, SplashScreen.class));

        login.setOnClickListener(view -> {
            username = usernameEditText.getText().toString();
            password = passwordEditText.getText().toString();
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Textfields must be non-empty", Toast.LENGTH_SHORT).show();
                return;
            }
            AccountDatabaseHelper db = new AccountDatabaseHelper(MainActivity.this);
            if (db.loginAuthentication(username,password) == 0) {
                Toast.makeText(this, "Invalid username/password", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(this, HomePage.class);
            intent.putExtra("username", String.valueOf(username));
            startActivity(intent);
        });

        register.setOnClickListener(view -> {
            startActivity(new Intent(this, Register.class));
        });
    }


}