package com.mycompany.inmobiliaria.modelo.dao;

import com.mycompany.inmobiliaria.modelo.FotosPropiedad;
import com.mycompany.inmobiliaria.resources.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FotosPropiedadDAO {

    private static final Logger logger = Logger.getLogger(FotosPropiedadDAO.class.getName());

    public void insertar(FotosPropiedad foto) throws SQLException {
    String sql = "INSERT INTO FotosPropiedad (id_propiedad, ruta_foto) VALUES (?, ?)";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        // Normalizar ruta
        String ruta = foto.getRuta_foto().replace("imagenes/", "");
        
        stmt.setInt(1, foto.getId_propiedad());
        stmt.setString(2, "imagenes/" + ruta); // Guardar con prefijo
        stmt.executeUpdate();
    }
}

    public ArrayList<FotosPropiedad> obtenerFotosPorPropiedad(int idPropiedad) throws SQLException {
        ArrayList<FotosPropiedad> fotos = new ArrayList<>();
        String sql = "SELECT id_foto, id_propiedad, ruta_foto FROM FotosPropiedad WHERE id_propiedad = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPropiedad);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    FotosPropiedad foto = new FotosPropiedad();
                    foto.setId_foto(rs.getInt("id_foto"));
                    foto.setId_propiedad(rs.getInt("id_propiedad"));
                    foto.setRuta_foto(rs.getString("ruta_foto"));
                    fotos.add(foto);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener fotos: " + e.getMessage(), e);
            throw e;
        }
        return fotos;
    }

    public void eliminarPorPropiedad(int idPropiedad) throws SQLException {
        String sql = "DELETE FROM FotosPropiedad WHERE id_propiedad = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPropiedad);
            int rowsAffected = stmt.executeUpdate();
            logger.info("Eliminadas " + rowsAffected + " fotos para id_propiedad: " + idPropiedad);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al eliminar fotos: " + e.getMessage(), e);
            throw e;
        }
    }

    public void eliminarFotoPorId(int idFoto) throws SQLException {
        String sql = "DELETE FROM FotosPropiedad WHERE id_foto = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idFoto);
            int rowsAffected = stmt.executeUpdate();
            logger.info("Eliminada foto con id_foto: " + idFoto + ", filas afectadas: " + rowsAffected);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al eliminar foto por ID: " + e.getMessage(), e);
            throw e;
        }
    }
}