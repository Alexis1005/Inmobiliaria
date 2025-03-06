<<<<<<< HEAD

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
        
    
=======
package com.mycompany.inmobiliaria.modelo.dao;

import com.mycompany.inmobiliaria.modelo.Propiedades;
import com.mycompany.inmobiliaria.resources.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PropiedadesDAO {

    private Connection cn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    /*
            --------------------------------------------
            Método dinámico para listar las propiedades 
            --------------------------------------------
     */
    public ArrayList<Propiedades> listar(Integer id_tipo, String modalidad) {
        ArrayList<Propiedades> lista = new ArrayList<>();

        try {
            cn = Conexion.getConnection();
            String sql = "select * from propiedades where 1=1 ";
            
            if(id_tipo != null){
                sql += "AND id_tipo = ? ";
            }
            if(modalidad != null){
                sql += "AND modalidad = ? ";
            }
            
            
            ps = cn.prepareStatement(sql);
            
            //asigna parametros a la consulta
            
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

    /*
            -----------------------------
            Método para agregar propiedad
            -----------------------------
     */
    public int insertar(Propiedades obj) {
        int result = 0;

        try {
            cn = Conexion.getConnection();
            String sql = "INSERT INTO propiedades(id_tipo,id_agente,direccion,precio,descripcion,estado,modalidad) values (?,?,?,?,?,?,?)";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, obj.getId_tipo());
            ps.setInt(2, obj.getId_agente());
            ps.setString(3, obj.getDireccion());
            ps.setDouble(4, obj.getPrecio());
            ps.setString(5, obj.getDescripcion());
            ps.setString(6, obj.getEstado());
            ps.setString(7, obj.getModalidad());

            result = ps.executeUpdate();
        } catch (SQLException e) {
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
        return result;
    }

/*
            -----------------------------
            Método para modificar propiedad
            -----------------------------
     */
    
    public int actualizar (Propiedades obj){
    int result = 0;

        try {
            cn = Conexion.getConnection();
            String sql = "UPDATE propiedades SET id_tipo=?, id_agente=?, direccion=?, precio=?, descripcion=?, estado=?, modalidad=? WHERE id_propiedad=? ";
                    
            ps = cn.prepareStatement(sql);
            ps.setInt(1, obj.getId_tipo());
            ps.setInt(2, obj.getId_agente());
            ps.setString(3, obj.getDireccion());
            ps.setDouble(4, obj.getPrecio());
            ps.setString(5, obj.getDescripcion());
            ps.setString(6, obj.getEstado());
            ps.setString(7, obj.getModalidad());
            ps.setInt(8,obj.getId_propiedad());
            
            result = ps.executeUpdate();
        } catch (SQLException e) {
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
        return result;
    }
        
>>>>>>> alexis
}
