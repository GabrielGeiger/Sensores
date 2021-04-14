package com.cristina.sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SensorLuminosidade extends AppCompatActivity {
TextView respostaLuz;
SensorManager sm;
SensorEventListener listener;
Sensor luzinha;
Button btnVoltar,btnAcelerar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_luminosidade);

        respostaLuz = findViewById(R.id.respostaLuz);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        luzinha = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnAcelerar = findViewById(R.id.btnAcelerar);

       btnAcelerar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               abrirAcelerador();
           }
       });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirVoltar();
            }
        });

        listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event)
            {
            respostaLuz.setText(String.valueOf(event.values[0]));
            int grayShade = (int) event.values[0];
            if(grayShade > 255) grayShade = 255;

            respostaLuz.setTextColor(Color.rgb(255 - grayShade, 255 - grayShade, 255 - grayShade));
            respostaLuz.setBackgroundColor(Color.rgb(grayShade, grayShade, grayShade));

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                Toast.makeText(SensorLuminosidade.this, "Textinho", Toast.LENGTH_SHORT).show();


            }

        };
sm.registerListener(listener, luzinha, SensorManager.SENSOR_DELAY_NORMAL);

    }
    @Override
    protected void onPause(){
        super.onPause();
        sm.unregisterListener(listener, luzinha);
    }

    public void abrirAcelerador(){
        Intent janela = new Intent(this, SensorAcelerar.class);
        startActivity(janela);
    }
    public void abrirVoltar(){
        Intent janela = new Intent(this, MainActivity.class);
        startActivity(janela);
    }
}