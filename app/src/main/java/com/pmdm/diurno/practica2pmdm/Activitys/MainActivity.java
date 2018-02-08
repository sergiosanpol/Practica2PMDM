package com.pmdm.diurno.practica2pmdm.Activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.pmdm.diurno.practica2pmdm.R;

/**
 * Clase principal de la aplicación
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Código para el acceso al registro
     */
    public static final int CODIGO_REGISTRO = 1;
    /**
     * Código para el acceso a las apuestas
     */
    public static final int CODIGO_APUESTA = 2;

    /**
     * Variable para saber si estamos registrados o no. En onCreate() la ponemos a false
     */
    private boolean registrado;

    private TextView apuesta;
    private SharedPreferences preferences;
    public static String apuestaMarcada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registrado = false;
        apuesta = (TextView) findViewById(R.id.tv_apuestaMarcada);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        apuestaMarcada = preferences.getString("deporte", "");
    }

    /**
     * Método que abre el registro al pulsar el botón registro. Si ya ha sido registrado no podemos volver a pulsarlo
     * @param v
     */
    public void abrirRegistro(View v){
        if(!registrado){
            startActivityForResult(new Intent(this, RegistroActivity.class), CODIGO_REGISTRO);
        }else{
            Toast.makeText(this, getResources().getText(R.string.toast_Registrado), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Método que responde al botón apuesta. Si ya te has registrado posdrás entrar en la apuestas sino no podremos acceder hasta
     * registrarnos
     * @param v
     */
    public void abrirApuestas(View v){
        if(registrado) {
            startActivityForResult(new Intent(this, ApuestasActivity.class), CODIGO_APUESTA);
        }else{
            Toast.makeText(this, getResources().getText(R.string.toast_No_Registrado), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Método que responde al botón Ajustes. Podremos acceder si se ha seleccionado ya la apuesta
     * @param v
     */
    public void abrirAjustes(View v){
        if(!apuestaMarcada.equals("") && registrado) {
            //Le pasamos la información sobre la apuesta marcada
            Intent ajustes = new Intent(this, AjustesActivity.class);
            ajustes.putExtra("APUESTA", apuestaMarcada);
            startActivity(ajustes);
        }else{
            Toast.makeText(this, getResources().getText(R.string.toast_Marcar_apuestas), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Método que responde al boton resultados
     * @param v
     */
    public void abrirResultados(View v){
        Intent result = new Intent(this, ResultadosAcivity.class);
        result.putExtra("DEPORTE", apuestaMarcada);
        startActivity(result);
    }

    /**
     * Método para procesar las respuestas devueltas por otras activitys
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Cuando accedemos al registro y nos devuelve un resultado OK
        if(requestCode == CODIGO_REGISTRO && resultCode == RESULT_OK){
            //La variable registro se pone a true indicando que ya nos hemos registrado
            registrado = true;
            //En caso de que ya hayamos elegido un deporte en preferencias
            if(!apuestaMarcada.equals("")){
                //Apuesta está oculto al inicio. Si no ha cambiado de visibilidad se le cambia a visible
                if(apuesta.getVisibility() ==  View.GONE){
                    apuesta.setVisibility(View.VISIBLE);
                }
                //Mostramos la apuesta predeterminada en las preferencias si existiera una seleccionaa
                apuestaMarcada = preferences.getString("deporte", "");
                apuesta.setText(getResources().getText(R.string.apuesta_marcada) + apuestaMarcada);
            }
        //Cuando accedemos a la apuesta y nos devuelve un resultado OK
        }else if(requestCode == CODIGO_APUESTA && resultCode == RESULT_OK){
            //Apuesta está oculto al inicio. Si no ha cambiado de visibilidad se le cambia a visible
            if(apuesta.getVisibility() ==  View.GONE){
                apuesta.setVisibility(View.VISIBLE);
            }

            //Recogemos el dato devuelto
            apuestaMarcada = data.getStringExtra("APUESTA");
            //Mostramos la apuesta realizada en la pantalla principal
            apuesta.setText(getResources().getText(R.string.apuesta_marcada) + apuestaMarcada);
        }
    }

    /**
     * Método para crear el menu del AppBar
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    /**
     * Método para dar funcinalidad a los botones del menú
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.item_ayuda){
            startActivity(new Intent(this, AyudaActivity.class));
        }else if(id == R.id.item_acercaDe){
            startActivity(new Intent(this, AcercaDeActivity.class));
        }else if(id == R.id.item_preferencias){
            startActivity(new Intent(this, PreferenciasActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Método para salvar el estado
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean("REGISTRADO", registrado);
        outState.putString("APUESTA", apuesta.getText().toString());
        outState.putString("APUESTA_MARCADA", apuestaMarcada);
    }

    /**
     * Método para restaurar el estado
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        registrado = savedInstanceState.getBoolean("REGISTRADO");
        apuesta.setText(savedInstanceState.getString("APUESTA"));
        apuestaMarcada = savedInstanceState.getString("APUESTA_MARCADA");

        //Si ya se ha escogido una apuesta se muestra la vista
        if(!apuesta.getText().toString().isEmpty()){
            apuesta.setVisibility(View.VISIBLE);
        }
    }
}
