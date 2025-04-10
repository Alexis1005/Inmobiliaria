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

    public ArrayList<FotosPropiedad> obtenerFotosPorPropiedad(int id_propiedad) {
        ArrayList<FotosPropiedad> fotos = new ArrayList<>();
        String sql = "SELECT id_foto, id_propiedad, ruta_foto FROM FotosPropiedad WHERE id_propiedad = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id_propiedad);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    FotosPropiedad foto = new FotosPropiedad();
                    foto.setId_foto(rs.getInt("id_foto"));
                    foto.setId_propiedad(rs.getInt("id_propiedad"));
                    foto.setRuta_foto(rs.getString("ruta_foto"));
                    fotos.add(foto);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al obtener fotos por propiedad: " + ex.getMessage(), ex);
            throw new RuntimeException("Error al obtener fotos por propiedad", ex);
        }
        return fotos;
    }

    public void insertar(FotosPropiedad foto) throws SQLException {
        String sql = "INSERT INTO FotosPropiedad (id_propiedad, ruta_foto) VALUES (?, ?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, foto.getId_propiedad());
            pstmt.setString(2, foto.getRuta_foto());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al insertar foto: " + e.getMessage(), e);
            throw e;
        }
    }
}