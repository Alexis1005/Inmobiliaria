
package com.mycompany.inmobiliaria.modelo.dao;


import com.mycompany.inmobiliaria.modelo.PropiedadesCaracteristicas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PropiedadesCaracteristicasDAO {

    private Connection cn;

    public PropiedadesCaracteristicasDAO(Connection cn) {
        this.cn = cn;
    }

    public PropiedadesCaracteristicasDAO() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Método para crear una nueva relación propiedad-característica
    public void crear(PropiedadesCaracteristicas pc) throws SQLException {
        String sql = "INSERT INTO PropiedadesCaracteristicas (id_propiedad, id_caracteristica, valor) VALUES (?, ?, ?)";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, pc.getId_propiedad());
            ps.setInt(2, pc.getId_caracteristica());
            ps.setString(3, pc.getValor());
            ps.executeUpdate();
        }
    }

    // Método para leer todas las relaciones propiedad-característica
    public List<PropiedadesCaracteristicas> listar() throws SQLException {
        List<PropiedadesCaracteristicas> lista = new ArrayList<>();
        String sql = "SELECT * FROM PropiedadesCaracteristicas";
        try (PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PropiedadesCaracteristicas pc = new PropiedadesCaracteristicas(
                    rs.getInt("id_propiedad"),
                    rs.getInt("id_caracteristica"),
                    rs.getString("valor")
                );
                lista.add(pc);
            }
        }
        return lista;
    }

    // Método para actualizar una relación propiedad-característica
    public void actualizar(PropiedadesCaracteristicas pc) throws SQLException {
        String sql = "UPDATE PropiedadesCaracteristicas SET valor = ? WHERE id_propiedad = ? AND id_caracteristica = ?";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, pc.getValor());
            ps.setInt(2, pc.getId_propiedad());
            ps.setInt(3, pc.getId_caracteristica());
            ps.executeUpdate();
        }
    }

    // Método para eliminar una relación propiedad-característica
    public void eliminar(int id_propiedad, int id_caracteristica) throws SQLException {
        String sql = "DELETE FROM PropiedadesCaracteristicas WHERE id_propiedad = ? AND id_caracteristica = ?";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id_propiedad);
            ps.setInt(2, id_caracteristica);
            ps.executeUpdate();
        }
    }

    // Método para buscar una relación propiedad-característica por IDs
    public PropiedadesCaracteristicas buscarPorIds(int id_propiedad, int id_caracteristica) throws SQLException {
        String sql = "SELECT * FROM PropiedadesCaracteristicas WHERE id_propiedad = ? AND id_caracteristica = ?";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id_propiedad);
            ps.setInt(2, id_caracteristica);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new PropiedadesCaracteristicas(
                        rs.getInt("id_propiedad"),
                        rs.getInt("id_caracteristica"),
                        rs.getString("valor")
                    );
                }
            }
        }
        return null;
    }}