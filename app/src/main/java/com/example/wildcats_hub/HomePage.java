package com.example.wildcats_hub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Button taskmasterBtn = findViewById(R.id.taskmaster);

        taskmasterBtn.setOnClickListener(view -> openTaskmaster());
    }
    public void openTaskmaster(){
        startActivity(new Intent(this, TaskMain.class));
    }
}