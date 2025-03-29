
package com.mycompany.inmobiliaria.modelo;


public class Caracteristicas {

    private int id_caracteristica;
    private String nombre;
    private String detalle;
    
    // Constructor
    public Caracteristicas(){
    }
    
    public Caracteristicas(String nombre, String detalle) {
        this.nombre = nombre;
        this.detalle = detalle;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public int getId_caracteristica() {
        return id_caracteristica;
    }

    public void setId_caracteristica(int id_caracteristica) {
        this.id_caracteristica = id_caracteristica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

 
    
}
