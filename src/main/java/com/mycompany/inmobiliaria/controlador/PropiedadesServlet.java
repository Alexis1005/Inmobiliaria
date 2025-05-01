package com.mycompany.inmobiliaria.controlador;

import com.mycompany.inmobiliaria.modelo.Propiedades;
import com.mycompany.inmobiliaria.modelo.PropiedadesCaracteristicas;
import com.mycompany.inmobiliaria.modelo.dao.PropiedadesCaracteristicasDAO;
import com.mycompany.inmobiliaria.modelo.dao.PropiedadesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/propiedades")
public class PropiedadesServlet extends HttpServlet {

    private final PropiedadesDAO propiedadesDAO = new PropiedadesDAO();
    private final PropiedadesCaracteristicasDAO pcDAO = new PropiedadesCaracteristicasDAO(); // ← instancia el DAO de detalles

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // 1) Obtener todas las propiedades disponibles desde la base de datos
            ArrayList<Propiedades> propiedades = propiedadesDAO.listar(null, null, "disponible");

            // 2) Construir el Map<id_propiedad, detalles>
            Map<Integer, List<PropiedadesCaracteristicas>> detallesMap = new HashMap<>();
            for (Propiedades p : propiedades) {
                // listarPorPropiedad(int id) debe devolver List<PropiedadesCaracteristicas>
                List<PropiedadesCaracteristicas> detalles
                        = pcDAO.listarPorPropiedad(p.getId_propiedad());
                detallesMap.put(p.getId_propiedad(), detalles);
                System.out.println("detallesMap.size() = " + detallesMap.size());
            }

            // 3) Pasar al JSP
            request.setAttribute("propiedades", propiedades);
            request.setAttribute("detallesMap", detallesMap);

            // 4) Redirigir al archivo JSP
            request.getRequestDispatcher("/principal.jsp").forward(request, response);
        } catch (ServletException | IOException | SQLException e) {
            throw new ServletException("Error al cargar propiedades", e);
        }
    }
}
