/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalestacionesqui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        System.out.println("Dime tu fecha de nacimiento con el siguiente formato dd/mm/aaaa");
        String fechaNacimiento = lector.next();
        Usuario usuario = new Usuario();
        usuario.setDni(dni);
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setFecha_nacimiento(fechaNacimiento);
        
        //Parte en la que introducimos la informacion en la BD
        Connection con = establecerConexion();
        String insertaCliente = "INSERT INTO clientes (dni, nombre, apellidos, fecha_nacimiento) VALUES (?, ?, ?, str_to_date(?,'%d/%m/%Y'))";
        PreparedStatement usuarios = con.prepareStatement(insertaCliente);
        usuarios.setString(1,usuario.getDni());
        usuarios.setString(2,usuario.getNombre());
        usuarios.setString(3,usuario.getApellidos());
        usuarios.setString(4,usuario.getFecha_nacimiento());
        boolean n = usuarios.execute();
        usuarios.close();
        
        //Parte en la que printamos los datos que ha introducido y lo guardamos en el log de usuarios
        Statement stUltimoId = con.createStatement ();
        ResultSet rsUltimoId = stUltimoId.executeQuery ("Select LAST_INSERT_ID()");   
        rsUltimoId.next ();
        int id = rsUltimoId.getInt(1);
        String datosUsReg = "\n---- Datos Usuario ----\nIdentificador: "+id;
        if(usuario.getDni().equals("0")){
            datosUsReg += " (necesario para identificarte)\nDNI: sin DNI";
        }
        else{
            datosUsReg += "\nDNI: "+usuario.getDni();
        }
        datosUsReg += "\nNombre: "+usuario.getNombre()+"\nApellidos: "+usuario.getApellidos()+"\nFecha de nacimiento: "+usuario.getFecha_nacimiento();
        System.out.println(datosUsReg); //Printamos los datos
        
        //Añadimos al log de usuarios
        
        //Obtenemos fecha actual y añadimos la info al final
        Date date = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        datosUsReg += "\nHora y fecha: "+hourdateFormat.format(date);
        
        //Añadimos al log de usuarios
        BufferedWriter bw = null;
        FileWriter fw = null;
        
        try{
            File file = new File("logUsuarios.txt");
            // Si el archivo no existe, se crea!
            if (!file.exists()) {
                file.createNewFile();
            }
            // flag true, indica adjuntar información al archivo.
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            bw.write(datosUsReg);
        }
        catch(IOException e) {
            e.printStackTrace();
        }finally {
            try {
                            //Cierra instancias de FileWriter y BufferedWriter
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        System.out.println("Proceso completado");
        
        if (rsUltimoId!= null) rsUltimoId.close (); //cierra el objeto ResultSet llamado rsUltimoId 
        if (stUltimoId!= null) stUltimoId.close ();//cierra el objeto Statement llamado stUltimoId
        if (con!= null) con.close (); //cierra el objeto Connection llamado con
    }
    
    public static Connection establecerConexion() throws SQLException{  
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/estacion_esqui", "root", "");  
    }
}

/*//Parte en la que introducimos la informacion en la BD
        Connection con = establecerConexion();
        PreparedStatement usuarios = null;
        String insertaCliente = "INSERT INTO clientes (dni, nombre, apellidos, fecha_nacimiento) VALUES (?, ?, ?, str_to_date(?,'%d/%m/%Y'))";
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
        }*/