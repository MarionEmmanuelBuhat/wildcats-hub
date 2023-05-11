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
        Button directoriesBtn = findViewById(R.id.directory);

        taskmasterBtn.setOnClickListener(view -> openTaskmaster());
        directoriesBtn.setOnClickListener(view -> openDirectories());
    }

    public void openTaskmaster(){
        startActivity(new Intent(this, TaskMain.class));
    }
    public void openDirectories(){
        startActivity(new Intent(this, Directories.class));
    }
}