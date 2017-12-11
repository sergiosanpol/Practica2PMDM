package com.pmdm.diurno.practica2pmdm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /**
     * Código para el acceso al registro
     */
    public static final int CODIGO_REGISTRO = 1;
    /**
     * Código para el acceso a las apuestas
     */
    public static final int CODIGO_APUESTA = 2;

    /**
     * Variable para saber si estamos registrados o no. En onCreate() la ponemos a false
     */
    private boolean registrado;

    private TextView apuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registrado = false;
        apuesta = (TextView) findViewById(R.id.tv_apuestaMarcada);
    }

    /**
     * Método que abre el registro al pulsar el botón registro. Si ya ha sido registrado no podemos volver a pulsarlo
     * @param v
     */
    public void abrirRegistro(View v){
        if(!registrado){
            startActivityForResult(new Intent(this, RegistroActivity.class), CODIGO_REGISTRO);
        }else{
            Toast.makeText(this, "Ya se encuentra registrado", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Método que responde al botón apuesta. Si ya te has registrado posdrás entrar en la apuestas sino no podremos acceder hasta
     * registrarnos
     * @param v
     */
    public void abrirApuestas(View v){
        if(registrado) {
            startActivityForResult(new Intent(this, ApuestasActivity.class), CODIGO_APUESTA);
        }else{
            Toast.makeText(this, "No registrado", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Método para procesar las respuestas devueltas por otras activitys
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Cuando accedemos al registro y nos devuelve un resultado OK
        if(requestCode == CODIGO_REGISTRO && resultCode == RESULT_OK){
            //La variable registro se pone a true indicando que ya nos hemos registrado
            registrado = true;
        }else if(requestCode == CODIGO_APUESTA && resultCode == RESULT_OK){
            if(apuesta.getVisibility() ==  View.GONE){
                apuesta.setVisibility(View.VISIBLE);
            }

            apuesta.setText("Apuesta marcada: " + data.getStringExtra("APUESTA"));
        }
    }
}
