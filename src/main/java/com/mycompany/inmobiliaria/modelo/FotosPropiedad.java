
package com.mycompany.inmobiliaria.modelo;

public class FotosPropiedad {
    private int id_foto;
    private int id_propiedad;
    private String ruta_foto;

    public FotosPropiedad() {}

    public FotosPropiedad(int id_propiedad, String ruta_foto) {
        this.id_propiedad = id_propiedad;
        this.ruta_foto = ruta_foto;
    }

    public int getId_foto() {
        return id_foto;
    }

    public void setId_foto(int id_foto) {
        this.id_foto = id_foto;
    }

    public int getId_propiedad() {
        return id_propiedad;
    }

    public void setId_propiedad(int id_propiedad) {
        this.id_propiedad = id_propiedad;
    }

    public String getRuta_foto() {
        return ruta_foto;
    }

    public void setRuta_foto(String ruta_foto) {
        this.ruta_foto = ruta_foto;
    }
}
