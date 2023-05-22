package com.example.wildcats_hub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

public class CITMap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citmap);
        Button CITMapOpenBtn = findViewById(R.id.btnopenMaps);

        CITMapOpenBtn.setOnClickListener(view -> openCITGMaps());
    }


    public void openCITGMaps(){
        Intent int_openMaps = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:10.2945,  123.8811"));
        startActivity(int_openMaps);
    }
}