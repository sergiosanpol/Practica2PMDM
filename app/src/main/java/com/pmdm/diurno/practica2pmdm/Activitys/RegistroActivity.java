package com.pmdm.diurno.practica2pmdm.Activitys;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.pmdm.diurno.practica2pmdm.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Clase que corresponde a la vista del Registro
 */
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

    /**
     * Método para mostrar un calendario para poner la fecha de nacimiento cuando se pulsa el EditText de fecha de nacimiento
     * @param v
     */
    public void dialogoFecha(View v){
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(this, this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        dialog.getDatePicker().getTouchables().get(0).performClick();
        dialog.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis());
        dialog.setTitle(null);
        dialog.show();
    }

    /**
     * Método que escribe la fecha de nacimiento en el EditText cuando confirmamos un cambio de fecha de DatePickerDialog
     * @param view
     * @param year
     * @param month
     * @param dayOfMonth
     */
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        fechaNacimiento.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

    }

    /**
     * Método para el botón volver. Finaliza la activity
     * @param v
     */
    public void volver(View v){
        finish();
    }

    /**
     * Método para el botón validar. Comprueba que los campos no estén vacios y la fecha de nacmiento sea de mayor de edad
     * @param v
     */
    public void validar(View v){
        //Se comprueba que los campos estén rellenos, sino muestra un error
        if(nombre.getText().toString().isEmpty() || email.getText().toString().isEmpty() || fechaNacimiento.getText().toString().isEmpty()){
            Toast.makeText(this, getResources().getText(R.string.toast_Falan_datos_por_rellenar), Toast.LENGTH_LONG).show();
        }else{
            //Se comprueba que sea mayor de edad. Si es mayor queda registrado, sino muestra error y no avanza
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Calendar nacimiento = Calendar.getInstance();
                nacimiento.setTime(format.parse(fechaNacimiento.getText().toString()));

                //Si es mayor de edad quedará registrado y devolverá al MainActivity que el resultado ha sido OK
                if(mayoriaDeEdad(nacimiento)) {
                    Toast.makeText(this, getResources().getText(R.string.toast_Quedas_registrado), Toast.LENGTH_LONG).show();
                    setResult(RESULT_OK);
                    finish();
                }else{
                    Toast.makeText(this, getResources().getText(R.string.toast_mayor_de_edad), Toast.LENGTH_LONG).show();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Método para saber si es mayor de edad o no
     * @param nacimiento
     * @return
     */
    public boolean mayoriaDeEdad(Calendar nacimiento){
        Calendar hoy = Calendar.getInstance();

        //Calculamos la diferencias de hoy y  de la fecha de nacimiento
        int difDias = hoy.get(Calendar.DAY_OF_MONTH) - nacimiento.get(Calendar.DAY_OF_MONTH);
        int difMes = hoy.get(Calendar.MONTH) - nacimiento.get(Calendar.MONTH);
        int difAnno = hoy.get(Calendar.YEAR) - nacimiento.get(Calendar.YEAR);

        //Si es mayor de 18 devuelve verdadero
        if(difAnno > 18) {
            return true;
        //Si la diferencia de edad es igual a 18 se comprueba que ya haya complido los 18
        }else if(difAnno == 18){
            //Si no los ha cumplido todavía devuelve falso
            if (difMes < 0 || (difMes == 0 && difDias < 0)) {
                return false;
            }else{
                return true;
            }
        //Si directamente la diferencia es menor de 18 devuelve false
        }else{
            return false;
        }
    }

    /**
     * Método para guardar el estado
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("NOMBRE", nombre.getText().toString());
        outState.putString("EMAIL", email.getText().toString());
        outState.putString("FECHA_NACIMIENTO", fechaNacimiento.getText().toString());
    }

    /**
     * Método para restaurar el estado
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        nombre.setText(savedInstanceState.getString("NOMBRE"));
        email.setText(savedInstanceState.getString("EMAIL"));
        fechaNacimiento.setText(savedInstanceState.getString("FECHA_NACIMIENTO"));
    }
}
