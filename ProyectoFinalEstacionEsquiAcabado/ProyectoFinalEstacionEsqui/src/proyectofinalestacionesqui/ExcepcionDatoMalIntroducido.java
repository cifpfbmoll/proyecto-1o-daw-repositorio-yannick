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
public class ExcepcionDatoMalIntroducido extends Exception{
    private String mensaje;

    public ExcepcionDatoMalIntroducido() {
    }

    public ExcepcionDatoMalIntroducido(String datoMalIntroducido) {
        this.mensaje = "Has introducido mal " + datoMalIntroducido;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
}
