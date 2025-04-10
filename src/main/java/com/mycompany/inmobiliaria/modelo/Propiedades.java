package com.mycompany.inmobiliaria.modelo;

import java.util.List;

public class Propiedades {
    private int id_propiedad;
    private int id_tipo;
    private int id_agente;
    private String direccion;
    private double precio;
    private String descripcion;
    private String estado;
    private String modalidad;
    private String caracteristicasGenerales;

    

    // Relación con FotosPropiedad (solo la primera imagen por simplicidad)
    private String imagen; // Ruta de la imagen principal

    // Relación con Caracteristicas (lista de características)
    private List<String> caracteristicas; // Nombres o valores de características

    // Constructor
    public Propiedades(int id_propiedad, int id_tipo, int id_agente, String direccion, double precio, 
                       String descripcion, String estado, String modalidad, String imagen, List<String> caracteristicas) {
        this.id_propiedad = id_propiedad;
        this.id_tipo = id_tipo;
        this.id_agente = id_agente;
        this.direccion = direccion;
        this.precio = precio;
        this.descripcion = descripcion;
        this.estado = estado;
        this.modalidad = modalidad;
        this.imagen = imagen;
        this.caracteristicas = caracteristicas;
        this.caracteristicasGenerales = caracteristicasGenerales;
    }
    // Constructor vacío
    public Propiedades() {
        // Opcionalmente inicializa valores por defecto
        this.id_propiedad = -1;
        this.imagen = null;
        this.caracteristicas = null;
    }
    // Constructor para crear una nueva propiedad (usado en SubirPropiedadServlet)
    public Propiedades(int id_tipo, int id_agente, String direccion, double precio, String descripcion, String estado, String modalidad) {
        this.id_tipo = id_tipo;
        this.id_agente = id_agente;
        this.direccion = direccion;
        this.precio = precio;
        this.descripcion = descripcion;
        this.estado = estado;
        this.modalidad = modalidad;
        this.id_propiedad = -1; // Valor temporal, se asignará al insertar
        this.imagen = null;     // Se asignará después al subir imágenes
        this.caracteristicas = null; // Se asignarán después al procesar características
        this.caracteristicasGenerales = caracteristicasGenerales;
    }

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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }
    public String getCaracteristicasGenerales() {
        return caracteristicasGenerales;
    }

    public void setCaracteristicasGenerales(String caracteristicasGenerales) {
        this.caracteristicasGenerales = caracteristicasGenerales;
    }
public String getImagen() { return imagen; } // Getter para la imagen
    public List<String> getCaracteristicas() { return caracteristicas; }
    
    public void setImagen(String imagen) { this.imagen = imagen; }
    public void setCaracteristicas(List<String> caracteristicas) { this.caracteristicas = caracteristicas; }
}
