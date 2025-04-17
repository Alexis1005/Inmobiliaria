package com.mycompany.inmobiliaria.controlador;


import com.mycompany.inmobiliaria.modelo.Propiedades;
import com.mycompany.inmobiliaria.resources.config.Conexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/filtrarPropiedades")
public class FiltrarPropiedadesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener parámetros del formulario
        String modalidad = request.getParameter("modalidad");
        String tipoPropiedad = request.getParameter("tipoPropiedad");

        // Imprimir parámetros para depuración
        System.out.println("Modalidad recibida: " + modalidad);
        System.out.println("Tipo de Propiedad recibida: " + tipoPropiedad);

        // Obtener propiedades filtradas
        List<Propiedades> propiedadesFiltradas = obtenerPropiedadesFiltradas(modalidad, tipoPropiedad);

        // Pasar resultados a la JSP
        request.setAttribute("propiedadesFiltradas", propiedadesFiltradas);
        request.getRequestDispatcher("/propiedadesFiltradas.jsp").forward(request, response);
    }

    private List<Propiedades> obtenerPropiedadesFiltradas(String modalidad, String tipoPropiedad) {
        List<Propiedades> propiedades = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
            "SELECT p.id_propiedad, p.direccion, p.precio, p.estado, p.imagen " +
            "FROM Propiedades p " +
            "JOIN TiposPropiedad tp ON p.id_tipo = tp.id_tipo WHERE 1=1"
        );
        List<String> params = new ArrayList<>();

        // Agregar filtros dinámicos
        if (modalidad != null && !modalidad.isEmpty()) {
            sql.append(" AND p.modalidad = ?");
            params.add(modalidad.toLowerCase()); // Normalizar a minúsculas
        }
        if (tipoPropiedad != null && !tipoPropiedad.isEmpty()) {
            sql.append(" AND tp.nombre = ?");
            params.add(tipoPropiedad);
        }

        // Imprimir la consulta para depuración
        System.out.println("Consulta SQL: " + sql.toString());
        System.out.println("Parámetros: " + params);

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            if (conn == null) {
                System.out.println("Error: No se pudo establecer la conexión a la base de datos.");
                return propiedades;
            }

            // Asignar parámetros a la consulta
            for (int i = 0; i < params.size(); i++) {
                stmt.setString(i + 1, params.get(i));
            }

            // Ejecutar la consulta
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Propiedades propiedad = new Propiedades();
                propiedad.setId_propiedad(rs.getInt("id_propiedad"));
                propiedad.setDireccion(rs.getString("direccion"));
                propiedad.setPrecio(rs.getDouble("precio"));
                propiedad.setEstado(rs.getString("estado"));
                propiedad.setImagen(rs.getString("imagen"));
                propiedades.add(propiedad);
            }
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
            e.printStackTrace();
        }
        return propiedades;
    }
}
