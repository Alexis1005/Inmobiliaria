
package com.mycompany.inmobiliaria.modelo.dao;


import com.mycompany.inmobiliaria.modelo.PropiedadesCaracteristicas;
import com.mycompany.inmobiliaria.resources.config.Conexion;
import static com.mysql.cj.conf.PropertyKey.logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class PropiedadesCaracteristicasDAO {

    private Connection cn;

    public PropiedadesCaracteristicasDAO(Connection cn) {
        this.cn = cn;
    }

    public PropiedadesCaracteristicasDAO() {
    // Constructor vacío, ya que la conexión se maneja en el método insertar
}
    public boolean agregar(PropiedadesCaracteristicas pc) throws SQLException {
        String sql = "INSERT INTO PropiedadesCaracteristicas (id_propiedad, id_caracteristica, detalle) VALUES (?, ?, ?)";
        try (Connection cn = Conexion.getConnection(); // Asume que tienes una clase Conexion
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, pc.getId_propiedad());
            ps.setInt(2, pc.getId_caracteristica());
            ps.setString(3, pc.getDetalle());
            return ps.executeUpdate() > 0;
        }
    }

    // Método para crear una nueva relación propiedad-característica
    public void insertar(PropiedadesCaracteristicas pc) throws SQLException {
        String sql = "INSERT INTO propiedadescaracteristicas (id_propiedad, id_caracteristica) VALUES (?, ?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, pc.getId_propiedad());
            pstmt.setInt(2, pc.getId_caracteristica());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al insertar propiedad-característica: " + e.getMessage(), e);
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
                    rs.getInt("id_caracteristica")
                   );
                lista.add(pc);
            }
        }
        return lista;
    }

    

    // Método para eliminar una relación propiedad-característica
    public void eliminar(int id_propiedad, int id_caracteristica) throws SQLException {
        String sql = "DELETE FROM propiedadescaracteristicas WHERE id_propiedad = ? AND id_caracteristica = ?";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id_propiedad);
            ps.setInt(2, id_caracteristica);
            ps.executeUpdate();
        }
    }

    // Método para buscar una relación propiedad-característica por IDs
    public PropiedadesCaracteristicas buscarPorIds(int id_propiedad, int id_caracteristica) throws SQLException {
        String sql = "SELECT * FROM propiedadescaracteristicas WHERE id_propiedad = ? AND id_caracteristica = ?";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id_propiedad);
            ps.setInt(2, id_caracteristica);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new PropiedadesCaracteristicas(
                        rs.getInt("id_propiedad"),
                        rs.getInt("id_caracteristica"));
                        
                 
                }
            }
        }
        return null;
    }}