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
public class ForfaitDia extends Forfait {
    private final int precio = 20;
    private final int precioSeguro = 3;
    private boolean seguroContratado;

    public ForfaitDia() {
    }

    public ForfaitDia(boolean seguroContratado) {
        this.seguroContratado = seguroContratado;
    }

    public ForfaitDia(boolean seguroContratado, int numForfait, String nombre, String apellidos, String dni) {
        super(numForfait, nombre, apellidos, dni);
        this.seguroContratado = seguroContratado;
    }
    
    
}

