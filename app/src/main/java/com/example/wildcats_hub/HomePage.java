package com.example.wildcats_hub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        ImageButton taskmasterBtn = findViewById(R.id.taskmaster);
        Button directoriesBtn = findViewById(R.id.directory);
        Button mapBtn = findViewById(R.id.btnCITMap);

        taskmasterBtn.setOnClickListener(view -> openTaskmaster());
        directoriesBtn.setOnClickListener(view -> openDirectories());
        mapBtn.setOnClickListener(view -> openMap());
    }

    public void openTaskmaster(){
        startActivity(new Intent(this, TaskMaster.class));
    }
    public void openDirectories(){
        startActivity(new Intent(this, Directories.class));
    }
    public void openMap(){startActivity(new Intent(this, CITMap.class));}
}