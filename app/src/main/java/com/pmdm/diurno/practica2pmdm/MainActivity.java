package com.pmdm.diurno.practica2pmdm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int CODIGO_REGISTRO = 1;

    private boolean registrado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registrado = false;
    }

    public void abrirRegistro(View v){
        if(!registrado){
            startActivityForResult(new Intent(this, RegistroActivity.class), CODIGO_REGISTRO);
        }else{
            Toast.makeText(this, "Ya se encuentra registrado", Toast.LENGTH_LONG).show();
        }
    }

    public void abrirApuestas(View v){
        if(registrado) {
            Toast.makeText(this, "Apuestas", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "No registrado", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CODIGO_REGISTRO && resultCode == RESULT_OK){
            registrado = true;
        }
    }
}
