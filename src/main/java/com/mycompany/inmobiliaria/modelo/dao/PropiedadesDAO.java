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

    /**
     * Lista las propiedades filtradas por tipo y modalidad.
     * @param idTipo ID del tipo de propiedad (opcional, null para no filtrar).
     * @param modalidad Modalidad de la propiedad (opcional, null para no filtrar).
     * @return Lista de propiedades que cumplen con los filtros.
     */
    public ArrayList<Propiedades> listar(Integer idTipo, String modalidad) {
        ArrayList<Propiedades> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT p.id_propiedad, p.id_tipo, p.id_agente, p.direccion, p.precio, "
                + "p.descripcion, p.estado, p.modalidad, p.imagen, p.descripcion_general "
                + "FROM propiedades p WHERE p.estado = 'disponible'");

        List<String> conditions = new ArrayList<>();
        if (idTipo != null) {
            conditions.add("p.id_tipo = ?");
        }
        if (modalidad != null && !modalidad.isEmpty()) {
            conditions.add("p.modalidad = ?");
        }
        if (!conditions.isEmpty()) {
            sql.append(" AND ").append(String.join(" AND ", conditions));
        }

        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            int paramIndex = 1;
            if (idTipo != null) {
                ps.setInt(paramIndex++, idTipo);
            }
            if (modalidad != null && !modalidad.isEmpty()) {
                ps.setString(paramIndex, modalidad);
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
                    obj.setImagen(rs.getString("imagen"));
                    obj.setDescripcion_general(rs.getString("descripcion_general"));

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

    /**
     * Inserta una nueva propiedad en la base de datos.
     * @param propiedad Objeto Propiedades con los datos a insertar.
     * @return El ID generado de la propiedad insertada, o -1 si falla.
     */
    public int insertar(Propiedades propiedad) {
        int idGenerado = -1;
        String sql = "INSERT INTO propiedades (id_tipo, id_agente, direccion, precio, descripcion, estado, modalidad, imagen, descripcion_general) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, propiedad.getId_tipo());
            ps.setInt(2, propiedad.getId_agente());
            ps.setString(3, propiedad.getDireccion());
            ps.setDouble(4, propiedad.getPrecio());
            ps.setString(5, propiedad.getDescripcion());
            ps.setString(6, propiedad.getEstado());
            ps.setString(7, propiedad.getModalidad());
            ps.setString(8, propiedad.getImagen());
            ps.setString(9, propiedad.getDescripcion_general());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    idGenerado = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al insertar propiedad: " + e.getMessage(), e);
        }
        return idGenerado;
    }

    /**
     * Actualiza una propiedad existente en la base de datos.
     * @param propiedad Objeto Propiedades con los datos actualizados.
     * @return Número de filas afectadas (1 si se actualizó, 0 si no).
     */
    public int actualizar(Propiedades propiedad) {
        int result = 0;
        String sql = "UPDATE propiedades SET id_tipo = ?, id_agente = ?, direccion = ?, precio = ?, descripcion = ?, "
                + "estado = ?, modalidad = ?, imagen = ?, descripcion_general = ? WHERE id_propiedad = ?";
        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, propiedad.getId_tipo());
            ps.setInt(2, propiedad.getId_agente());
            ps.setString(3, propiedad.getDireccion());
            ps.setDouble(4, propiedad.getPrecio());
            ps.setString(5, propiedad.getDescripcion());
            ps.setString(6, propiedad.getEstado());
            ps.setString(7, propiedad.getModalidad());
            ps.setString(8, propiedad.getImagen());
            ps.setString(9, propiedad.getDescripcion_general());
            ps.setInt(10, propiedad.getId_propiedad());

            result = ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar propiedad: " + e.getMessage(), e);
        }
        return result;
    }

    /**
     * Obtiene una propiedad por su ID.
     * @param idPropiedad ID de la propiedad a buscar.
     * @return Objeto Propiedades si se encuentra, null si no.
     * @throws SQLException Si hay un error al acceder a la base de datos.
     */
    public Propiedades obtenerPorId(int idPropiedad) throws SQLException {
        Propiedades propiedad = null;
        String sql = "SELECT p.id_propiedad, p.id_tipo, p.id_agente, p.direccion, p.precio, "
                + "p.descripcion, p.estado, p.modalidad, p.imagen, p.descripcion_general "
                + "FROM propiedades p WHERE p.id_propiedad = ?";

        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
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
                    propiedad.setImagen(rs.getString("imagen"));
                    propiedad.setDescripcion_general(rs.getString("descripcion_general"));

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

    /**
     * Obtiene las características asociadas a una propiedad.
     * @param idPropiedad ID de la propiedad.
     * @param cn Conexión a la base de datos.
     * @return Lista de características (nombre y detalles).
     * @throws SQLException Si hay un error al acceder a la base de datos.
     */
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

    /**
     * Verifica si un tipo de propiedad existe en la base de datos.
     * @param idTipo ID del tipo de propiedad a verificar.
     * @return true si existe, false si no.
     * @throws SQLException Si hay un error al acceder a la base de datos.
     */
    public boolean existeTipoPropiedad(int idTipo) throws SQLException {
        String sql = "SELECT COUNT(*) FROM TiposPropiedad WHERE id_tipo = ?";
        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idTipo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    /**
     * Actualiza la imagen de una propiedad.
     * @param idPropiedad ID de la propiedad.
     * @param imagen Nueva imagen a asignar.
     * @throws SQLException Si hay un error al actualizar.
     */
    public void actualizarImagen(int idPropiedad, String imagen) throws SQLException {
        String sql = "UPDATE propiedades SET imagen = ? WHERE id_propiedad = ?";
        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, imagen);
            ps.setInt(2, idPropiedad);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar la imagen de la propiedad: " + e.getMessage(), e);
            throw new SQLException("Error al actualizar la imagen de la propiedad: " + e.getMessage(), e);
        }
    }

    /**
     * Cierra los recursos de base de datos (usado solo en métodos sin try-with-resources).
     * @param cn Conexión a cerrar.
     * @param ps PreparedStatement a cerrar.
     * @param rs ResultSet a cerrar.
     */
    private void cerrarRecursos(Connection cn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (cn != null) cn.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al cerrar recursos: " + e.getMessage(), e);
        }
    }
}