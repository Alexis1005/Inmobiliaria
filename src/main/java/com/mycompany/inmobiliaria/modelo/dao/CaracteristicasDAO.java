package com.mycompany.inmobiliaria.modelo.dao;

import com.mycompany.inmobiliaria.modelo.Caracteristica;

import com.mycompany.inmobiliaria.resources.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CaracteristicasDAO {

    public List<Caracteristica> listar() throws SQLException {
        List<Caracteristica> lista = new ArrayList<>();
        String sql = "SELECT * FROM caracteristicas";
        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Caracteristica obj = new Caracteristica();
                obj.setId_caracteristica(rs.getInt("id_caracteristica"));
                obj.setNombre(rs.getString("nombre"));
                obj.setDetalle(rs.getString("detalles"));
                obj.setTipoPropiedadId(rs.getInt("tipo_propiedad_id"));
                lista.add(obj);
            }
        } catch (SQLException ex) {
            throw new SQLException("Error al listar características: " + ex.getMessage(), ex);
        }
        return lista;
    }

    public List<Caracteristica> listarPorTipo(int tipoId) throws SQLException {
        List<Caracteristica> lista = new ArrayList<>();
        String sql = "SELECT * FROM caracteristicas WHERE id_tipo = ?";
        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, tipoId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Caracteristica obj = new Caracteristica();
                    obj.setId_caracteristica(rs.getInt("id_caracteristica"));
                    obj.setNombre(rs.getString("nombre"));
                    obj.setDetalle(rs.getString("detalles"));
                    obj.setTipoPropiedadId(rs.getInt("id_tipo"));
                    lista.add(obj);
                }
            }
        } catch (SQLException ex) {
            throw new SQLException("Error al listar características por tipo: " + ex.getMessage(), ex);
        }
        return lista;
    }

    public boolean agregar(Caracteristica caracteristica) throws SQLException {
        String sql = "INSERT INTO caracteristicas (nombre, detalles, tipo_propiedad_id) VALUES (?, ?, ?)";
        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, caracteristica.getNombre());
            ps.setString(2, caracteristica.getDetalle() != null ? caracteristica.getDetalle() : "");
            ps.setInt(3, caracteristica.getTipoPropiedadId());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            throw new SQLException("Error al agregar característica: " + ex.getMessage(), ex);
        }
    }

    public boolean actualizar(Caracteristica caracteristica) throws SQLException {
        String sql = "UPDATE caracteristicas SET nombre = ?, detalles = ?, tipo_propiedad_id = ? WHERE id_caracteristica = ?";
        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, caracteristica.getNombre());
            ps.setString(2, caracteristica.getDetalle());
            ps.setInt(3, caracteristica.getTipoPropiedadId());
            ps.setInt(4, caracteristica.getId_caracteristica());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            throw new SQLException("Error al actualizar característica: " + ex.getMessage(), ex);
        }
    }

    public int registrar(Caracteristica obj) throws SQLException {
        String sql = "INSERT INTO caracteristicas (nombre, detalles, tipo_propiedad_id) VALUES (?, ?, ?)";
        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDetalle());
            ps.setInt(3, obj.getTipoPropiedadId());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException("Error al registrar característica: " + ex.getMessage(), ex);
        }
    }

    public int editar(Caracteristica obj) throws SQLException {
        String sql = "UPDATE caracteristicas SET nombre = ?, detalles = ?, tipo_propiedad_id = ? WHERE id_caracteristica = ?";
        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDetalle());
            ps.setInt(3, obj.getTipoPropiedadId());
            ps.setInt(4, obj.getId_caracteristica());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException("Error al editar característica: " + ex.getMessage(), ex);
        }
    }

    public int eliminar(int idCaracteristica) throws SQLException {
        String sql = "DELETE FROM caracteristicas WHERE id_caracteristica = ?";
        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idCaracteristica);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException("Error al eliminar característica: " + ex.getMessage(), ex);
        }
    }

    public Caracteristica buscarPorId(int id) throws SQLException {
        Caracteristica obj = null;
        String sql = "SELECT * FROM caracteristicas WHERE id_caracteristica = ?";
        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    obj = new Caracteristica();
                    obj.setId_caracteristica(rs.getInt("id_caracteristica"));
                    obj.setNombre(rs.getString("nombre"));
                    obj.setDetalle(rs.getString("detalles"));
                    obj.setTipoPropiedadId(rs.getInt("tipo_propiedad_id"));
                }
            }
        } catch (SQLException ex) {
            throw new SQLException("Error al buscar característica por ID: " + ex.getMessage(), ex);
        }
        return obj;
    }

    public List<String> listarNombres() throws SQLException {
        List<String> nombres = new ArrayList<>();
        String sql = "SELECT DISTINCT nombre FROM caracteristicas";
        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                nombres.add(rs.getString("nombre"));
            }
        } catch (SQLException ex) {
            throw new SQLException("Error al listar nombres de características: " + ex.getMessage(), ex);
        }
        return nombres;
    }

    public List<String> listarDetalles() throws SQLException {
        List<String> detalles = new ArrayList<>();
        String sql = "SELECT DISTINCT detalles FROM caracteristicas";
        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                detalles.add(rs.getString("detalles"));
            }
        } catch (SQLException ex) {
            throw new SQLException("Error al listar detalles de características: " + ex.getMessage(), ex);
        }
        return detalles;
    }
}
