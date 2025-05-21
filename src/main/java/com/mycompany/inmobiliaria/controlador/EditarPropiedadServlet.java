package com.mycompany.inmobiliaria.controlador;

import com.mycompany.inmobiliaria.modelo.Propiedades;
import com.mycompany.inmobiliaria.modelo.PropiedadesCaracteristicas;
import com.mycompany.inmobiliaria.modelo.TiposPropiedad;
import com.mycompany.inmobiliaria.modelo.dao.PropiedadesCaracteristicasDAO;
import com.mycompany.inmobiliaria.modelo.dao.PropiedadesDAO;
import com.mycompany.inmobiliaria.modelo.dao.TiposPropiedadDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/editarPropiedad")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class EditarPropiedadServlet extends HttpServlet {

    private final PropiedadesDAO propiedadesDAO = new PropiedadesDAO();
    private final PropiedadesCaracteristicasDAO pcDAO = new PropiedadesCaracteristicasDAO();
    private TiposPropiedadDAO tiposDAO = new TiposPropiedadDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1) Verificación de sesión
        if (request.getSession(false) == null
                || request.getSession(false).getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            // 1) Cargar tipos para el filtro
            List<TiposPropiedad> tipos = tiposDAO.ListarTiposPropiedades();

            // 2) Leemos los parámetros de filtrado (si vienen vacíos o nulos, listamos todas)
            String filtroModalidad = request.getParameter("modalidad"); // nombre del <select name="modalidad">

            // 3) Convertimos idTipo a Integer (si no está vacío)
            Integer idTipo = null;
            String tipoParam = request.getParameter("tipo");
            if (tipoParam != null && !tipoParam.isEmpty()) {
                idTipo = Integer.valueOf(tipoParam);
            }

            List<Propiedades> listaFiltrada = propiedadesDAO.listar(idTipo, filtroModalidad, "disponible");
                        
            
            // 5) Construimos el “map” id_propiedad → lista de detalles
            Map<Integer, List<PropiedadesCaracteristicas>> detallesMap = new HashMap<>();
            for (Propiedades p : listaFiltrada) {
                try {
                    List<PropiedadesCaracteristicas> detalles
                            = pcDAO.listarPorPropiedad(p.getId_propiedad());
                    detallesMap.put(p.getId_propiedad(), detalles);
                } catch (SQLException ex) {
                    throw new ServletException(
                            "Error cargando detalles para propiedad " + p.getId_propiedad(), ex
                    );
                }
            }

            // 6) Enviamos atributos al JSP:
            request.setAttribute("tiposPropiedad", tipos);
            request.setAttribute("listaPropiedades", listaFiltrada);
            request.setAttribute("detallesMap", detallesMap);

            // 7) Forward al JSP de edición (que incluye tanto el formulario de filtros como las cards)
            request.getRequestDispatcher("editarPropiedad.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            throw new ServletException("Error al listar/filtrar propiedades", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1) Verificar sesión (igual que antes)
        if (request.getSession(false) == null
                || request.getSession(false).getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            // 2) Leer el ID de la propiedad a editar
            int id = Integer.parseInt(request.getParameter("id"));

            // 3) Traer el objeto completo desde BD (para no perder campos que no se envían)
            Propiedades actual = propiedadesDAO.obtenerPorId(id);
            if (actual == null) {
                throw new ServletException("No se encontró la propiedad con ID = " + id);
            }

            // 4) Sobrescribo en ese objeto solamente los campos enviados por el formulario
            //    (suponiendo que tu formulario incluye estos campos con name="..."
            actual.setDireccion(request.getParameter("direccion"));
            actual.setDescripcion(request.getParameter("descripcion"));
            actual.setPrecio(Double.parseDouble(request.getParameter("precio")));
            actual.setEstado(request.getParameter("estado"));
            actual.setModalidad(request.getParameter("modalidad"));

            // Si enviás un campo 'imagen' como texto (por ejemplo nombre de archivo), lo seteas:
            String nuevaImagen = request.getParameter("imagen");
            if (nuevaImagen != null && !nuevaImagen.isEmpty()) {
                actual.setImagen(nuevaImagen);
            }
            // Si tu formulario también envía 'caracteristicasGenerales':
            String cg = request.getParameter("caracteristicasGenerales");
            if (cg != null) {
                actual.setCaracteristicasGenerales(cg);
            }

            // 5) Llamar finalmente al método que actualiza TODOS los campos
            int filas = propiedadesDAO.actualizar(actual);
            if (filas == 0) {
                // Opcional: si no se actualizó nada, lanzar excepción o mensaje
                throw new ServletException("No se actualizó ninguna fila (ID inválido?).");
            }

            // 6) Redirigir a la misma pantalla de edición para recargar listado
            response.sendRedirect("editarPropiedad");
        } catch (NumberFormatException ex) {
            throw new ServletException("ID o precio no numérico", ex);
        }
    }

}
