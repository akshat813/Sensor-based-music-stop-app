package com.example.sensor_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    Button button;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.play_id);
        mediaPlayer=MediaPlayer.create(this,R.raw.song);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();

            }
        });

        //Proximity Sensor
        SensorManager sensorManager;
       //Accelerometer
        SensorManager sensorManager1;
        sensorManager=(SensorManager)this.getSystemService(SENSOR_SERVICE);
        sensorManager1=(SensorManager)this.getSystemService(SENSOR_SERVICE);

        Sensor proxy,acc;
        proxy=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        acc=sensorManager1.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,proxy,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager1.registerListener(this,acc,sensorManager1.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor= sensorEvent.sensor;
        if (sensor.getType()==Sensor.TYPE_PROXIMITY)
        {
            mediaPlayer.pause();
        }
        else if(sensor.getType()==Sensor.TYPE_ACCELEROMETER)
        {
            float x=sensorEvent.values[0];
            float y=sensorEvent.values[1];
            if (x>10)
            {
                mediaPlayer.start();
            }
            if(y>10)
            {
                mediaPlayer.pause();
            }
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
