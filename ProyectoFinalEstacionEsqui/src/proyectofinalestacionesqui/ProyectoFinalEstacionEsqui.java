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
            menuPrincipal();
        } catch (SQLException ex) {
            System.out.println(ex.getSQLState());
            System.out.println(ex.getMessage());
            Logger.getLogger(ProyectoFinalEstacionEsqui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void menuPrincipal() throws SQLException {
        // TODO code application logic here
        Scanner lector = new Scanner(System.in);
        boolean SeguirMostrandoMenu = true;
        while(SeguirMostrandoMenu){
            System.out.println("\n---- Menu principal ----");
            System.out.println("1- Crear usuario");
            System.out.println("1- Comprar forfaits");
            System.out.println("2- Alquilar material de esquí/snow");
            System.out.println("3- Alquilar profesor de esqui/snow");
            System.out.println("4- Consultar información de las pistas");
            System.out.println("5- Consultar rutas por dificultad");
            System.out.println("6- Salir");
            int opcion = lector.nextInt();
            switch(opcion){
                case 1:
                    try{
                        crearUsuario();
                    }
                    catch(ExcepcionDatoMalIntroducido ex) {
                        System.out.println(ex.getMensaje()+". Vuelve a introducir los datos");
                    }
                    break;
                case 9:
                    SeguirMostrandoMenu = true;
                    break;
            }
        }    
    }
    
    /**
     * Con este metodo creamos los usuarios, en este metodo ultizamos los metodos 
     * comprobarDni(dni) y comprobarFechaNacimiento(fechaNacimiento) para comprobar
     * que los datos introducidos tengan un formato correcto.
     * @throws SQLException Excepcion producida por un fallo relacionado con la base de datos
     * @throws ExcepcionDatoMalIntroducido Excepcion producida por un dato mal introducido en el dni o en la fecha de nacimiento
     */
    public static void crearUsuario() throws SQLException, ExcepcionDatoMalIntroducido {
        
        //Pedimos los datos y los guardamos en un objeto
        Usuario usuario = new Usuario();
        Scanner lector = new Scanner(System.in);
        System.out.println("\n---- Crear usuario ----");
        System.out.println("Dime tu DNI con este formato 12345678Q, si no tienes escribe 0");
        String dni = lector.nextLine();
        boolean dniValido = comprobarDni(dni);
        if(dniValido){
            usuario.setDni(dni);
        }
        else{
            throw new ExcepcionDatoMalIntroducido("el DNI");
        } 
        System.out.println("Dime tu nombre");
        String nombre = lector.nextLine();
        usuario.setNombre(nombre);
        System.out.println("Dime tus apellidos");
        String apellidos = lector.nextLine();
        usuario.setApellidos(apellidos);
        System.out.println("Dime tu fecha de nacimiento con el siguiente formato dd/mm/aaaa ej 01/01/1990");
        String fechaNacimiento = lector.next();
        boolean fechaValida = comprobarFechaNacimiento(fechaNacimiento);
        if(fechaValida){
            usuario.setFecha_nacimiento(fechaNacimiento);
        }
        else{
            throw new ExcepcionDatoMalIntroducido("la fecha");
        }
                
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
    
    /**
     * Con este metodo establecemos la conexion con la BD
     * @return Nos devuelve la conexion (Connection). Con esto conectamos a la BD
     * @throws SQLException Excepcion producida por un fallo relacionado con la base de datos
     */
    public static Connection establecerConexion() throws SQLException{  
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/estacion_esqui", "root", "");  
    }
    
    /**
     * Con este metodo hacemos las comprobaciones necesarias para determinar si el DNI es correcto
     * @param dni le pasamos un String con el dni por parametro
     * @return nos devuelve true o false indicando el resultado de la comprobacion
     */
    public static boolean comprobarDni(String dni){
        boolean resultadoComprobado = false;
        if(dni.equals("0")){
             resultadoComprobado = true;
        }
        else{
            if(dni.length()==9){
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
                if(sumadorDigitos==8&&ultimoLetra){
                    resultadoComprobado = true;
                }
            }
        }
        return resultadoComprobado;
    }
    
    /**
     * Con este metodo hacemos las comprobaciones necesarias para determinar si la fecha es correcta
     * @param fecha le pasamos un String con la fecha por parametro
     * @return nos devuelve true o false indicando el resultado de la comprobacion
     */
    public static boolean comprobarFechaNacimiento(String fecha){
        boolean fechaComprobada = false;
        //Si la fecha tiene un length de 10
        if(fecha.length()==10){
            //Comprobamos las barras
            boolean barrasComprobadas = false;
            if(fecha.charAt(2)=='/'&&fecha.charAt(5)=='/'){
                barrasComprobadas = true;
            }
            //Comprobamos los dias con sus meses
            boolean diasComprobados =  false;
            //Si los dias y los meses son numeros
            if(Character.isDigit(fecha.charAt(0))&&Character.isDigit(fecha.charAt(1))&&Character.isDigit(fecha.charAt(3))&&Character.isDigit(fecha.charAt(4))){
                //Pasamos los dias a int
                String numDiaSt=String.valueOf(fecha.charAt(0)) + String.valueOf(fecha.charAt(1)); 
                int numDiaInt = Integer.parseInt(numDiaSt);
                //Comprobamos que sea un numero de dia valido
                if(numDiaInt>0&&numDiaInt<32){
                    //Si es un dia valido pasamos el mes a int
                    String numMesSt=String.valueOf(fecha.charAt(3)) + String.valueOf(fecha.charAt(4)); 
                    int numMesInt = Integer.parseInt(numMesSt);
                    //Y comprobamos si el numero de dia es valido con su mes 
                    //Asimismo con este codigo nos aseguramos que nos introduzcan un mes correcto
                    if((numMesInt == 1 || numMesInt == 3 || numMesInt == 5 || numMesInt == 7 || numMesInt == 8 || numMesInt == 10 || numMesInt == 12)&&(numDiaInt<32)){
                        diasComprobados = true;
                    }
                    else if((numMesInt == 4 || numMesInt == 6 || numMesInt == 9 || numMesInt == 11)&&(numDiaInt<31)){
                        diasComprobados = true;
                    }
                    else if(numMesInt==2&&numDiaInt<30){
                        diasComprobados = true;
                    }
                }
            }
            //Comprobamos el año
            boolean añoComprobado =  false;
            //Si los caracteres donde va el año son numeros
            if(Character.isDigit(fecha.charAt(6))&&Character.isDigit(fecha.charAt(7))&&Character.isDigit(fecha.charAt(8))&&Character.isDigit(fecha.charAt(9))){
                //Los pasamos a un String
                String numAñoSt=String.valueOf(fecha.charAt(6)) + String.valueOf(fecha.charAt(7) + String.valueOf(fecha.charAt(8))) + String.valueOf(fecha.charAt(9));
                //Los convertimos a un int
                int numAñoInt = Integer.parseInt(numAñoSt);
                //Comprobamos que es un año valido
                if(numAñoInt>1910 && numAñoInt<2015){
                    añoComprobado =  true;
                }
            }
            //Si todas las comprobaciones son correctas la fecha queda comrobada
            if(barrasComprobadas&&diasComprobados&&añoComprobado){
                fechaComprobada = true;
            } 
        }
        return fechaComprobada;
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