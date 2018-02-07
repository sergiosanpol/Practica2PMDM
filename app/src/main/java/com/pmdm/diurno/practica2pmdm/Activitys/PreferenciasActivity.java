package com.pmdm.diurno.practica2pmdm.Activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pmdm.diurno.practica2pmdm.Fragments.PreferenciasFragment;

/**
 * Created by g_ele on 06/02/2018.
 */

public class PreferenciasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new PreferenciasFragment())
                .commit();
    }

}
