/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalestacionesqui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import static proyectofinalestacionesqui.ProyectoFinalEstacionEsqui.establecerConexion;

/**
 * Esta clase nos sirve para instanciar un objeto tipo NuevoMaterial, lo usamos para agrupar la informacion de un nuevo material y añadirlo a la base de datos
 * @author Yann
 */
public class NuevoMaterial {
    private String id;
    private int talla;
    private String tipoMaterial;
    private double precio;

    public NuevoMaterial() {
    }

    public NuevoMaterial(String id, int talla, String tipoMaterial, double precio) {
        this.id = id;
        this.talla = talla;
        this.tipoMaterial = tipoMaterial;
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTalla() {
        return talla;
    }

    public void setTalla(int talla) {
        this.talla = talla;
    }

    public String getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    /**
     * Con este metodo llenamos el objeto tipo NuevoMaterial que ha sido pasado desde añadirNuevoMaterial() con los datos del nuevo material y  luego lo a agregar a la base de datos.
     * @throws SQLException 
     */
    public void añadirMaterialNuevoDB() throws SQLException{
        Scanner lector = new Scanner(System.in);
        System.out.println("\n----Añadir nuevo material----");
        //Pedimos los datos del nuevo material y lo guardamos en el objeto
        System.out.println("Dime el id del material");
        String id = lector.next();
        this.setId(id);
        System.out.println("Dime la talla del material");
        int talla = lector.nextInt();
        this.setTalla(talla);
        System.out.println("Dime el tipo de material (tabla, esquis, botas_snow, botas_esqui)");
        String tipoMaterial = lector.next();
        this.setTipoMaterial(tipoMaterial);
        System.out.println("Dime el precio de alquiler del material por 1 dia");
        double precio = lector.nextDouble();
        this.setPrecio(precio);
        
        //Lo pasamos a la BD
        Connection con = establecerConexion();
        String strAñadirNuevoMaterial = "INSERT into material VALUES (?,?,?,?,true) ";
        PreparedStatement stAñadirNuevoMaterial = con.prepareStatement(strAñadirNuevoMaterial);
        stAñadirNuevoMaterial.setString(1,this.getId());
        stAñadirNuevoMaterial.setInt(2, this.getTalla());
        stAñadirNuevoMaterial.setString(3, this.getTipoMaterial());
        stAñadirNuevoMaterial.setDouble(4, this.getPrecio());
        boolean n = stAñadirNuevoMaterial.execute();
        System.out.println("Material añadido!");
    }
}
