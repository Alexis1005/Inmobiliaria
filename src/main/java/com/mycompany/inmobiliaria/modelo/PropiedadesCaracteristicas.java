
package com.mycompany.inmobiliaria.modelo;

/**
 * Representa una relación muchos a muchos entre propiedades y características.
 * Esta clase actúa como una tabla intermedia en una base de datos.
 */
public class PropiedadesCaracteristicas {
    private int id_propiedad;
    private int id_caracteristica;
    private String valor;

    /**
     * Constructor para inicializar una relación entre propiedad y característica.
     *
     * @param id_propiedad      El identificador único de la propiedad.
     * @param id_caracteristica El identificador único de la característica.
     * @param valor             una descripcion de las caractristicas generales de la propiedad.
     */
    public PropiedadesCaracteristicas(int id_propiedad, int id_caracteristica, String valor) {
        this.id_propiedad = id_propiedad;
        this.id_caracteristica = id_caracteristica;
        this.valor = valor;
    }

    public int getId_propiedad() {
        return id_propiedad;
    }

    public void setId_propiedad(int id_propiedad) {
        
        this.id_propiedad = id_propiedad;
    }

    public int getId_caracteristica() {
        return id_caracteristica;
    }

    public void setId_caracteristica(int id_caracteristica) {
        
        this.id_caracteristica = id_caracteristica;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "PropiedadesCaracteristicas{" +
                "id_propiedad=" + id_propiedad +
                ", id_caracteristica=" + id_caracteristica +
                ", valor='" + valor + '\'' +
                '}';
    }
}
    
    

