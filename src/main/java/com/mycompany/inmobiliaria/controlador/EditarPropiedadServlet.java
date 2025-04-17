package com.mycompany.inmobiliaria.controlador;

import com.mycompany.inmobiliaria.modelo.Propiedades;
import com.mycompany.inmobiliaria.modelo.dao.PropiedadesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/editarPropiedad")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB

public class EditarPropiedadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PropiedadesDAO propiedadesDAO = new PropiedadesDAO();
        try {
            List<Propiedades> propiedades = propiedadesDAO.obtenerTodasLasPropiedades();
            request.setAttribute("listaPropiedades", propiedades);
            request.getRequestDispatcher("/editarPropiedad.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener las propiedades.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Obtené los parámetros del formulario
            int id = Integer.parseInt(request.getParameter("id"));
            String direccion = request.getParameter("direccion");
            String descripcion = request.getParameter("descripcion");
            double precio = Double.parseDouble(request.getParameter("precio"));
            
            PropiedadesDAO propiedadesDAO = new PropiedadesDAO();
            
            // Actualizar la propiedad en la base de datos
            propiedadesDAO.actualizarPropiedad(id, direccion, descripcion, precio);
            // Redirigir a la página de edición nuevamente (o a otra)
            response.sendRedirect("editarPropiedad");
        } catch (SQLException ex) {
            Logger.getLogger(EditarPropiedadServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}