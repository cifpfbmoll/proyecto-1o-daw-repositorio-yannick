/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalestacionesqui;

/**
 * Esta clase sirve para instanciar un objeto ColorTexto con los codigos de colores que necesitamos para la aplicacion y que nos permite acceder facilmente a pintar texto con color
 * @author Yann
 */
public class ColorTexto {
    private String reset;
    private String textoAzul;
    private String textoVerde;
    private String textoRojo;
    private String textoBlancoFondoRojo;
    private String textoBlancoFondoAmarillo;
    private String textoBlancoFondoAzul;
    private String textoBlancoFondoMorado;
    private String textoBlancoFondoAzulCyan;
    
    public ColorTexto() {
    }

    public ColorTexto(String reset, String textoAzul, String textoVerde, String textoRojo, String textoBlancoFondoRojo, String textoBlancoFondoAmarillo, String textoBlancoFondoAzul, String textoBlancoFondoMorado, String textoBlancoFondoAzulCyan) {
        this.reset = reset;
        this.textoAzul = textoAzul;
        this.textoVerde = textoVerde;
        this.textoRojo = textoRojo;
        this.textoBlancoFondoRojo = textoBlancoFondoRojo;
        this.textoBlancoFondoAmarillo = textoBlancoFondoAmarillo;
        this.textoBlancoFondoAzul = textoBlancoFondoAzul;
        this.textoBlancoFondoMorado = textoBlancoFondoMorado;
        this.textoBlancoFondoAzulCyan = textoBlancoFondoAzulCyan;
    }

    public String getReset() {
        return reset;
    }

    public void setReset(String reset) {
        this.reset = reset;
    }

    public String getTextoAzul() {
        return textoAzul;
    }

    public void setTextoAzul(String textoAzul) {
        this.textoAzul = textoAzul;
    }

    public String getTextoVerde() {
        return textoVerde;
    }

    public void setTextoVerde(String textoVerde) {
        this.textoVerde = textoVerde;
    }

    public String getTextoRojo() {
        return textoRojo;
    }

    public void setTextoRojo(String textoRojo) {
        this.textoRojo = textoRojo;
    }

    public String getTextoBlancoFondoRojo() {
        return textoBlancoFondoRojo;
    }

    public void setTextoBlancoFondoRojo(String textoBlancoFondoRojo) {
        this.textoBlancoFondoRojo = textoBlancoFondoRojo;
    }

    public String getTextoBlancoFondoAmarillo() {
        return textoBlancoFondoAmarillo;
    }

    public void setTextoBlancoFondoAmarillo(String textoBlancoFondoAmarillo) {
        this.textoBlancoFondoAmarillo = textoBlancoFondoAmarillo;
    }

    public String getTextoBlancoFondoAzul() {
        return textoBlancoFondoAzul;
    }

    public void setTextoBlancoFondoAzul(String textoBlancoFondoAzul) {
        this.textoBlancoFondoAzul = textoBlancoFondoAzul;
    }

    public String getTextoBlancoFondoMorado() {
        return textoBlancoFondoMorado;
    }

    public void setTextoBlancoFondoMorado(String textoBlancoFondoMorado) {
        this.textoBlancoFondoMorado = textoBlancoFondoMorado;
    }

    public String getTextoBlancoFondoAzulCyan() {
        return textoBlancoFondoAzulCyan;
    }

    public void setTextoBlancoFondoAzulCyan(String textoBlancoFondoAzulCyan) {
        this.textoBlancoFondoAzulCyan = textoBlancoFondoAzulCyan;
    }
    
}
