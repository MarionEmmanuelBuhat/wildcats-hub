package com.example.wildcats_hub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class EmergencyAccess extends AppCompatActivity {
    String phoneNo;
    String message;

     EditText phoneNumberEditText;
     Button callButton;

    private static final int REQUEST_CALL_PHONE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_access);

        ImageButton btnEmerPFG = findViewById(R.id.btnopenEmergencypdf);
        btnEmerPFG.setOnClickListener(view -> openEmergency());

        Button btn_sendSMS = (Button) findViewById(R.id.btnSend);
        EditText txt_phoneNum = (EditText) findViewById(R.id.editTextNumber);
        EditText txt_msg = (EditText) findViewById(R.id.editTextMsg);
        Button clears = (Button) findViewById(R.id.btnClear);


        phoneNumberEditText = findViewById(R.id.editTxtCall);
        callButton = findViewById(R.id.btnCall);

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePhoneCall();
            }
        });

        btn_sendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneNo = txt_phoneNum.getText().toString();
                message = txt_msg.getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", phoneNo, null));
                intent.putExtra("sms_body", message);
                startActivity(intent);

                Toast.makeText(EmergencyAccess.this, "SMS Sent", Toast.LENGTH_SHORT).show();
            }
        });

        clears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText msgClear = findViewById(R.id.editTextMsg);
                EditText noClear = findViewById(R.id.editTextNumber);
                msgClear.getText().clear();
                noClear.getText().clear();
            }
        });
    }

    public void openEmergency(){
        Intent openEmer = new Intent(this, pdfEmergency.class);
        startActivity(openEmer);
    }


    private void makePhoneCall() {
        String phoneNumber = phoneNumberEditText.getText().toString().trim();

        if (phoneNumber.isEmpty()) {
            phoneNumberEditText.setError("Please enter a phone number");
            phoneNumberEditText.requestFocus();
        } else {
            Uri uri = Uri.parse("tel:" + phoneNumber);
            Intent intent = new Intent(Intent.ACTION_DIAL, uri);
            startActivity(intent);


//            if (ActivityCompat.checkSelfPermission(EmergencyAccess.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
//                startCallIntent(phoneNumber);
//            } else {
//                ActivityCompat.requestPermissions(EmergencyAccess.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE);
//            }
        }
    }
//
//    private void startCallIntent(String phoneNumber) {
//        Intent callIntent = new Intent(Intent.ACTION_DIAL);
//        callIntent.setData(Uri.parse("tel:" + phoneNumber));
//
//        if (ActivityCompat.checkSelfPermission(EmergencyAccess.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
//
//        startActivity(callIntent);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_CALL_PHONE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                makePhoneCall();
//            }
//        }
//    }



}