package com.pmdm.diurno.practica2pmdm;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class ApuestasActivity extends AppCompatActivity {

    private CheckBox futbol, tenis, baloncesto, balonmano;
    private CheckBox[] checkBoxes = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apuestas);

        futbol = (CheckBox) findViewById(R.id.cb_futbol);
        tenis = (CheckBox) findViewById(R.id.cb_tenis);
        baloncesto = (CheckBox) findViewById(R.id.cb_baloncesto);
        balonmano = (CheckBox) findViewById(R.id.cb_balonmano);

        checkBoxes = new CheckBox[]{futbol, tenis, baloncesto, balonmano};
    }

    public void volver(View v){
        finish();
    }

    public void aceptar(View v){
        int cont = 0;
        String apuestaMarcada = "";

        for(int i = 0;i < checkBoxes.length && cont != 2;i++){
            if(checkBoxes[i].isChecked()){
                apuestaMarcada = checkBoxes[i].getText().toString();
                cont++;
            }
        }

        switch (cont){
            case 0:
                Toast.makeText(this, "Debes seleccionar una apuesta", Toast.LENGTH_LONG).show();
                break;
            case 1:
                Toast.makeText(this, "Apuesta guardada", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.putExtra("APUESTA", apuestaMarcada);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case 2:
                Toast.makeText(this, "La versión Lite sólo admite un tipo de apuesta. Compra nuestra versión de Pago", Toast.LENGTH_LONG).show();
                break;
        }
    }

}
