package com.mycompany.inmobiliaria.controlador;

import com.mycompany.inmobiliaria.modelo.FotosPropiedad;
import com.mycompany.inmobiliaria.modelo.Propiedades;
import com.mycompany.inmobiliaria.modelo.PropiedadesCaracteristicas;
import com.mycompany.inmobiliaria.modelo.TiposPropiedad;
import com.mycompany.inmobiliaria.modelo.dao.FotosPropiedadDAO;
import com.mycompany.inmobiliaria.modelo.dao.PropiedadesDAO;
import com.mycompany.inmobiliaria.modelo.dao.PropiedadesCaracteristicasDAO;
import com.mycompany.inmobiliaria.modelo.dao.TiposPropiedadDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet("/detallePropiedad")
public class DetallePropiedadServlet extends HttpServlet {

    private PropiedadesDAO propiedadesDAO;
    private FotosPropiedadDAO fotosDAO;
    private TiposPropiedadDAO tiposDAO;
    private PropiedadesCaracteristicasDAO pcDAO;
    private static final Logger logger = Logger.getLogger(DetallePropiedadServlet.class.getName());

    @Override
    public void init() throws ServletException {
        propiedadesDAO = new PropiedadesDAO();
        fotosDAO = new FotosPropiedadDAO();
        tiposDAO = new TiposPropiedadDAO();
        pcDAO = new PropiedadesCaracteristicasDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idPropiedadParam = request.getParameter("id");
            if (idPropiedadParam == null || idPropiedadParam.isEmpty()) {
                throw new ServletException("El ID de la propiedad es requerido");
            }

            int id_propiedad = Integer.parseInt(idPropiedadParam);
            Propiedades propiedad = propiedadesDAO.obtenerPropiedadPorId(id_propiedad);

            if (propiedad == null) {
                throw new ServletException("Propiedad no encontrada con ID: " + id_propiedad);
            }

            // Obtener el tipo de propiedad
            TiposPropiedad tipo = tiposDAO.obtenerPorId(propiedad.getId_tipo());

            // Obtener fotos
            ArrayList<FotosPropiedad> fotos = fotosDAO.obtenerFotosPorPropiedad(id_propiedad);
// 3) Obtener lista de propiedades
            int idTipo = propiedad.getId_tipo();
            String modalidad = propiedad.getModalidad();
            List<Propiedades> propiedades = propiedadesDAO.listar(idTipo, modalidad);
            // Obtener características específicas de la propiedad

// 4) Construir detallesMap: id_propiedad → List<PropiedadesCaracteristicas>
            Map<Integer, List<PropiedadesCaracteristicas>> detallesMap = new HashMap<>();
            for (Propiedades p : propiedades) {
                detallesMap.put(
                        p.getId_propiedad(),
                        pcDAO.listarPorPropiedad(p.getId_propiedad())
                );
                logger.info("Propiedad " + p.getId_propiedad() + " → " + detallesMap.get(p.getId_propiedad()));
            }

            request.setAttribute("propiedad", propiedad);
            request.setAttribute("tipoPropiedad", tipo);
            request.setAttribute("fotos", fotos);

            request.setAttribute("detalleMap", detallesMap);
            // Obtener características específicas de la propiedad seleccionada AGREGADO 15/05
            List<PropiedadesCaracteristicas> caracteristicas = pcDAO.listarPorPropiedad(id_propiedad);
            request.setAttribute("caracteristicas", caracteristicas);

            request.getRequestDispatcher("/WEB-INF/views/detalle.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            throw new ServletException("El ID de la propiedad debe ser un número válido", e);
        } catch (SQLException e) {
            throw new ServletException("Error al cargar detalle de propiedad: " + e.getMessage(), e);
        }
    }
}
