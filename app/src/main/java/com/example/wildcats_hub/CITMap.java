package com.example.wildcats_hub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class CITMap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citmap);
        ImageButton CITMapOpenBtn = findViewById(R.id.btnopenMaps);

        CITMapOpenBtn.setOnClickListener(view -> openCITGMaps());

    }


    public void openCITGMaps(){
        String url = "https://maps.app.goo.gl/qcYPuLBSBAKM7iSr6";
        Intent openMaps = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(openMaps);
    }
}