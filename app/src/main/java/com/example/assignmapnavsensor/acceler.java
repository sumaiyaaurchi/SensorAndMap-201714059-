package com.example.assignmapnavsensor;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class acceler extends AppCompatActivity implements SensorEventListener {


    private TextView xText, yText, zText;
    private Sensor mySensor;
    private SensorManager SM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceler);

        //when app is opened and user navigates to this page, this oncreate() is called

        //get a instance of the SensorEvent Listener Class
        SM=(SensorManager)getSystemService(SENSOR_SERVICE);

        //set the sensor object to a specific sensor
        mySensor=SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //register the listener to this activity,
        // sensor listener will start when this activity starts
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);

        //assign textview
        xText=(TextView)findViewById(R.id.xtext);
        yText=(TextView)findViewById(R.id.ytext);
        zText=(TextView)findViewById(R.id.ztext);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int x = (int)(sensorEvent.values[0]);
        int y = (int)(sensorEvent.values[1]);
        int z = (int)(sensorEvent.values[2]);

        //do what you want with the sensor values here. Perform some actions, change activity, call, toast etc.
        xText.setText("X: " + x);
        yText.setText("Y: " + y);
        zText.setText("Z: " + z);


        if (y >= 9) {
            getWindow().getDecorView().setBackgroundColor(Color.CYAN);
          //  Toast.makeText(getApplicationContext(), "Up", Toast.LENGTH_SHORT).show();

        }
        else if(y<=-9)
        {
            getWindow().getDecorView().setBackgroundColor(Color.CYAN);
          //  Toast.makeText(getApplicationContext(), "Down", Toast.LENGTH_SHORT).show();



        }
        else
        {
            getWindow().getDecorView().setBackgroundColor(Color.GRAY);

        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    @Override
    protected void onResume() {
        super.onResume();
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SM.unregisterListener(this);
    }
}
