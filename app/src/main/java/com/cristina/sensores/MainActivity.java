package com.cristina.sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    SensorManager sensorManager;
    private Button lista, proximo;
    private TextView resposta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        lista = findViewById(R.id.lista);
        proximo = findViewById(R.id.proximo);
        resposta = findViewById(R.id.resposta);

        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recebe = "";
                List<Sensor> listagem = sensorManager.getSensorList(Sensor.TYPE_ALL);

                for(int i = 0; i < listagem.size(); i++)
                {
                    recebe = listagem.get(i).getName() + "\n";
                }
                resposta.setText(" " + recebe);
            }
        });

        proximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirProximo();
            }
        });


    }

    public void abrirProximo(){
        Intent janela = new Intent(this, SensorProximidade.class);
        startActivity(janela);
    }
}