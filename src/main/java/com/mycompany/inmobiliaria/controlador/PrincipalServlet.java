package com.mycompany.inmobiliaria.controlador;

import com.mycompany.inmobiliaria.modelo.Propiedades;
import com.mycompany.inmobiliaria.modelo.TiposPropiedad;
import com.mycompany.inmobiliaria.modelo.dao.PropiedadesDAO;
import com.mycompany.inmobiliaria.modelo.dao.TiposPropiedadDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/principal")
public class PrincipalServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(PrincipalServlet.class.getName());
    private PropiedadesDAO propiedadesDAO;
    private final TiposPropiedadDAO tiposPropiedadDAO = new TiposPropiedadDAO();

    @Override
    public void init() throws ServletException {
        propiedadesDAO = new PropiedadesDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<TiposPropiedad> tipos = tiposPropiedadDAO.ListarTiposPropiedades();
            // Obtén parámetros opcionales (puedes omitirlos si no los usas)
            Integer idTipo = request.getParameter("id_tipo") != null ? Integer.parseInt(request.getParameter("id_tipo")) : null;
            String modalidad = request.getParameter("modalidad");

                // Lista las propiedades, mostrando solo las disponibles
                List<Propiedades> listaPropiedades = propiedadesDAO.listar(idTipo, modalidad, "disponible");

            // Coloca la lista en el request
            request.setAttribute("tiposPropiedad", tipos);
            request.setAttribute("propiedades", listaPropiedades);

            // Despacha a principal.jsp
            request.getRequestDispatcher("/WEB-INF/views/principal.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Error en PrincipalServlet: ID de tipo no válido - " + e.getMessage(), e);
            throw new ServletException("El ID de tipo debe ser un número válido", e);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error en PrincipalServlet: " + e.getMessage(), e);
            throw new ServletException("Error al cargar propiedades", e);
        }
    }
}