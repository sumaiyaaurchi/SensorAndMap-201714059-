package com.example.assignmapnavsensor;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.TextView;

public class proximity extends AppCompatActivity implements SensorEventListener {
    private TextView xText, yText, zText;
    private Sensor mySensor;
    private SensorManager SM;
    Vibrator vibrator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);
        SM=(SensorManager)getSystemService(SENSOR_SERVICE);
        vibrator=(Vibrator)getSystemService(VIBRATOR_SERVICE);

        mySensor=SM.getDefaultSensor(Sensor.TYPE_PROXIMITY);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.values[0]<mySensor.getMaximumRange()) {
            getWindow().getDecorView().setBackgroundColor(Color.BLACK);
            vibrator.vibrate(1000);
        }
        else
        {
            getWindow().getDecorView().setBackgroundColor(Color.GREEN);

        }

    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    protected void onResume()
    {
        super.onResume();
        SM.registerListener(this,mySensor,SensorManager.SENSOR_DELAY_NORMAL);
    }
    protected void onPause()
    {
        super.onPause();
        SM.unregisterListener(this);
    }


}



