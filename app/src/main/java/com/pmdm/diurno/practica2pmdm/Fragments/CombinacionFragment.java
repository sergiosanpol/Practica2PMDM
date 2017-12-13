package com.pmdm.diurno.practica2pmdm.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.pmdm.diurno.practica2pmdm.Activitys.MainActivity;
import com.pmdm.diurno.practica2pmdm.R;

import java.io.Serializable;

/**
 * Clase del fragment de Combinaciones. Implementa Serializable para poder guardar los estados del fragment
 */
public class CombinacionFragment extends Fragment implements Serializable{

    private TextView apuestaMarcada;
    private EditText apuesta1, apuesta2;
    private String combinacion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Con esto inicamos que el fragment se mantenga retenido en los cambios de configuración
        setRetainInstance(true);
        //Obtenemos del MainActivity una variable estática que nos indica que tipo de apuesta escogida
        combinacion = MainActivity.apuestaMarcada;
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
        View v = inflater.inflate(R.layout.fragment_combinacion, container, false);
        //Cargamos los recursos de esta vista
        apuestaMarcada = (TextView) v.findViewById(R.id.tv_apuestaMarcada);
        apuestaMarcada.setText(jugadaCombinacion(combinacion));
        apuesta1 = (EditText) v.findViewById(R.id.et_apuesta1);
        apuesta2 = (EditText) v.findViewById(R.id.et_apuesta2);
        return v;
    }

    /**
     * Método que devuelve el EditText de la apuesta1
     * @return
     */
    public EditText getApuesta1() {
        return apuesta1;
    }

    /**
     * Método que devuelve el EditText de la apuesta2
     * @return
     */
    public EditText getApuesta2() {
        return apuesta2;
    }

    /**
     * Método que devuelce un String. Dependiendo del deporte que se haya seleccinado se mostrará
     * el enfrentamiento correspondiente
     * @param combinacion
     * @return
     */
    private String jugadaCombinacion(String combinacion){
        if(combinacion.equalsIgnoreCase(getResources().getString(R.string.cb_futbol))){
            return "Baza - Caniles";
        }else if(combinacion.equalsIgnoreCase(getResources().getString(R.string.cb_tenis))){
            return "Djokovic - Federer";
        }else if(combinacion.equalsIgnoreCase(getResources().getString(R.string.cb_baloncesto))){
            return "Unicaja -  Baskonia";
        }else if(combinacion.equalsIgnoreCase(getResources().getString(R.string.cb_balonmano))){
            return "Fraikin BM. Granollers - Bidasoa Irún";
        }
        return  null;
    }
}
