/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalestacionesqui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

/**
 *
 * @author Yann
 */
public class ProyectoFinalEstacionEsqui {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //menuPrincipal();
            crearUsuario();
        } catch (SQLException ex) {
            System.out.println(ex.getSQLState());
            System.out.println(ex.getMessage());
            Logger.getLogger(ProyectoFinalEstacionEsqui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void menuPrincipal() {
        // TODO code application logic here
        System.out.println("1- Comprar forfaits");
        System.out.println("2- Alquilar material de esquí/snow");
        System.out.println("3- Alquilar profesor de esqui/snow");
        System.out.println("4- Consultar información de las pistas");
        System.out.println("5- Consultar rutas por dificultad");
        System.out.println("6- Salir");
    } 
    
    public static void crearUsuario() throws SQLException {
        //Pedimos los datos y los guardamos en un objeto
        Scanner lector = new Scanner(System.in);
        System.out.println("---- Crear usuario ----");
        System.out.println("Dime tu DNI, si no tienes escribe 0");
        String dni = lector.nextLine();
        System.out.println("Dime tu nombre");
        String nombre = lector.nextLine();
        System.out.println("Dime tus apellidos");
        String apellidos = lector.nextLine();
        System.out.println("Dime tu fecha de nacimiento con el siguiente formato aaaa-mm-dd");
        String fechaNacimiento = lector.next();
        Usuario usuario = new Usuario();
        usuario.setDni(dni);
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setFecha_nacimiento(fechaNacimiento);
        
        //Parte en la que introducimos la informacion en la BD
        Connection con = establecerConexion();
        PreparedStatement usuarios = null;
        String insertaCliente = "INSERT INTO clientes (dni, nombre, apellidos, fecha_nacimiento) VALUES (?, ?, ?, ?)";
        try{   
            usuarios = con.prepareStatement(insertaCliente);
            con.setAutoCommit(false);
            usuarios.setString(1,usuario.getDni());
            usuarios.setString(2,usuario.getNombre());
            usuarios.setString(3,usuario.getApellidos());
            usuarios.setString(4,usuario.getFecha_nacimiento());
            boolean n = usuarios.execute();
            con.commit();
        } catch (SQLException ex) {
            System.out.println("SQLSTATE " + ex.getSQLState() + "SQLMESSAGE" +             ex.getMessage());
            System.out.println("Hago rollback");
            con.rollback();  
        } finally{
            con.setAutoCommit(true);
            usuarios.close();
        }
    }
    
    public static Connection establecerConexion() throws SQLException{  
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/estacion_esqui", "root", "");  
    }
}
