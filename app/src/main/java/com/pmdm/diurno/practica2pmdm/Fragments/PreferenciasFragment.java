package com.pmdm.diurno.practica2pmdm.Fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.pmdm.diurno.practica2pmdm.R;

/**
 * Created by g_ele on 06/02/2018.
 */

public class PreferenciasFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedIntanceState) {
        super.onCreate(savedIntanceState);

        addPreferencesFromResource(R.xml.preferencias);
    }

}
