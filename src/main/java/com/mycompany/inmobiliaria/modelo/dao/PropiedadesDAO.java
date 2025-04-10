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

<<<<<<< HEAD
    /*
     * Método dinámico para listar las propiedades 
     */
    public ArrayList<Propiedades> listar(Integer id_tipo, String modalidad) {
=======
    private static final Logger logger = Logger.getLogger(PropiedadesDAO.class.getName());

   public int insertar(Propiedades propiedad) {
    int idGenerado = -1;
    try (Connection cn = Conexion.getConnection();
         PreparedStatement ps = cn.prepareStatement(
             "INSERT INTO Propiedades (id_tipo, id_agente, direccion, precio, descripcion, estado, modalidad, imagen, caracteristicasGenerales) " +
             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
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
>>>>>>> ruben
        ArrayList<Propiedades> lista = new ArrayList<>();
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

<<<<<<< HEAD
        try {
            cn = Conexion.getConnection();
            StringBuilder sql = new StringBuilder("SELECT * FROM propiedades WHERE 1=1");
            
            if(id_tipo != null){
                sql.append(" AND id_tipo = ?");
            }
            if(modalidad != null){
                sql.append(" AND modalidad = ?");
            }
            
            ps = cn.prepareStatement(sql.toString());
            
            int indiceParametro = 1;
            if(id_tipo != null){
                ps.setInt(indiceParametro++, id_tipo);
            }
            if(modalidad != null){
                ps.setString(indiceParametro++, modalidad);
            }
            
            rs = ps.executeQuery();
            
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
                obj.setDescripcion_general(rs.getString("descripcion_general"));
                
                lista.add(obj);
=======
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
>>>>>>> ruben
            }
            logger.info("Propiedades listadas: " + lista.size());
        } catch (SQLException ex) {
<<<<<<< HEAD
            System.err.println("Error al listar propiedades: " + ex.getMessage());
        } finally {
            cerrarRecursos(cn, ps, rs);
=======
            logger.log(Level.SEVERE, "Error al listar propiedades: " + ex.getMessage(), ex);
            throw new RuntimeException("Error al listar propiedades", ex);
>>>>>>> ruben
        }
        return lista;
    }

<<<<<<< HEAD
    /*
     * Método para buscar propiedad por id
     */
    public Propiedades obtenerPorId(int id_propiedad) {
        Propiedades propiedad = null;
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            cn = Conexion.getConnection();
            String sql = "SELECT * FROM propiedades WHERE id_propiedad = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id_propiedad);
            rs = ps.executeQuery();

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
                propiedad.setDescripcion_general(rs.getString("descripcion_general"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener propiedad: " + e.getMessage());
        } finally {
            cerrarRecursos(cn, ps, rs);
        }
        return propiedad;
    }

    /*
     * Método para agregar propiedad
     */
    public int insertar(Propiedades obj) {
        int result = 0;
        Connection cn = null;
        PreparedStatement ps = null;

        try {
            cn = Conexion.getConnection();
            String sql = "INSERT INTO propiedades(id_tipo,id_agente,direccion,precio,descripcion,estado,modalidad,descripcion_general) VALUES (?,?,?,?,?,?,?,?)";
            ps = cn.prepareStatement(sql);
=======
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
>>>>>>> ruben
            ps.setInt(1, obj.getId_tipo());
            ps.setInt(2, obj.getId_agente());
            ps.setString(3, obj.getDireccion());
            ps.setDouble(4, obj.getPrecio());
            ps.setString(5, obj.getDescripcion());
            ps.setString(6, obj.getEstado());
            ps.setString(7, obj.getModalidad());
<<<<<<< HEAD
            ps.setString(8, obj.getDescripcion_general());

            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar propiedad: " + e.getMessage());
        } finally {
            cerrarRecursos(cn, ps, null);
=======
            ps.setString(8, obj.getImagen()); // Incluir el campo imagen
            ps.setString(9,obj.getCaracteristicasGenerales());
            ps.setInt(9, obj.getId_propiedad());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar propiedad: " + e.getMessage(), e);
>>>>>>> ruben
        }
        return result;
    }

<<<<<<< HEAD
    /*
     * Método para modificar propiedad
     */
    public int actualizar(Propiedades obj) {
        int result = 0;
        Connection cn = null;
        PreparedStatement ps = null;

        try {
            cn = Conexion.getConnection();
            String sql = "UPDATE propiedades SET id_tipo=?, id_agente=?, direccion=?, precio=?, descripcion=?, estado=?, modalidad=?, descripcion_general=? WHERE id_propiedad=?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, obj.getId_tipo());
            ps.setInt(2, obj.getId_agente());
            ps.setString(3, obj.getDireccion());
            ps.setDouble(4, obj.getPrecio());
            ps.setString(5, obj.getDescripcion());
            ps.setString(6, obj.getEstado());
            ps.setString(7, obj.getModalidad());
            ps.setString(8, obj.getDescripcion_general());
            ps.setInt(9, obj.getId_propiedad());
            
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar propiedad: " + e.getMessage());
        } finally {
            cerrarRecursos(cn, ps, null);
=======
    public boolean existeTipoPropiedad(int idTipo) throws SQLException {
        String sql = "SELECT COUNT(*) FROM TiposPropiedad WHERE id_tipo = ?";
        try (Connection conn = Conexion.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idTipo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
>>>>>>> ruben
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
<<<<<<< HEAD

    /*
     * Método auxiliar para cerrar recursos
     */
    private void cerrarRecursos(Connection cn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (cn != null) cn.close();
        } catch (SQLException e) {
            System.err.println("Error al cerrar recursos: " + e.getMessage());
        }
    }
}
=======
}
>>>>>>> ruben
