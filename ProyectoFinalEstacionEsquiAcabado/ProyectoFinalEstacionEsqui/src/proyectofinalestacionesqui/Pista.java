/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalestacionesqui;

/**
 *
 * @author Yann
 */
public class Pista {
    private int id;
    private String nombre;
    private int alturaInicio;
    private int alturaFin;
    private boolean pistaAbierta;
    private double temp;
    private String nivel;

    public Pista() {
    }

    public Pista(int id, String nombre, int alturaInicio, int alturaFin, boolean pistaAbierta, double temp, String nivel) {
        this.id = id;
        this.nombre = nombre;
        this.alturaInicio = alturaInicio;
        this.alturaFin = alturaFin;
        this.pistaAbierta = pistaAbierta;
        this.temp = temp;
        this.nivel = nivel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAlturaInicio() {
        return alturaInicio;
    }

    public void setAlturaInicio(int alturaInicio) {
        this.alturaInicio = alturaInicio;
    }

    public int getAlturaFin() {
        return alturaFin;
    }

    public void setAlturaFin(int alturaFin) {
        this.alturaFin = alturaFin;
    }

    public boolean isPistaAbierta() {
        return pistaAbierta;
    }

    public void setPistaAbierta(boolean pistaAbierta) {
        this.pistaAbierta = pistaAbierta;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
    
    
    
}
