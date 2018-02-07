package com.pmdm.diurno.practica2pmdm.Activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.pmdm.diurno.practica2pmdm.R;

/**
 * Clase para la vista de Apuestas
 */
public class ApuestasActivity extends AppCompatActivity {

    private CheckBox futbol, tenis, baloncesto, balonmano;
    //Este array es simplemente para ahorrar comprobaciones if o case
    private CheckBox[] checkBoxes = null;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apuestas);

        futbol = (CheckBox) findViewById(R.id.cb_futbol);
        tenis = (CheckBox) findViewById(R.id.cb_tenis);
        baloncesto = (CheckBox) findViewById(R.id.cb_baloncesto);
        balonmano = (CheckBox) findViewById(R.id.cb_balonmano);

        //Méto todos los checkBox en el array
        checkBoxes = new CheckBox[]{futbol, tenis, baloncesto, balonmano};

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String deporte;
        deporte = preferences.getString("deporte", "");

        if(getResources().getText(R.string.cb_futbol).equals(deporte)){
            futbol.setChecked(true);
        }else if(getResources().getText(R.string.cb_tenis).equals(deporte)){
            tenis.setChecked(true);
        }else if(getResources().getText(R.string.cb_baloncesto).equals(deporte)){
            baloncesto.setChecked(true);
        }else if(getResources().getText(R.string.cb_balonmano).equals(deporte)){
            balonmano.setChecked(true);
        }
    }

    /**
     * Método para finalizar la activity
     * @param v
     */
    public void volver(View v){
        finish();
    }

    /**
     * Método para validar la apuesta seleccionada
     * @param v
     */
    public void aceptar(View v){
        int cont = 0;
        String apuestaMarcada = "";

        //Se comprueba que opción está marcada. Se utiliza un contador para que en caso de haber dos
        //opciones seleccionadas salga del bucle y muestre un mensaje de error
        for(int i = 0;i < checkBoxes.length && cont != 2;i++){
            if(checkBoxes[i].isChecked()){
                apuestaMarcada = checkBoxes[i].getText().toString(); //Aquí se almacena el deporte seleccionado
                cont++;
            }
        }

        //Dependiendo del contador mostraremos un mensaje u otro
        switch (cont){
            //No ha habido ninguna selección
            case 0:
                Toast.makeText(this, getResources().getText(R.string.toast_Debes_seleccionar_una_apuesta), Toast.LENGTH_LONG).show();
                break;
            //Ha pulsado una opción. Finaliza la activity
            case 1:
                Toast.makeText(this, getResources().getText(R.string.toast_Apuesta_guardada), Toast.LENGTH_LONG).show();
                //Mandamos los resultados a la activity anterior, MainActivity en este caso
                Intent intent = new Intent();
                intent.putExtra("APUESTA", apuestaMarcada);
                setResult(RESULT_OK, intent);
                finish();
                break;
            //Ha pulsado 2 o más opciones. Muestra un error
            case 2:
                Toast.makeText(this, getResources().getText(R.string.toast_Mas_de_una_apuestas), Toast.LENGTH_LONG).show();
                break;
        }
    }

}
