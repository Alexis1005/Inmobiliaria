package com.mycompany.inmobiliaria.modelo.dao;

import com.mycompany.inmobiliaria.modelo.Caracteristicas;
import com.mycompany.inmobiliaria.resources.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CaracteristicasDAO {

    private Connection cn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    // sdfsdsdf
    public ArrayList<Caracteristicas> listar() {
        ArrayList<Caracteristicas> lista = new ArrayList<>();

        try {
            cn = Conexion.getConnection();
            String sql = "Select * from Caracteristicas";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Caracteristicas obj = new Caracteristicas("Departamento a estrenar");
                obj.setId_caracteristica(rs.getInt("id_caracteristica"));
                obj.setNombre(rs.getString("nombre"));
                lista.add(obj);
            }

        } catch (SQLException ex) {
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
            }
        }
        return lista;

    }

    public boolean insertar(Caracteristicas caracteristicas) {
        boolean resultado = false;

        try {
            cn = Conexion.getConnection();
            String sql = "INSERT INTO Caracteristicas (nombre) VALUES (?)";
            ps = cn.prepareStatement(sql);
            ps.setString(1, caracteristicas.getNombre());
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Error en la inserción: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
            }
        }
        return resultado;
    }

    public boolean actualizar(Caracteristicas caracteristicas) {
        boolean resultado = false;

        try {
            cn = Conexion.getConnection();
            String sql = "UPDATE Caracteristicas SET nombre = ? WHERE id_caracteristica = ?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, caracteristicas.getNombre());
            ps.setInt(2, caracteristicas.getId_caracteristica());
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
            }
        }
        return resultado;
    }

}
