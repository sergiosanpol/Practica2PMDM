package com.pmdm.diurno.practica2pmdm.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.pmdm.diurno.practica2pmdm.R;

/**
 * Clase para la vista de Ayuda
 */
public class AyudaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);
    }

    /**
     * Método para volver a la activity anterior
     * @param v
     */
    public void volver(View v){
        finish();
    }
}
