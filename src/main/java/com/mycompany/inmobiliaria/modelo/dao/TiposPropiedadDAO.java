package com.mycompany.inmobiliaria.modelo.dao;

import com.mycompany.inmobiliaria.modelo.TiposPropiedad;
import com.mycompany.inmobiliaria.resources.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TiposPropiedadDAO {

    public ArrayList<TiposPropiedad> ListarTiposPropiedades() throws SQLException {
        ArrayList<TiposPropiedad> listaTipo = new ArrayList<>();
        String sql = "SELECT * FROM tiposPropiedad order by nombre asc";
        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                TiposPropiedad obj = new TiposPropiedad();
                obj.setId_tipo(rs.getInt("id_tipo"));
                obj.setNombre(rs.getString("nombre"));
                listaTipo.add(obj);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al listar tipos de propiedad: " + e.getMessage(), e);
        }
        return listaTipo;
    }

    public int registrar(TiposPropiedad obj) throws SQLException {
        String sql = "INSERT INTO tiposPropiedad (nombre) VALUES (?)";
        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, obj.getNombre());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al registrar tipo de propiedad: " + e.getMessage(), e);
        }
    }

    public TiposPropiedad obtenerPorId(int id_tipo) throws SQLException {
        TiposPropiedad tipo = null;
        String sql = "SELECT * FROM TiposPropiedad WHERE id_tipo = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id_tipo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    tipo = new TiposPropiedad();
                    tipo.setId_tipo(rs.getInt("id_tipo"));
                    tipo.setNombre(rs.getString("nombre"));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener el tipo de propiedad por ID: " + e.getMessage(), e);
        }
        return tipo;
    }
}       