/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalestacionesqui;

/**
 * Con esta clase Usuario intanciamos el objeto Usuario y una vez instanciado
 * recogemos los datos del usuario para enviarlos a la base de datos
 * @author Yann
 */
public class Usuario {
    private String dni;
    private String nombre;
    private String apellidos;
    private String fecha_nacimiento;

    /**
     * Metodo constructor vacio
     */
    public Usuario() {
    }

    /**
     * Metodo constructor con parametros
     * @param dni DNI del usuario
     * @param nombre nombre del usuario
     * @param apellidos apellidos del usuario
     * @param fecha_nacimiento  fecha de nacimiento del usuario
     */
    public Usuario(String dni, String nombre, String apellidos, String fecha_nacimiento) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    /**
     * Getter del dni
     * @return nos devuelve el dni del Usuario
     */
    public String getDni() {
        return dni;
    }

    /**
     * Setter del dni
     * @param dni le pasamos el dni del Usuario por parametro
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Getter del nombre
     * @return nos retorna el nombre del Usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter del nombre. En este caso le ponemos la primera letra del nombre en
     * mayuscula, si es un nombre compuesto tambien, con el metodo ponerMayusculasNombreApellido()
     * @param nombre le pasamos el nombre del Usuario por parametro
     */
    public void setNombre(String nombre) {
        //Pasamos el nombre por el metodo ponerMayusculasNombreApellido() para
        //que nos guarde el nombre en mayusculas si el usuario no lo ha hecho
        nombre = ponerMayusculasNombreApellido(nombre);
        this.nombre = nombre;
    }

    /**
     * Getter del nombre
     * @return nos retorna el apellido del Usuario
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Setter del apellido. En este caso le ponemos la primera letra de los apellidos en
     * mayuscula, tenga tantos apellidos como tenga, con el metodo ponerMayusculasNombreApellido()
     * @param apellidos le pasamos los apellidos del Usuario por parametro
     */
    public void setApellidos(String apellidos) {
        //Pasamos el apellido por el metodo ponerMayusculasNombreApellido() para
        //que nos guarde el nombre en mayusculas si el usuario no lo ha hecho
        apellidos = ponerMayusculasNombreApellido(apellidos);
        this.apellidos = apellidos;
    }

    /**
     * Getter de la fecha de nacimiento
     * @return nos retorna la fecha de nacimiento del usuario
     */ 
    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    /**
     * Setter de la fecha de nacimiento. Cuando la fecha llega a este Setter deveria
     * estar bien formateada
     * @param fecha_nacimiento le pasamos por parametro la fecha de nacimiento
     */
    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
    
    /**
     * Metodo para poner la primera letra en Mayuscula de un nombre compuesto o no
     * o de los apellidos
     * @param nomAp le pasamos por parametro el nombre o los apellidos
     * @return nos retorna los nombres o los apellidos con la primera letra en mayusculas
     */
    public String ponerMayusculasNombreApellido(String nomAp){
        //Pasamos todo el String a minusculas, por si hay alguna mayuscula que no toca
        nomAp = nomAp.toLowerCase();
        
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
}
