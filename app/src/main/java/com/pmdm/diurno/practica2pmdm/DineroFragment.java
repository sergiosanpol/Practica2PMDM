package com.pmdm.diurno.practica2pmdm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class DineroFragment extends Fragment {

    private Spinner spinner;


    public DineroFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_dinero, container, false);
        spinner = (Spinner) v.findViewById(R.id.sp_dinero);
        String[] dinero = {"1", "2", "5", "10"};
        spinner.setAdapter(new ArrayAdapter<String>(v.getContext(), R.layout.support_simple_spinner_dropdown_item, dinero));
        return v;
    }

}
