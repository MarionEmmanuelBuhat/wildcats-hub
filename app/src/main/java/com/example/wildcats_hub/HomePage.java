package com.example.wildcats_hub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        ImageButton taskmasterBtn = findViewById(R.id.taskmaster);
        ImageButton directoriesBtn = findViewById(R.id.directory);
        ImageButton mapBtn = findViewById(R.id.btnCITMap);
        ImageButton calendarBtn = findViewById(R.id.btnCalendar);
        ImageButton emergency = findViewById(R.id.btnEmergency);
        TextView welcome = findViewById(R.id.welcome);
        Intent intent = getIntent();
        String name = intent.getStringExtra("username");
        welcome.append(name + "!");

        taskmasterBtn.setOnClickListener(view -> openTaskmaster());
        directoriesBtn.setOnClickListener(view -> openDirectories());
        mapBtn.setOnClickListener(view -> openMap());
        calendarBtn.setOnClickListener(view -> openCalendarPage());
        emergency.setOnClickListener(view -> openEmergency());
    }

    public void openTaskmaster(){
        startActivity(new Intent(this, TaskMaster.class));
    }
    public void openDirectories(){
        startActivity(new Intent(this, Directories.class));
    }
    public void openMap(){startActivity(new Intent(this, CITMap.class));}

    public void openCalendarPage(){
        startActivity(new Intent(this, CollegeCalendar.class));
    }
    public void openEmergency(){startActivity(new Intent(this, pdfEmergency.class));}
}