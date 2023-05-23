package com.example.wildcats_hub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

public class pdfActivity extends AppCompatActivity {

    private PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        pdfView = findViewById(R.id.pdfView);

        // Load the PDF file from assets folder
        pdfView.fromAsset("calendarpdf.pdf")
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