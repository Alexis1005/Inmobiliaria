
package com.mycompany.inmobiliaria.controlador;

import com.mycompany.inmobiliaria.modelo.Caracteristica;
import com.mycompany.inmobiliaria.modelo.FotosPropiedad;
import com.mycompany.inmobiliaria.modelo.Propiedades;
import com.mycompany.inmobiliaria.modelo.TiposPropiedad;
import com.mycompany.inmobiliaria.modelo.dao.CaracteristicasDAO;
import com.mycompany.inmobiliaria.modelo.dao.FotosPropiedadDAO;
import com.mycompany.inmobiliaria.modelo.dao.PropiedadesDAO;
import com.mycompany.inmobiliaria.modelo.dao.TiposPropiedadDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/detallePropiedad")
public class DetallePropiedadServlet extends HttpServlet {
    private PropiedadesDAO propiedadesDAO = new PropiedadesDAO();
    private FotosPropiedadDAO fotosDAO = new FotosPropiedadDAO();
    private TiposPropiedadDAO tiposDAO = new TiposPropiedadDAO();
    private CaracteristicasDAO caracteristicasDAO = new CaracteristicasDAO(); // Instanciar DAO de características

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
        
        // Obtener características por tipo de propiedad
            List<Caracteristica> caracteristicas = caracteristicasDAO.listarPorTipo(tipo.getId_tipo());

        request.setAttribute("propiedad", propiedad);
        request.setAttribute("tipoPropiedad", tipo); // Nuevo atributo
        request.setAttribute("fotos", fotos);
        request.setAttribute("caracteristicas", caracteristicas); // Añadir las características
        
        request.getRequestDispatcher("/detalle.jsp").forward(request, response);
    } catch (NumberFormatException e) {
        throw new ServletException("El ID de la propiedad debe ser un número válido", e);
    } catch (Exception e) {
        throw new ServletException("Error al cargar detalle de propiedad", e);
    }
}}
    