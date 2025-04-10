
package com.mycompany.inmobiliaria.controlador;

import com.mycompany.inmobiliaria.modelo.Propiedades;
import com.mycompany.inmobiliaria.modelo.dao.PropiedadesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/propiedades")
public class PropiedadesServlet extends HttpServlet {
    private PropiedadesDAO propiedadesDAO = new PropiedadesDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Obtener todas las propiedades disponibles desde la base de datos
            ArrayList<Propiedades> propiedades = propiedadesDAO.listar(null, null, "disponible");
            
            // Pasar la lista al JSP como atributo
            request.setAttribute("propiedades", propiedades);
            
            // Redirigir al archivo JSP
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error al cargar propiedades", e);
        }
    }
}