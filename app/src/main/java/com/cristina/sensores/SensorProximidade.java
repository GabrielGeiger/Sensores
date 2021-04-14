package com.cristina.sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SensorProximidade extends AppCompatActivity implements SensorEventListener {
    private Sensor proximidade;
    private TextView resultado;
    private SensorManager medir;
    private Button proximo2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_proximidade);

        medir = (SensorManager)this.getSystemService(SENSOR_SERVICE);
        proximidade = medir.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        resultado = findViewById(R.id.resultado);
        proximo2 = findViewById(R.id.proximo2);

        proximo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirLuz();
            }
        });
    }

    public void abrirLuz(){
        Intent janela = new Intent(this, SensorLuminosidade.class);
        startActivity(janela);
    }

    @Override
    protected void onResume(){
medir.registerListener(this, proximidade, SensorManager.SENSOR_DELAY_NORMAL);
super.onResume();
    }

    @Override
    protected void onPause(){
        medir.unregisterListener(this, proximidade);
        super.onPause();
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
if(event.values[0] == 0)
{
    getWindow().getDecorView().setBackgroundColor(Color.MAGENTA);
    resultado.setText("Está Proximo");
}
else
    {
        getWindow().getDecorView().setBackgroundColor(Color.CYAN);
    resultado.setText("Esta Longe");
    }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}