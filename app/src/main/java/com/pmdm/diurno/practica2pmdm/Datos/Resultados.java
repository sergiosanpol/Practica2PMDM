package com.pmdm.diurno.practica2pmdm.Datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Clase que gestiona la base de datos de resultados
 * Created by g_ele on 07/02/2018.
 */

public class Resultados extends SQLiteOpenHelper {

    public static final String NOMBRE_BD = "apuestas";

    public Resultados(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS apuestas");

        sqLiteDatabase.execSQL("CREATE TABLE apuestas(" +
                "idPartido INTEGER PRIMARY KEY AUTOINCREMENT," +
                "equipo1 VARCHAR NOT NULL," +
                "equipo2 VARCHAR NOT NULL," +
                "resultado1 INTEGER NOT NULL," +
                "resultado2 INTEGER NOT NULL," +
                "deporte VARCHAR NOT NULL)");

        //Futbol
        sqLiteDatabase.execSQL("INSERT INTO apuestas (equipo1, equipo2, resultado1, resultado2, deporte) VALUES " +
                "('Valencia', 'Barcelona', 2, 1, 'Futbol')");
        sqLiteDatabase.execSQL("INSERT INTO apuestas (equipo1, equipo2, resultado1, resultado2, deporte) VALUES " +
                "('Rayo Vallecano', 'Getafe', 3, 1, 'Futbol')");
        sqLiteDatabase.execSQL("INSERT INTO apuestas (equipo1, equipo2, resultado1, resultado2, deporte) VALUES " +
                "('Almeria', 'Cadiz', 0, 2, 'Futbol')");

        //Tenis
        sqLiteDatabase.execSQL("INSERT INTO apuestas (equipo1, equipo2, resultado1, resultado2, deporte) VALUES " +
                "('Nadal', 'Federer', 2, 1, 'Tenis')");
        sqLiteDatabase.execSQL("INSERT INTO apuestas (equipo1, equipo2, resultado1, resultado2, deporte) VALUES " +
                "('Almagro', 'Del Potro', 0, 3, 'Tenis')");
        sqLiteDatabase.execSQL("INSERT INTO apuestas (equipo1, equipo2, resultado1, resultado2, deporte) VALUES " +
                "('Dimitrov', 'Djoković', 2, 3, 'Tenis')");

        //Baloncesto
        sqLiteDatabase.execSQL("INSERT INTO apuestas (equipo1, equipo2, resultado1, resultado2, deporte) VALUES " +
                "('Andorra', 'Real Madrid', 89, 87, 'Baloncesto')");
        sqLiteDatabase.execSQL("INSERT INTO apuestas (equipo1, equipo2, resultado1, resultado2, deporte) VALUES " +
                "('Bilbao Basket', 'Fuenlabrada', 77, 83, 'Baloncesto')");
        sqLiteDatabase.execSQL("INSERT INTO apuestas (equipo1, equipo2, resultado1, resultado2, deporte) VALUES " +
                "('Unicaja', 'UCAM Murcia', 78, 67, 'Baloncesto')");

        //Balonmano
        sqLiteDatabase.execSQL("INSERT INTO apuestas (equipo1, equipo2, resultado1, resultado2, deporte) VALUES " +
                "('Huesca', 'Balonmano', 22, 22, 'Balonmano')");
        sqLiteDatabase.execSQL("INSERT INTO apuestas (equipo1, equipo2, resultado1, resultado2, deporte) VALUES " +
                "('Puente Genil', 'BM Ciudad Encantada', 29, 29, 'Balonmano')");
        sqLiteDatabase.execSQL("INSERT INTO apuestas (equipo1, equipo2, resultado1, resultado2, deporte) VALUES " +
                "('Teucro', 'Zamora', 23, 22, 'Balonmano')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     * Método que devuelve los resultados del deporte seleccionado. En caso de que no especifiquemos el deporte devolverá todos
     * los resultados de la base de datos
     * @param deporte
     * @return
     */
    public Cursor mostrarResultados(String deporte){
        String columnas[] = {"idPartido", "equipo1", "equipo2", "resultado1", "resultado2", "deporte"};
        String deporteArray[] = {deporte};
        Cursor cursor;
        //Abirmos la BD en modo lectura
        SQLiteDatabase database = getReadableDatabase();

        //Si no se especifica deporte devuelve todos los resultados
        if(deporte == null || deporte.equals("")){
            cursor = database.query(NOMBRE_BD, columnas, null, null, null, null, null);
        }else{
            cursor = database.query(NOMBRE_BD, columnas, "deporte=?", deporteArray, null, null, null);
        }

        return cursor;
    }

    /**
     * Método que añade un resultado a la base de datos
     * @param partido
     * @return Si devuelve mayor que 0 la fila ha sido insertada
     */
    public boolean annadirResultado(Partido partido){
        //Abrimos la BD en modo escritura
        SQLiteDatabase database = getWritableDatabase();
        ContentValues datos = new ContentValues();

        //Si está en inglés lo traduce
        if(partido.getDeporte() != null){
            if(partido.getDeporte().equals("Football")){
                partido.setDeporte("Futbol");
            }else if(partido.getDeporte().equals("Tennis")){
                partido.setDeporte("Tenis");
            }else if(partido.getDeporte().equals("Basketball")){
                partido.setDeporte("Baloncesto");
            }else if(partido.getDeporte().equals("Handball")){
                partido.setDeporte("Balonmano");
            }
        }

        //Añadimos los valores pasados por el objeto de partido
        datos.put("equipo1", partido.getEquipo1());
        datos.put("equipo2", partido.getEquipo2());
        datos.put("resultado1", partido.getResultado1());
        datos.put("resultado2", partido.getResultado2());
        datos.put("deporte", partido.getDeporte());

        //Insertamos en la base de datos
        long introducido = database.insert(NOMBRE_BD, null, datos);

        return introducido > 0;
    }

}
