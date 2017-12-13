package com.pmdm.diurno.practica2pmdm.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.pmdm.diurno.practica2pmdm.R;

import java.io.Serializable;

/**
 * Clase del fragment de Dinero. Implementa Serializable para poder guardar los estados del fragment
 */
public class DineroFragment extends Fragment implements Serializable{

    private Spinner spinner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Con esto inicamos que el fragment se mantenga retenido en los cambios de configuración
        setRetainInstance(true);
    }

    /**
     * Método que crea la vista del fragment
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dinero, container, false);
        //Cargamos el recurso con la vista creada
        spinner = (Spinner) v.findViewById(R.id.sp_dinero);
        String[] dinero = {"1", "2", "5", "10"};
        spinner.setAdapter(new ArrayAdapter<>(v.getContext(), R.layout.support_simple_spinner_dropdown_item, dinero));
        return v;
    }

    /**
     * Método que devuelve el Spinner asociado al fragment
     * @return
     */
    public Spinner getSpinner() {
        return spinner;
    }
}
