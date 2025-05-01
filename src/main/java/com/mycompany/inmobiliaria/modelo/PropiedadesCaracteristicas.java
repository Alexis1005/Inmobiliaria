package com.mycompany.inmobiliaria.modelo;

public class PropiedadesCaracteristicas {
    private int id_propiedad;
    private int id_caracteristica;
    private String detalle;
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Constructor vacío
    public PropiedadesCaracteristicas() {
    }

    // Constructor con parámetros
    public PropiedadesCaracteristicas(int idPropiedad, int idCaracteristica) {
        this.id_propiedad = idPropiedad;
        this.id_caracteristica = idCaracteristica;
    }

    // Constructor con parámetros incluyendo detalle
    public PropiedadesCaracteristicas(int idPropiedad, int idCaracteristica, String detalle) {
        this.id_propiedad = idPropiedad;
        this.id_caracteristica = idCaracteristica;
        this.detalle = detalle;
    }

    // Getters y setters
    public int getId_propiedad() {
        return id_propiedad;
    }

    public void setId_propiedad(int idPropiedad) {
        this.id_propiedad = idPropiedad;
    }

    public int getId_caracteristica() {
        return id_caracteristica;
    }

    public void setId_caracteristica(int idCaracteristica) {
        this.id_caracteristica = idCaracteristica;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    @Override
    public String toString() {
        return "PropiedadesCaracteristicas{" +
               "id_propiedad=" + id_propiedad +
               ", id_paracteristica=" + id_caracteristica +
               ", detalle='" + detalle + '\'' +
               '}';
    }
}