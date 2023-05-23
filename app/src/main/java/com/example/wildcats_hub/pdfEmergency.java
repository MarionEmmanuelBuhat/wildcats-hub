package com.example.wildcats_hub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

public class pdfEmergency extends AppCompatActivity {
    private PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_emergency);


        pdfView = findViewById(R.id.pdfViewer);
        // Load the PDF file from assets folder
        pdfView.fromAsset("EMERGENCY.pdf")
                .defaultPage(0)
//                .enableSwipe(true)
//                .swipeHorizontal(false)
//                .enableDoubletap(true)
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(10)
//
                .load();
    }
}