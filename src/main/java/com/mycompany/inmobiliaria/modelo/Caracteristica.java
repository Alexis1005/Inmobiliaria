package com.mycompany.inmobiliaria.modelo;

public class Caracteristica {
    private int idCaracteristica;
    private String nombre;
    private String detalle;
    private int tipoPropiedadId;

    // Constructor vacío
    public Caracteristica() {}

    // Constructor con parámetros
    public Caracteristica(String nombre, String detalle, int tipoPropiedadId) {
        this.nombre = nombre;
        this.detalle = detalle;
        this.tipoPropiedadId = tipoPropiedadId;
    }

    // Getters y setters
    public int getId_caracteristica() {
        return idCaracteristica;
    }

    public void setId_caracteristica(int idCaracteristica) {
        this.idCaracteristica = idCaracteristica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public int getTipoPropiedadId() {
        return tipoPropiedadId;
    }

    public void setTipoPropiedadId(int tipoPropiedadId) {
        this.tipoPropiedadId = tipoPropiedadId;
    }
}