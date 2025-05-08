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
            if (cn == null) {
                System.out.println("Error: Conexión nula en ListarTiposPropiedades");
                return listaTipo;
            }
            System.out.println("Conexión establecida. Consultando tiposPropiedad...");
            String sql = "SELECT * FROM tiposPropiedad";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                TiposPropiedad obj = new TiposPropiedad();
                obj.setId_tipo(rs.getInt("id_tipo"));
                obj.setNombre(rs.getString("nombre"));
                listaTipo.add(obj);
                System.out.println("Tipo encontrado: ID=" + obj.getId_tipo() + ", Nombre=" + obj.getNombre());
            }
            System.out.println("Total tipos encontrados: " + listaTipo.size());
        } catch (SQLException ex) {
            System.out.println("Error al listar tipos de propiedad: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
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
            System.out.println("Error al listar tipos de propiedad: " + ex.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
             System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }

        return result;
    }
}
