/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalestacionesqui;

/**
 * Esta clase se encarga de construir el objeto Material, utilizado para guardar informacion sobre un material que se quiere alquilar
 * @author Yann
 */
public class Material {
    private int talla;
    private String tipoMaterial;
    private String nombreTipoMaterial;

    public Material() {
    }

    public Material(int talla, String tipoMaterial, String nombreTipoMaterial) {
        this.talla = talla;
        this.tipoMaterial = tipoMaterial;
        this.nombreTipoMaterial = nombreTipoMaterial;
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

    public String getNombreTipoMaterial() {
        return nombreTipoMaterial;
    }

    public void setNombreTipoMaterial(String nombreTipoMaterial) {
        this.nombreTipoMaterial = nombreTipoMaterial;
    }
    
    
    
}
