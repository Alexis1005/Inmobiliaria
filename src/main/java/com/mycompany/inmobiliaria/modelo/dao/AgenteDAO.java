package com.mycompany.inmobiliaria.modelo.dao;

import com.mycompany.inmobiliaria.modelo.Agente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AgenteDAO {
    private Connection cn;

    // Constructor que acepta una conexión
    public AgenteDAO(Connection cn) {
        this.cn = cn;
    }

    // Método para listar agentes
    public ArrayList<Agente> listar() throws SQLException {
        ArrayList<Agente> lista = new ArrayList<>();
        String sql = "SELECT * FROM agentes"; // Asegúrate de que la tabla se llame "agentes"
        try (PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Agente agente = new Agente();
                agente.setId_agente(rs.getInt("id_agente"));
                agente.setNombre(rs.getString("nombre"));
                agente.setTelefono(rs.getString("telefono"));
                agente.setEmail(rs.getString("email"));
                lista.add(agente);
            }
        }
        return lista;
    }
}
