package com.mycompany.inmobiliaria.modelo.dao;

import com.mycompany.inmobiliaria.modelo.Propiedades;
import com.mycompany.inmobiliaria.modelo.Propiedades.EstadoPropiedad;
import com.mycompany.inmobiliaria.modelo.Propiedades.ModalidadPropiedad;
import com.mycompany.inmobiliaria.resources.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PropiedadesDAO {

    // Método para listar todas las propiedades
    public List<Propiedades> listar() {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Propiedades> listaPropiedades = new ArrayList<>();

        try {
            // Obtener la conexión a la base de datos
            cn = Conexion.getConnection();

            // Definir la consulta SQL
            String sql = "SELECT * FROM Propiedades";
            ps = cn.prepareStatement(sql);

            // Ejecutar la consulta
            rs = ps.executeQuery();

            // Recorrer los resultados y mapearlos a objetos Propiedades
            while (rs.next()) {
                Propiedades propiedad = new Propiedades();
                propiedad.setId_propiedad(rs.getInt("id_propiedad"));
                propiedad.setId_tipo(rs.getInt("id_tipo"));
                propiedad.setId_agente(rs.getInt("id_agente"));
                propiedad.setDireccion(rs.getString("direccion"));
                propiedad.setPrecio(rs.getDouble("precio"));
                propiedad.setDescripcion(rs.getString("descripcion"));

                // Mapear el estado y la modalidad usando los enums
                propiedad.setEstado(EstadoPropiedad.valueOf(rs.getString("estado")));
                propiedad.setModalidad(ModalidadPropiedad.valueOf(rs.getString("modalidad")));

                // Agregar la propiedad a la lista
                listaPropiedades.add(propiedad);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Cerrar los recursos
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return listaPropiedades;
    }
    // Método para filtrar propiedades por modalidad
    public List<Propiedades> filtrarPorModalidad(ModalidadPropiedad modalidad) {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Propiedades> listaPropiedades = new ArrayList<>();

        try {
            // Obtener la conexión a la base de datos
            cn = Conexion.getConnection();

            // Definir la consulta SQL con un filtro por modalidad
            String sql = "SELECT * FROM Propiedades WHERE modalidad = ?";
            ps = cn.prepareStatement(sql);

            // Asignar el valor del parámetro (modalidad)
            ps.setString(1, modalidad.name()); // Convertir el enum a String

            // Ejecutar la consulta
            rs = ps.executeQuery();

            // Recorrer los resultados y mapearlos a objetos Propiedades
            while (rs.next()) {
                Propiedades propiedad = new Propiedades();
                propiedad.setId_propiedad(rs.getInt("id_propiedad"));
                propiedad.setId_tipo(rs.getInt("id_tipo"));
                propiedad.setId_agente(rs.getInt("id_agente"));
                propiedad.setDireccion(rs.getString("direccion"));
                propiedad.setPrecio(rs.getDouble("precio"));
                propiedad.setDescripcion(rs.getString("descripcion"));

                // Mapear el estado y la modalidad usando los enums
                propiedad.setEstado(EstadoPropiedad.valueOf(rs.getString("estado")));
                propiedad.setModalidad(ModalidadPropiedad.valueOf(rs.getString("modalidad")));

                // Agregar la propiedad a la lista
                listaPropiedades.add(propiedad);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Cerrar los recursos
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return listaPropiedades;
    }
}

