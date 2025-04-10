
package com.mycompany.inmobiliaria.controlador;

import com.mycompany.inmobiliaria.modelo.FotosPropiedad;
import com.mycompany.inmobiliaria.modelo.Propiedades;
import com.mycompany.inmobiliaria.modelo.dao.FotosPropiedadDAO;
import com.mycompany.inmobiliaria.modelo.dao.PropiedadesDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@WebServlet("/detallePropiedad")
public class DetallePropiedadServlet extends HttpServlet {
    private PropiedadesDAO propiedadesDAO = new PropiedadesDAO();
    private FotosPropiedadDAO fotosDAO = new FotosPropiedadDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Obtener el ID de la propiedad desde los parámetros
            String idPropiedadParam = request.getParameter("id");
            if (idPropiedadParam == null || idPropiedadParam.isEmpty()) {
                throw new ServletException("El ID de la propiedad es requerido");
            }

            int idPropiedad = Integer.parseInt(idPropiedadParam);
            // Obtener la propiedad específica usando obtenerPorId
            Propiedades propiedad = propiedadesDAO.obtenerPorId(idPropiedad);

            if (propiedad == null) {
                throw new ServletException("Propiedad no encontrada con ID: " + idPropiedad);
            }

            // Obtener todas las fotos de la propiedad
            ArrayList<FotosPropiedad> fotos = fotosDAO.obtenerFotosPorPropiedad(idPropiedad);

            request.setAttribute("propiedad", propiedad);
            request.setAttribute("fotos", fotos);
            request.getRequestDispatcher("/detalle.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            throw new ServletException("El ID de la propiedad debe ser un número válido", e);
        } catch (Exception e) {
            throw new ServletException("Error al cargar detalle de propiedad", e);
        }}}
    