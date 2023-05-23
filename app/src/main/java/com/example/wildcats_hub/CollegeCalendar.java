package com.example.wildcats_hub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class CollegeCalendar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_calendar);

        ImageButton calendarPDF = findViewById(R.id.btnCalendarPDF);
//        calendarPDF.setOnClickListener(view -> openCalendarPDF());

    }

//    public void openCalendarPDF(){
//        startActivity(new);
//    }
}