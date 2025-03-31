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

    public ArrayList<Caracteristicas> listar() {
        ArrayList<Caracteristicas> lista = new ArrayList<>();

        try {
            cn = Conexion.getConnection();
            String sql = "Select * from caracteristicas";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Caracteristicas obj = new Caracteristicas();
                obj.setId_caracteristica(rs.getInt("id_caracteristica"));
                obj.setNombre(rs.getString("nombre"));
                obj.setDetalle(rs.getString("detalle"));
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

    public int insertar(Caracteristicas caracteristica) {
        int idGenerado = -1;  // Valor por defecto si no se genera ID
        try {
            cn = Conexion.getConnection();
            String sql = "INSERT INTO Caracteristicas (nombre, detalle) VALUES (?, ?)";
            ps = cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, caracteristica.getNombre());
            ps.setString(2, caracteristica.getDetalle());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    idGenerado = rs.getInt(1);  // Obtiene el ID generado
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error al insertar característica: " + ex.getMessage());
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
                System.err.println("Error al cerrar recursos: " + ex.getMessage());
            }
        }
        return idGenerado;
    }

    public boolean actualizar(Caracteristicas caracteristicas) {
        boolean resultado = false;

        try {
            cn = Conexion.getConnection();
            String sql = "UPDATE caracteristicas SET nombre = ?, detalle = ? WHERE id_caracteristica = ?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, caracteristicas.getNombre());
            ps.setString(2, caracteristicas.getDetalle());
            ps.setInt(3, caracteristicas.getId_caracteristica());
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

    public int registrar(Caracteristicas obj) {
        int result = 0;

        try {
            cn = Conexion.getConnection();
            String sql = "INSERT INTO caracteristicas (nombre, detalle) VALUES (?, ?)";
            ps = cn.prepareStatement(sql);
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDetalle());

            result = ps.executeUpdate();

        } catch (Exception ex) {
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

        return result;
    }

    public int editar(Caracteristicas obj) {
        int result = 0;

        try {
            cn = Conexion.getConnection();
            String sql = "UPDATE caracteristicas SET nombre = ?, detalle = ? WHERE id_caracteristica = ?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDetalle());
            ps.setInt(3, obj.getId_caracteristica());

            result = ps.executeUpdate();
        } catch (Exception ex) {
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

        return result;
    }

    public int eliminar(int id_caracteristica) {
        int result = 0;

        try {
            cn = Conexion.getConnection();
            String sql = "delete from caracteristicas where id_caracteristica = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id_caracteristica);

            result = ps.executeUpdate();
        } catch (Exception ex) {
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

        return result;
    }

    public Caracteristicas buscarPorId(int id) {
        Caracteristicas obj = null;

        try {
            cn = Conexion.getConnection();
            String sql = "Select * from caracteristicas where id_caracteristica = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                obj = new Caracteristicas();
                obj.setId_caracteristica(rs.getInt("id_caracteristica"));
                obj.setNombre(rs.getString("nombre"));
                obj.setDetalle(rs.getString("detalle"));
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
        return obj;

    }

}
