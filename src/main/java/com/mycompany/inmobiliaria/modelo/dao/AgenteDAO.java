package com.mycompany.inmobiliaria.modelo.dao;

import com.mycompany.inmobiliaria.modelo.Agente;
import com.mycompany.inmobiliaria.resources.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AgenteDAO {

    public List<Agente> listar() throws SQLException {
        List<Agente> lista = new ArrayList<>();
        String sql = "SELECT * FROM agentes";
        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Agente agente = new Agente();
                agente.setId_agente(rs.getInt("id_agente"));
                agente.setNombre(rs.getString("nombre"));
                lista.add(agente);
            }
        } catch (SQLException ex) {
            throw new SQLException("Error al listar agentes: " + ex.getMessage(), ex);
        }
        return lista;
    }
}
