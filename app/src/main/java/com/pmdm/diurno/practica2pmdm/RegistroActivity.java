package com.pmdm.diurno.practica2pmdm;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RegistroActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private EditText nombre, email, fechaNacimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombre = (EditText) findViewById(R.id.et_nombre);
        email = (EditText) findViewById(R.id.et_email);
        fechaNacimiento = (EditText) findViewById(R.id.et_fechaNacimiento);
        fechaNacimiento.setFocusableInTouchMode(false);

    }

    public void dialogoFecha(View v){
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(this, this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        dialog.getDatePicker().getTouchables().get(0).performClick();
        dialog.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis());
        dialog.setTitle(null);
        dialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        fechaNacimiento.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

    }

    public void volver(View v){
        finish();
    }

    public void validar(View v){
        if(nombre.getText().toString().isEmpty() || email.getText().toString().isEmpty() || fechaNacimiento.getText().toString().isEmpty()){
            Toast.makeText(this, "Falan datos por rellenar", Toast.LENGTH_LONG).show();
        }else{
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Calendar nacimiento = Calendar.getInstance();
                nacimiento.setTime(format.parse(fechaNacimiento.getText().toString()));

                if(mayoriaDeEdad(nacimiento)) {
                    Toast.makeText(this, "Quedas registrado", Toast.LENGTH_LONG).show();
                    setResult(RESULT_OK);
                    finish();
                }else{
                    Toast.makeText(this, "Debes ser mayor de edad para poder registrarte", Toast.LENGTH_LONG).show();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean mayoriaDeEdad(Calendar nacimiento){
        Calendar hoy = Calendar.getInstance();

        int difDias = hoy.get(Calendar.DAY_OF_MONTH) - nacimiento.get(Calendar.DAY_OF_MONTH);
        int difMes = hoy.get(Calendar.MONTH) - nacimiento.get(Calendar.MONTH);
        int difAnno = hoy.get(Calendar.YEAR) - nacimiento.get(Calendar.YEAR);

        if(difAnno > 18) {
            return true;
        }else if(difAnno == 18){
            if (difMes < 0 || (difMes == 0 && difDias < 0)) {
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("NOMBRE", nombre.getText().toString());
        outState.putString("EMAIL", email.getText().toString());
        outState.putString("FECHA_NACIMIENTO", fechaNacimiento.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        nombre.setText(savedInstanceState.getString("NOMBRE"));
        email.setText(savedInstanceState.getString("EMAIL"));
        fechaNacimiento.setText(savedInstanceState.getString("FECHA_NACIMIENTO"));
    }
}
