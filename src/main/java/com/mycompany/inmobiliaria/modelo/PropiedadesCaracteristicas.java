package com.mycompany.inmobiliaria.modelo;

public class PropiedadesCaracteristicas {
    private int id_propiedad;
    private int id_caracteristica;
<<<<<<< HEAD


    /**
     * Constructor para inicializar una relación entre propiedad y característica.
     *
     * @param id_propiedad      El identificador único de la propiedad.
     * @param id_caracteristica El identificador único de la característica.
     */
    
    public PropiedadesCaracteristicas(int id_propiedad, int id_caracteristica) {
        this.id_propiedad = id_propiedad;
        this.id_caracteristica = id_caracteristica;
=======
    private String detalle;

    // Constructor vacío
    public PropiedadesCaracteristicas() {
>>>>>>> ruben
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

<<<<<<< HEAD
=======
    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
>>>>>>> ruben

    @Override
    public String toString() {
        return "PropiedadesCaracteristicas{" +
<<<<<<< HEAD
                "id_propiedad=" + id_propiedad +
                ", id_caracteristica=" + id_caracteristica +
                ", + '\'}";
=======
               "id_propiedad=" + id_propiedad +
               ", id_paracteristica=" + id_caracteristica +
               ", detalle='" + detalle + '\'' +
               '}';
>>>>>>> ruben
    }
}