package com.example.wildcats_hub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    Button register, homeButton;
    AccountDatabaseHelper myDB;
    EditText username, email, ins_email, pass, pass_conf;
    String username2, email2, ins_email2, pass2, pass_conf2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button register = findViewById(R.id.buttonRegister);
        username = findViewById(R.id.editTextUsername);
        email = findViewById(R.id.editTextEmail);
        ins_email = findViewById(R.id.editTextCitEmail);
        pass = findViewById(R.id.editTextPassword);
        pass_conf = findViewById(R.id.editTextConfirmPassword);
        homeButton = findViewById(R.id.homeButton);

        homeButton.setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
        });

        register = findViewById(R.id.buttonRegister);

        myDB = new AccountDatabaseHelper(Register.this);

        register.setOnClickListener(view -> {
            username2 = username.getText().toString();
            email2 = email.getText().toString();
            ins_email2 = ins_email.getText().toString();
            pass2 = pass.getText().toString();
            pass_conf2 = pass_conf.getText().toString();
            if (username2.isEmpty() || email2.isEmpty() || ins_email2.isEmpty() ||
                    pass2.isEmpty() || pass_conf2.isEmpty()) {
                Toast.makeText(this, "Textfields must be non-empty", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!pass2.equals(pass_conf2)) {
                Toast.makeText(this, "Invalid password", Toast.LENGTH_SHORT).show();
                return;
            }
            AccountDatabaseHelper db = new AccountDatabaseHelper(Register.this);
            db.addAccount(username2, email2, ins_email2, pass2);
            startActivity(new Intent(this, MainActivity.class));
        });
    }
}