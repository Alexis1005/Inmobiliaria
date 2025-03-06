package com.mycompany.inmobiliaria.modelo;

public class Propiedades {
    private int id_propiedad;
    private int id_tipo;
    private int id_agente;
    private String direccion;
    private double precio;
    private String descripcion;
    private EstadoPropiedad estado; // Usamos el enum EstadoPropiedad
    private ModalidadPropiedad modalidad; // Usamos el enum ModalidadPropiedad

    // Constructor vacío
    public Propiedades() {
    }

    // Constructor con todos los atributos
    public Propiedades(int id_propiedad, int id_tipo, int id_agente, String direccion, double precio, String descripcion, EstadoPropiedad estado, ModalidadPropiedad modalidad) {
        this.id_propiedad = id_propiedad;
        this.id_tipo = id_tipo;
        this.id_agente = id_agente;
        this.direccion = direccion;
        this.precio = precio;
        this.descripcion = descripcion;
        this.estado = estado;
        this.modalidad = modalidad;
    }
    
    // Enums declarados dentro de la clase
    public enum EstadoPropiedad {
        disponible, VENDIDO, ALQUILADO, ARRENDADO
    }

    public enum ModalidadPropiedad {
        venta, arrendamiento, alquiler
    }


    // Getters y Setters
    public int getId_propiedad() {
        return id_propiedad;
    }

    public void setId_propiedad(int id_propiedad) {
        this.id_propiedad = id_propiedad;
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public int getId_agente() {
        return id_agente;
    }

    public void setId_agente(int id_agente) {
        this.id_agente = id_agente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoPropiedad getEstado() {
        return estado;
    }

    public void setEstado(EstadoPropiedad estado) {
        this.estado = estado;
    }

    public ModalidadPropiedad getModalidad() {
        return modalidad;
    }

    public void setModalidad(ModalidadPropiedad modalidad) {
        this.modalidad = modalidad;
    }

    // Método toString
    @Override
    public String toString() {
        return "Propiedades{" +
                "id_propiedad=" + id_propiedad +
                ", id_tipo=" + id_tipo +
                ", id_agente=" + id_agente +
                ", direccion='" + direccion + '\'' +
                ", precio=" + precio +
                ", descripcion='" + descripcion + '\'' +
                ", estado=" + estado +
                ", modalidad=" + modalidad +
                '}';
    }
}