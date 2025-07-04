package com.mycompany.inmobiliaria.controlador;

import com.mycompany.inmobiliaria.modelo.FotosPropiedad;
import com.mycompany.inmobiliaria.modelo.Propiedades;
import com.mycompany.inmobiliaria.modelo.PropiedadesCaracteristicas;
import com.mycompany.inmobiliaria.modelo.TiposPropiedad;
import com.mycompany.inmobiliaria.modelo.dao.FotosPropiedadDAO;
import com.mycompany.inmobiliaria.modelo.dao.PropiedadesCaracteristicasDAO;
import com.mycompany.inmobiliaria.modelo.dao.PropiedadesDAO;
import com.mycompany.inmobiliaria.modelo.dao.TiposPropiedadDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/principal")
public class PrincipalServlet extends HttpServlet {

   private PropiedadesDAO propiedadesDAO;
    private FotosPropiedadDAO fotosDAO;
    private PropiedadesCaracteristicasDAO pcDAO;
    private TiposPropiedadDAO tiposDAO;

    @Override
    public void init() throws ServletException {
        propiedadesDAO = new PropiedadesDAO();
        fotosDAO       = new FotosPropiedadDAO();
        pcDAO          = new PropiedadesCaracteristicasDAO();
        tiposDAO       = new TiposPropiedadDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // 1) Cargar tipos para el filtro
            List<TiposPropiedad> tipos = tiposDAO.ListarTiposPropiedades();

            // 2) Leer filtros (el name de tu <select> en el JSP es "tipoPropiedad")
            Integer idTipo = null;
            String tipoParam = request.getParameter("tipoPropiedad");
            if (tipoParam != null && !tipoParam.isEmpty()) {
                idTipo = Integer.parseInt(tipoParam);
            }
            String modalidad = request.getParameter("modalidad");

            // 3) Obtener lista de propiedades
            List<Propiedades> propiedades = propiedadesDAO.listar(idTipo, modalidad);
            
             // 4) Para cada propiedad, obtener lista de fotos y ponerlas en un mapa:
            Map<Integer, List<FotosPropiedad>> fotosMap = new HashMap<>();
            for (Propiedades p : propiedades) {
                List<FotosPropiedad> fotos = fotosDAO.obtenerFotosPorPropiedad(p.getId_propiedad());
                fotosMap.put(p.getId_propiedad(), fotos);
            }

            // ) Construir detallesMap: id_propiedad → List<PropiedadesCaracteristicas>
            Map<Integer, List<PropiedadesCaracteristicas>> detallesMap = new HashMap<>();
            for (Propiedades p : propiedades) {
                detallesMap.put(
                    p.getId_propiedad(),
                    pcDAO.listarPorPropiedad(p.getId_propiedad())
                );
            }

            // 5) Pasar atributos al JSP
            request.setAttribute("tiposPropiedad", tipos);
            request.setAttribute("propiedades", propiedades);
            request.setAttribute("detallesMap", detallesMap);
            request.setAttribute("fotosMap", fotosMap);

            // 6) Forward a la vista
            request.getRequestDispatcher("/WEB-INF/views/principal.jsp")
                   .forward(request, response);

        } catch (NumberFormatException ex) {
            throw new ServletException("El parámetro 'tipoPropiedad' debe ser un número", ex);
        } catch (SQLException ex) {
            throw new ServletException("Error de base de datos al obtener características", ex);
        }
    }
}