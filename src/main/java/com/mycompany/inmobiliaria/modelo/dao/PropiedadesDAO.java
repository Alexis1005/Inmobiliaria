package com.mycompany.inmobiliaria.modelo.dao;

import com.mycompany.inmobiliaria.modelo.Propiedades;
import com.mycompany.inmobiliaria.resources.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PropiedadesDAO {

    /*
     * Método dinámico para listar las propiedades 
     */
    public ArrayList<Propiedades> listar(Integer id_tipo, String modalidad) {
        ArrayList<Propiedades> lista = new ArrayList<>();
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

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
            }
        } catch (SQLException ex) {
            System.err.println("Error al listar propiedades: " + ex.getMessage());
        } finally {
            cerrarRecursos(cn, ps, rs);
        }
        return lista;
    }

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
            ps.setInt(1, obj.getId_tipo());
            ps.setInt(2, obj.getId_agente());
            ps.setString(3, obj.getDireccion());
            ps.setDouble(4, obj.getPrecio());
            ps.setString(5, obj.getDescripcion());
            ps.setString(6, obj.getEstado());
            ps.setString(7, obj.getModalidad());
            ps.setString(8, obj.getDescripcion_general());

            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar propiedad: " + e.getMessage());
        } finally {
            cerrarRecursos(cn, ps, null);
        }
        return result;
    }

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
        }
        return result;
    }

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