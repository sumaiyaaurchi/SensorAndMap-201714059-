package com.example.assignmapnavsensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;

import com.squareup.seismic.ShakeDetector;

public class shake extends AppCompatActivity implements ShakeDetector.Listener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);
        SensorManager SM=(SensorManager)getSystemService(SENSOR_SERVICE);
        ShakeDetector SD=new ShakeDetector(this);
        SD.start(SM);
    }

    @Override
    public void hearShake() {

        String numbe="015214640";

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+ numbe));
        startActivity(intent);

    }
}
