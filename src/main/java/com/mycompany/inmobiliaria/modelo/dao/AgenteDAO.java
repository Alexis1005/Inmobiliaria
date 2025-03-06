
package com.mycompany.inmobiliaria.modelo.dao;

import com.mycompany.inmobiliaria.modelo.Agente;
import com.mycompany.inmobiliaria.resources.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class AgenteDAO {
    private Connection cn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    public ArrayList <Agente> ListarTodos(){
        ArrayList <Agente> lista = new ArrayList<>();
        
        
        try {
            cn = Conexion.getConnection();
        String sql = "select * from agentes";
        ps = cn.prepareStatement(sql);
        rs = ps.executeQuery();
        
            while (rs.next()) {
                Agente obj = new Agente();
                obj.setId_agente(rs.getInt("id_agente"));
                obj.setNombre(rs.getString("nombre"));
                obj.setTelefono(rs.getString("telefono"));
                obj.setEmail(rs.getString("email"));
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
}