package com.example.usuario1.chronometer;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Declaramos los objetos y la variable
    Chronometer cronometro;
    Button iniciar, pausa, reiniciar;
    TextView Funciones;
    long elapsedTime=0;
    String currentTime;
    Boolean activity =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Relacionamos los controles del activity con los objetos de esta clase
        cronometro = (Chronometer) findViewById(R.id.cronometro);
        iniciar = (Button)findViewById(R.id.iniciar);
        pausa = (Button)findViewById(R.id.pausa);
        reiniciar = (Button)findViewById(R.id.reiniciar);
        Funciones = (TextView)findViewById(R.id.textView_Funcion);

        //Deshabilitar y habilitar los botones
        iniciar.setEnabled(true);
        pausa.setEnabled(false);
        reiniciar.setEnabled(false);

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Habilitar y deshabilitar los botones
                iniciar.setEnabled(false);
                pausa.setEnabled(true);
                reiniciar.setEnabled(true);
                Funciones.setText(">");
                //Evaluamos la variable activity
                if (!activity) {
                    cronometro.setBase(SystemClock.elapsedRealtime());
                    cronometro.start();
                }
            }
        });
        pausa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciar.setEnabled(true);
                pausa.setEnabled(false);
                reiniciar.setEnabled(true);
                Funciones.setText("ll");
                cronometro.stop();



            }
        });
        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cronometro.stop();
                cronometro.setText("00:00");
                activity=false;
                iniciar.setEnabled(true);
                pausa.setEnabled(false);
                reiniciar.setEnabled(false);
                Funciones.setText("");

            }
        });
        cronometro.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                //Evaluamos la variable activity si no es falsa
                if (!activity){

                    long minutos=((SystemClock.elapsedRealtime()-cronometro.getBase())/1000)/60;
                    long segundos=((SystemClock.elapsedRealtime()-cronometro.getBase())/1000)%60;

                    currentTime=minutos+":"+segundos;
                    chronometer.setText(currentTime);

                    elapsedTime=SystemClock.elapsedRealtime();
                }else{

                    long minutos=((elapsedTime-cronometro.getBase())/1000)/60;
                    long segundos=((elapsedTime-cronometro.getBase())/1000)%60;


                    elapsedTime=elapsedTime+1000;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
