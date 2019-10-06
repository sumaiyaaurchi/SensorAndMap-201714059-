package com.example.assignmapnavsensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Gyro extends AppCompatActivity implements SensorEventListener {
    private TextView xGyro,yGyro,zGyro;
    private Sensor mySensor;
    private SensorManager SM;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyro);
        databaseReference= FirebaseDatabase.getInstance().getReference("Gyroscope");
        SM=(SensorManager)getSystemService(SENSOR_SERVICE);

       // mySensor=SM.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mySensor=SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        SM.registerListener(this,mySensor,SensorManager.SENSOR_DELAY_NORMAL);

        xGyro=(TextView)findViewById(R.id.xGyro);
        yGyro=(TextView)findViewById(R.id.yGyro);
        zGyro=(TextView)findViewById(R.id.zGyro);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];
        xGyro.setText("X: " + (int) x);
        yGyro.setText("Y: " + (int) y);
        zGyro.setText("Z: " + (int) z);
        if ((int) y == 0) {
            saveData();
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    public void saveData()
    {
        String x,y,z;
        x=xGyro.getText().toString();
        y=yGyro.getText().toString();
        z=zGyro.getText().toString();

            Store store = new Store(x, y, z);
            String key = databaseReference.push().getKey();
            databaseReference.child(key).setValue(store);
            Toast.makeText(this, "Data entered", Toast.LENGTH_SHORT).show();
            openHome();

    }

    private void openHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    protected void onResume()
    {
        super.onResume();
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);    }
    protected void onPause()
    {
        super.onPause();
        SM.unregisterListener(this);
    }

}
