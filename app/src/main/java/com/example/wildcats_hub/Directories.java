package com.example.wildcats_hub;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;

public class Directories extends AppCompatActivity {

    DrawerLayout draw_layout;
    MaterialToolbar navigation;
    ActionBarDrawerToggle dToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directories);

//        draw_layout = findViewById(R.id.directoryLayout);
//        navigation = findViewById(R.id.topAppBar);
//        dToggle = new ActionBarDrawerToggle(this, draw_layout,R.string.open,R.string.close);
//        draw_layout.addDrawerListener(dToggle);
//        dToggle.syncState();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}