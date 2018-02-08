package com.pmdm.diurno.practica2pmdm.Activitys;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.pmdm.diurno.practica2pmdm.Datos.Partido;
import com.pmdm.diurno.practica2pmdm.Datos.Resultados;
import com.pmdm.diurno.practica2pmdm.R;

/**
 * Clase de la actividad resultados
 */
public class ResultadosAcivity extends AppCompatActivity {

    private Resultados resultados;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados_acivity);

        //Declaración de variables locales
        String deporte = null;
        Cursor cursor;
        TableLayout tableLayout;
        TableRow tableRow;
        TextView idPartido, equipo1, equipo2, resultado1, resultado2, deporteTv;

        //Creamos el objeto de la base de datos de resultados
        resultados = new Resultados(this, Resultados.NOMBRE_BD, null, 1);

        if(getIntent().hasExtra("DEPORTE")){
            deporte = getIntent().getStringExtra("DEPORTE");
        }

        //Si la aplicación está en ingles traducimos los deportes,ya que la base de datos está en español
        if(deporte != null){
            if(deporte.equals("Football")){
                deporte = "Futbol";
            }else if(deporte.equals("Tennis")){
                deporte = "Tenis";
            }else if(deporte.equals("Basketball")){
                deporte = "Baloncesto";
            }else if(deporte.equals("Handball")){
                deporte = "Balonmano";
            }
        }

        //Obtenemos un cursor con el deporte seleccionado. En caso de que no haya sido especificado el deporte se
        //muestran todos los deportes
        cursor = resultados.mostrarResultados(deporte);

        //Cargamos el tableLayout de la vista XML
        tableLayout = (TableLayout) findViewById(R.id.tableLayoutResultados);
        //Mostramos un titulo con el deporte seleccionado
        deporteTv = (TextView) findViewById(R.id.deporteTv);
        if(deporte != null) {
            deporteTv.setText(deporte);
        }else{
            deporteTv.setText(getResources().getText(R.string.resultados));
        }

        //Nos movemos por el cursor
        if(cursor.moveToFirst()){
            do{
                //Por cada iteración del cursor crearemos una fila en la tabla con la información correspondiente
                tableRow = (TableRow) LayoutInflater.from(this).inflate(R.layout.fila_tabla, null, false);
                idPartido = (TextView) tableRow.findViewById(R.id.id_resultado);
                equipo1 = (TextView) tableRow.findViewById(R.id.equipo1);
                equipo2 = (TextView) tableRow.findViewById(R.id.equipo2);
                resultado1 = (TextView) tableRow.findViewById(R.id.resultado1);
                resultado2 = (TextView) tableRow.findViewById(R.id.resultado2);

                //Rellenamos los datos del cursor
                idPartido.setText(String.valueOf(cursor.getInt(0)));
                equipo1.setText(cursor.getString(1));
                equipo2.setText(cursor.getString(2));
                resultado1.setText(String.valueOf(cursor.getInt(3)));
                resultado2.setText(cursor.getString(4));

                //Añadimos la fila a la tabla
                tableLayout.addView(tableRow);
            }while(cursor.moveToNext());
        }

        //Adaptamos el spinner para añadir nuevos deportes
        spinner = (Spinner) findViewById(R.id.spinnerDeportes);
        String deportes[] = {String.valueOf(getResources().getText(R.string.cb_futbol)),
                             String.valueOf(getResources().getText(R.string.cb_tenis)),
                             String.valueOf(getResources().getText(R.string.cb_baloncesto)),
                             String.valueOf(getResources().getText(R.string.cb_balonmano))};
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, deportes));
    }

    /**
     * Método para añadir un nuevo resultado en la base de datos al pulsar el boton
     * @param v
     */
    public void annadirNuevoResultado(View v){
        TextView equipo1, equipo2, resultado1, resultado2;
        Partido partido;

        equipo1 = (TextView) findViewById(R.id.equipoJugador1);
        equipo2 = (TextView) findViewById(R.id.equipoJugador2);
        resultado1 = (TextView) findViewById(R.id.resultado1Nuevo);
        resultado2 = (TextView) findViewById(R.id.resultado2Nuevo);

        //Comprobar que los datos están rellenos
        if(!equipo1.getText().toString().equals("") && !equipo2.getText().toString().equals("") &&
                !resultado1.getText().toString().equals("") && !resultado2.getText().toString().equals("")) {
            try {
                //Encapsulamos los datos en el objeto del partido
                partido = new Partido(equipo1.getText().toString(), equipo2.getText().toString(),
                        Integer.parseInt(resultado1.getText().toString()),
                        Integer.parseInt(resultado2.getText().toString()),
                        spinner.getSelectedItem().toString());

                //Esperamos una repuesta del método. Si devuelve mayor de 0 se ha insertado la nueva fila
                if (resultados.annadirResultado(partido)) {
                    Toast.makeText(this, getResources().getText(R.string.resultadoAnnadido), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, getResources().getText(R.string.resultadoAnnadido), Toast.LENGTH_LONG).show();
                }
            } catch (NumberFormatException e) {

            }
        }else{
            Toast.makeText(this, getResources().getText(R.string.toast_Falan_datos_por_rellenar), Toast.LENGTH_LONG).show();
        }
    }

}
