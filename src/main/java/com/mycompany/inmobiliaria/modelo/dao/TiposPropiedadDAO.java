package com.mycompany.inmobiliaria.modelo.dao;

import com.mycompany.inmobiliaria.modelo.TiposPropiedad;
import com.mycompany.inmobiliaria.resources.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TiposPropiedadDAO {

    private Connection cn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public ArrayList<TiposPropiedad> ListarTiposPropiedades() {
        ArrayList<TiposPropiedad> listaTipo = new ArrayList<>();
        try {
            cn = Conexion.getConnection();
            String sql = "select * from tiposPropiedad";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                TiposPropiedad obj = new TiposPropiedad();
                obj.setId_tipo(rs.getInt("id_tipo"));
                obj.setNombre(rs.getString("nombre"));
                listaTipo.add(obj);
            }
        } catch (SQLException ex) {
        } finally {
            try {
                if (null != rs) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
            }
        }
        return listaTipo;
    }

    /*
            -----------------------------
            Método para agregar propiedad
            -----------------------------
     */
    public int registrar(TiposPropiedad obj) {
        int result = 0;

        try {
            cn = Conexion.getConnection();
            String sql = "INSERT INTO tiposPropiedad(nombre) values (?)";
            ps = cn.prepareStatement(sql);
            ps.setString(1, obj.getNombre());

            result = ps.executeUpdate();

        } catch (SQLException ex) {
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
            }
        }

        return result;
    }

    public TiposPropiedad obtenerPorId(int id_tipo) throws SQLException {
        TiposPropiedad tipo = null;
        String sql = "SELECT * FROM TiposPropiedad WHERE id_tipo = ?";
        
        // Usamos try-with-resources para cerrar automáticamente los recursos
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id_tipo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    tipo = new TiposPropiedad();
                    tipo.setId_tipo(rs.getInt("id_tipo"));
                    tipo.setNombre(rs.getString("nombre"));
                    // Agrega otros campos según tu tabla
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener el tipo de propiedad por ID: " + id_tipo, e);
        }
        return tipo;
    }
}
