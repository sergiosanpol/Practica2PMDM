package com.pmdm.diurno.practica2pmdm.Datos;

/**
 * Clase que encapsula un partido
 * Created by g_ele on 07/02/2018.
 */

public class Partido {

    private String equipo1;
    private String equipo2;
    private int resultado1;
    private int resultado2;
    private String deporte;

    public Partido(String equipo1, String equipo2, int resultado1, int resultado2, String deporte) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.resultado1 = resultado1;
        this.resultado2 = resultado2;
        this.deporte = deporte;
    }

    public String getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(String equipo1) {
        this.equipo1 = equipo1;
    }

    public String getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(String equipo2) {
        this.equipo2 = equipo2;
    }

    public int getResultado1() {
        return resultado1;
    }

    public void setResultado1(int resultado1) {
        this.resultado1 = resultado1;
    }

    public int getResultado2() {
        return resultado2;
    }

    public void setResultado2(int resultado2) {
        this.resultado2 = resultado2;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

}
