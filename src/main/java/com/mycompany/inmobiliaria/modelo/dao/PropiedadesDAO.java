package com.mycompany.inmobiliaria.modelo.dao;

import com.mycompany.inmobiliaria.modelo.Propiedades;
import com.mycompany.inmobiliaria.resources.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropiedadesDAO {

    private static final Logger logger = Logger.getLogger(PropiedadesDAO.class.getName());

    public int insertar(Propiedades propiedad) {
        int idGenerado = -1;
        try (Connection cn = Conexion.getConnection(); PreparedStatement ps = cn.prepareStatement(
                "INSERT INTO Propiedades (id_tipo, id_agente, direccion, precio, descripcion, estado, modalidad, imagen, caracteristicasGenerales) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, propiedad.getId_tipo());
            ps.setInt(2, propiedad.getId_agente());
            ps.setString(3, propiedad.getDireccion());
            ps.setDouble(4, propiedad.getPrecio());
            ps.setString(5, propiedad.getDescripcion());
            ps.setString(6, propiedad.getEstado());
            ps.setString(7, propiedad.getModalidad());
            ps.setString(8, propiedad.getImagen()); // Incluir el campo imagen
            ps.setString(9, propiedad.getCaracteristicasGenerales());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idGenerado = rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al insertar propiedad: " + e.getMessage(), e);
        }
        return idGenerado;
    }

    public ArrayList<Propiedades> listar(Integer idTipo, String modalidad, String modality) {
        ArrayList<Propiedades> lista = new ArrayList<>();

        String sql = "SELECT p.id_propiedad, p.id_tipo, p.id_agente, p.direccion, p.precio, "
                + "p.descripcion, p.estado, p.modalidad, p.imagen "
                + "FROM Propiedades p "
                + "WHERE p.estado = 'disponible'";

        List<String> conditions = new ArrayList<>();
        if (idTipo != null) {
            conditions.add("p.id_tipo = ?");
        }
        if (modalidad != null && !modalidad.isEmpty()) {
            conditions.add("p.modalidad = ?");
        }
        if (!conditions.isEmpty()) {
            sql += " AND " + String.join(" AND ", conditions);
        }

        try (Connection cn = Conexion.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {

            int paramIndex = 1;
            if (idTipo != null) {
                ps.setInt(paramIndex++, idTipo);
            }
            if (modalidad != null && !modalidad.isEmpty()) {
                ps.setString(paramIndex, modality);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Propiedades obj = new Propiedades();
                    obj.setId_propiedad(rs.getInt("id_propiedad"));
                    obj.setId_tipo(rs.getInt("id_tipo"));
                    obj.setId_agente(rs.getInt("id_agente"));
                    obj.setDireccion(rs.getString("direccion"));
                    obj.setPrecio(rs.getDouble("precio"));
                    obj.setDescripcion(rs.getString("descripcion"));
                    obj.setEstado(rs.getString("estado"));
                    obj.setModalidad(rs.getString("modalidad"));
                    obj.setImagen(rs.getString("imagen")); // Usar el campo imagen de Propiedades

                    // Cargar características
                    List<String> caracteristicas = obtenerCaracteristicas(rs.getInt("id_propiedad"), cn);
                    obj.setCaracteristicas(caracteristicas);

                    lista.add(obj);
                }
            }
            logger.info("Propiedades listadas: " + lista.size());
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al listar propiedades: " + ex.getMessage(), ex);
            throw new RuntimeException("Error al listar propiedades", ex);
        }
        return lista;
    }

    private List<String> obtenerCaracteristicas(int idPropiedad, Connection cn) throws SQLException {
        List<String> caracteristicas = new ArrayList<>();
        String sql = "SELECT c.nombre, c.detalles "
                + "FROM PropiedadesCaracteristicas pc "
                + "JOIN Caracteristicas c ON pc.id_caracteristica = c.id_caracteristica "
                + "WHERE pc.id_propiedad = ?";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idPropiedad);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String detalles = rs.getString("detalles");
                    caracteristicas.add(nombre + (detalles != null ? ": " + detalles : ""));
                }
            }
        }
        return caracteristicas;
    }

    public int actualizar(Propiedades obj) {
        int result = 0;
        try (Connection cn = Conexion.getConnection(); PreparedStatement ps = cn.prepareStatement(
                "UPDATE Propiedades SET id_tipo=?, id_agente=?, direccion=?, precio=?, descripcion=?, estado=?, modalidad=?, imagen=?, caracteristicasGenerales=? WHERE id_propiedad=?")) {
            ps.setInt(1, obj.getId_tipo());
            ps.setInt(2, obj.getId_agente());
            ps.setString(3, obj.getDireccion());
            ps.setDouble(4, obj.getPrecio());
            ps.setString(5, obj.getDescripcion());
            ps.setString(6, obj.getEstado());
            ps.setString(7, obj.getModalidad());
            ps.setString(8, obj.getImagen()); // Incluir el campo imagen
            ps.setString(9, obj.getCaracteristicasGenerales());
            ps.setInt(9, obj.getId_propiedad());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar propiedad: " + e.getMessage(), e);
        }
        return result;
    }

    public boolean existeTipoPropiedad(int idTipo) throws SQLException {
        String sql = "SELECT COUNT(*) FROM TiposPropiedad WHERE id_tipo = ?";
        try (Connection conn = Conexion.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idTipo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public Propiedades obtenerPorId(int idPropiedad) {
        Propiedades propiedad = null;
        String sql = "SELECT p.id_propiedad, p.id_tipo, p.id_agente, p.direccion, p.precio, "
                + "p.descripcion, p.estado, p.modalidad, p.imagen "
                + "FROM Propiedades p "
                + "WHERE p.id_propiedad = ?";

        try (Connection cn = Conexion.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idPropiedad);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    propiedad = new Propiedades();
                    propiedad.setId_propiedad(rs.getInt("id_propiedad"));
                    propiedad.setId_tipo(rs.getInt("id_tipo"));
                    propiedad.setId_agente(rs.getInt("id_agente"));
                    propiedad.setDireccion(rs.getString("direccion"));
                    propiedad.setPrecio(rs.getDouble("precio"));
                    propiedad.setDescripcion(rs.getString("descripcion"));
                    propiedad.setEstado(rs.getString("estado"));
                    propiedad.setModalidad(rs.getString("modalidad"));
                    propiedad.setImagen(rs.getString("imagen")); // Usar el campo imagen de Propiedades

                    List<String> caracteristicas = obtenerCaracteristicas(idPropiedad, cn);
                    propiedad.setCaracteristicas(caracteristicas);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al obtener propiedad por ID: " + ex.getMessage(), ex);
            throw new RuntimeException("Error al obtener propiedad por ID", ex);
        }
        return propiedad;
    }

    public void actualizarImagen(int idPropiedad, String imagen) throws SQLException {
        String sql = "UPDATE Propiedades SET imagen = ? WHERE id_propiedad = ?";
        try (Connection conn = Conexion.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, imagen);
            pstmt.setInt(2, idPropiedad);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar la imagen de la propiedad: " + e.getMessage(), e);
        }
    }

    // Método para obtener una propiedad por ID (necesario para la edición)
    public Propiedades obtenerPropiedadPorId(int id_propiedad) throws SQLException {
        Propiedades propiedad = null;
        String sql = "SELECT * FROM propiedades WHERE id_propiedad = ?";
        try (Connection cn = Conexion.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id_propiedad);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    propiedad = new Propiedades();
                    propiedad.setId_propiedad(rs.getInt("id_propiedad"));
                    propiedad.setId_tipo(rs.getInt("id_tipo"));
                    propiedad.setId_agente(rs.getInt("id_agente"));
                    propiedad.setDireccion(rs.getString("direccion"));
                    propiedad.setPrecio(rs.getDouble("precio"));
                    propiedad.setDescripcion(rs.getString("descripcion"));
                    propiedad.setEstado(rs.getString("estado"));
                    propiedad.setModalidad(rs.getString("modalidad"));
                    propiedad.setImagen(rs.getString("imagen")); // Usar el campo imagen de Propiedades
                    propiedad.setCaracteristicasGenerales(rs.getString("propiedadesCaracteristicas"));

                }
            }
        }
        return propiedad;
    }

    public List<Propiedades> obtenerTodasLasPropiedades() throws SQLException {
        List<Propiedades> lista = new ArrayList<>();

        String sql = "SELECT * FROM propiedades";

        try (Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Propiedades p = new Propiedades();
                p.setId_propiedad(rs.getInt("id_propiedad"));
                p.setId_tipo(rs.getInt("id_tipo"));
                p.setId_agente(rs.getInt("id_agente"));
                p.setDireccion(rs.getString("direccion"));
                p.setPrecio(rs.getDouble("precio"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setEstado(rs.getString("estado"));
                p.setModalidad(rs.getString("modalidad"));
                p.setImagen(rs.getString("imagen"));
                p.setCaracteristicasGenerales(rs.getString("caracteristicasGenerales")); //posible cambio

                lista.add(p);
            }
        }

        return lista;
    }

    public void actualizarPropiedad(int id, String direccion, String descripcion, double precio) throws SQLException {
        String sql = "UPDATE propiedades SET direccion = ?, descripcion = ?, precio = ? WHERE id_propiedad = ?";

        try (Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, direccion);
            stmt.setString(2, descripcion);
            stmt.setDouble(3, precio);
            stmt.setInt(4, id);

            stmt.executeUpdate();
        }
    }

}
