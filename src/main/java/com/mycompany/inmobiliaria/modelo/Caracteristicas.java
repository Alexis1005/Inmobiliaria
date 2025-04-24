
package com.mycompany.inmobiliaria.modelo;


public class Caracteristicas {

    int id_caracteristica;
    String nombre;
    
    // Constructor
    public Caracteristicas(String nombre) {
        this.nombre = nombre;
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
