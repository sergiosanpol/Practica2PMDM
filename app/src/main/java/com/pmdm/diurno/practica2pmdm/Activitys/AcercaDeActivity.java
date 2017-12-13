package com.pmdm.diurno.practica2pmdm.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.pmdm.diurno.practica2pmdm.R;

/**
 * Clase correspondiente de la vista de Acerca de
 */
public class AcercaDeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);
    }

    public void volver(View v){
        finish();
    }
}
