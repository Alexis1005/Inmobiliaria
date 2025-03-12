package com.mycompany.inmobiliaria.modelo;

/**
 * Representa a un agente con un identificador único, nombre, teléfono y email.
 */
public class Agente {
    private int id_agente;
    private String nombre;
    private String telefono;
    private String email;

    /**
     * Constructor para inicializar un agente con todos sus atributos.
     *
     * @param id_agente El identificador único del agente.
     * @param nombre    El nombre del agente.
     * @param telefono  El teléfono del agente.
     * @param email     El email del agente.
     */
    public Agente(int id_agente, String nombre, String telefono, String email) {
        this.id_agente = id_agente;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    /**
     * Constructor por defecto.
     * Inicializa los atributos con valores por defecto.
     */
    public Agente() {
        this.id_agente = 0;          // Valor por defecto para id_agente
        this.nombre = "";            // Valor por defecto para nombre
        this.telefono = "";          // Valor por defecto para teléfono
        this.email = "";             // Valor por defecto para email
    }

    public int getId_agente() {
        return id_agente;
    }

    public void setId_agente(int id_agente) {
        this.id_agente = id_agente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("El email no es válido");
        }
        this.email = email;
    }

    @Override
    public String toString() {
        return "Agente{" +
                "id_agente=" + id_agente +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}