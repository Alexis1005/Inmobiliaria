
package com.mycompany.inmobiliaria.modelo.dao;

import com.mycompany.inmobiliaria.modelo.Propiedades;
import com.mycompany.inmobiliaria.resources.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class PropiedadesDAO {
    private Connection cn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    public ArrayList <Propiedades> ListarPropiedades(){
        ArrayList <Propiedades> lista = new ArrayList<>();
        
        
        try {
            cn = Conexion.getConnection();
        String sql = "select * from Propiedades";
        ps = cn.prepareStatement(sql);
        rs = ps.executeQuery();
        
            while (rs.next()) {    
                Propiedades obj = new Propiedades();
                obj.setId_propiedad(rs.getInt(id_propiedad));
                obj.set
                
            }            
                
            }
        
    
}
