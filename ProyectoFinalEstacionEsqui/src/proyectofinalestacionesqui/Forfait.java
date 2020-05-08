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
public class Forfait implements PistasEsqui {
    private int numForfait;
    private String nombre;
    private String apellidos;
    private String dni;

    public Forfait() {
    }

    public Forfait(int numForfait, String nombre, String apellidos, String dni) {
        this.numForfait = numForfait;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
    }

    public int getNumForfait() {
        return numForfait;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setNumForfait(int numForfait) {
        this.numForfait = numForfait;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
}

