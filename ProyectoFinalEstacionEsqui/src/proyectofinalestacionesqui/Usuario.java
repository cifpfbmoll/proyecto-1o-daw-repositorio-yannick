/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalestacionesqui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yann
 */
public class Usuario implements PistasEsqui{
    private String dni;
    private String nombre;
    private String apellidos;
    private String fecha_nacimiento;

    public Usuario() {
    }

    public Usuario(String dni, String nombre, String apellidos, String fecha_nacimiento) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        boolean dniValido = comprobarDni(dni);
        if(dniValido){
            this.dni = dni;
        }
        else{
            try {
                throw new ExcepcionDatoMalIntroducido("DNI");
            } catch (ExcepcionDatoMalIntroducido ex) {
                System.out.println(ex.getMensaje());
            }
        }
        //this.dni = dni;
         

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        //Pasamos el nombre por el metodo ponerMayusculasNombreApellido() para
        //que nos guarde el nombre en mayusculas si el usuario no lo ha hecho
        nombre = ponerMayusculasNombreApellido(nombre);
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        //Pasamos el apellido por el metodo ponerMayusculasNombreApellido() para
        //que nos guarde el nombre en mayusculas si el usuario no lo ha hecho
        apellidos = ponerMayusculasNombreApellido(apellidos);
        this.apellidos = apellidos;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
    
    //Metodo para poner la primera letra en Mayuscula de un nombre compuesto o no
    //o de los apellidos
    public String ponerMayusculasNombreApellido(String nomAp){
        //Pasamos el String de nombre o apellidos a un array de chars
        char[] cfr = nomAp.toCharArray();
        
        //La primera letra siempre es mayuscula, asi que la cojemos y la guardamos
        //en un String
        String nomApFinal = nomAp.substring(0, 1).toUpperCase();
        
        //Aqui comprobamos si hay espacios (en el array de chars) a partir del 
        //segundo caracter, si hay un espacio el caracter siguiente es cambiado
        //a mayusculas despues guardamos todos los caracteres en un string pero
        //esta vez con las Mayusculas en los nombres y apellidos
        for(int i = 1; i<cfr.length; i++) {
           if(cfr[i] == ' '){
                cfr[i+1] = Character.toUpperCase(cfr[i+1]);
           }
           nomApFinal += cfr[i];
        }
        return nomApFinal;
    }
    
    public boolean comprobarDni(String dni){
        boolean resultadoComprobado = false;
        if(dni=="0"){
             resultadoComprobado = true;
        }
        else{
            int sumadorDigitos = 0;
            boolean ultimoLetra = false;
            for(int i=0;i<8;i++){
                char c = dni.charAt(i);
                if(Character.isDigit(c)){
                    sumadorDigitos++;
                }
            }
            if(Character.isLetter(dni.charAt(8))){
                ultimoLetra = true;
            }
            if(sumadorDigitos==9&&ultimoLetra){
                resultadoComprobado = true;
            }
        }
        return resultadoComprobado;
    }
    
    /*public boolean comprobarFecha(String fecha){
        
    }*/
}
