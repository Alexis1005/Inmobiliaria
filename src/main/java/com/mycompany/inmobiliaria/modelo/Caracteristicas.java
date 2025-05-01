
package com.mycompany.inmobiliaria.modelo;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Caracteristicas {
    @JsonProperty("idCaracteristica") 
    int id_caracteristica;
    int id_tipo;
    String nombre;
    String detalle;

    // Constructor
    
    public Caracteristicas(){
    };
    
    
    public Caracteristicas(String nombre, String detalle, int id_tipo) {
        this.nombre = nombre;
        this.detalle = detalle;
        this.id_tipo = id_tipo;
    }
    
    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
    
    @JsonProperty("idCaracteristica")
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
