package com.cristina.sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SensorAcelerar extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private boolean isColor = false;
    private TextView respostaC;
    private long ultimaAtualizacao;
    private Button btnRetornar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_acelerar);

        respostaC = findViewById(R.id.respostaC);
        respostaC.setBackgroundColor(Color.RED);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        ultimaAtualizacao = System.currentTimeMillis();
        btnRetornar = findViewById(R.id.btnRetornar);

        btnRetornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirRetornar();
            }
        });
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event);
        }
    }

    private void getAccelerometer(SensorEvent event) {
        float[] values = event.values;
        float x = values[0];
        float y = values[1];
        float z = values[2];

        float accelationSquareRoot = (x * x + y * y + z * z) / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
        long tempo = System.currentTimeMillis();
        Toast.makeText(getApplicationContext(), String.valueOf(accelationSquareRoot) + "" + SensorManager.GRAVITY_EARTH, Toast.LENGTH_SHORT).show();

        if (accelationSquareRoot >= 2) {
            if (tempo - ultimaAtualizacao < 200) {
                return;
            }
        }

        ultimaAtualizacao = tempo;
        if (isColor) {
            respostaC.setBackgroundColor(Color.BLACK);
        } else {
            respostaC.setBackgroundColor(Color.GRAY);

        }

        isColor = !isColor;
    }






    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void abrirRetornar()
    {
        Intent janela = new Intent(this, MainActivity.class);
        startActivity(janela);
    }
}