package com.mycompany.inmobiliaria.controlador;

import com.mycompany.inmobiliaria.modelo.FotosPropiedad;
import com.mycompany.inmobiliaria.modelo.Propiedades;
import com.mycompany.inmobiliaria.modelo.PropiedadesCaracteristicas;
import com.mycompany.inmobiliaria.modelo.dao.FotosPropiedadDAO;
import com.mycompany.inmobiliaria.modelo.dao.PropiedadesCaracteristicasDAO;
import com.mycompany.inmobiliaria.modelo.dao.PropiedadesDAO;
import com.mycompany.inmobiliaria.modelo.dao.TiposPropiedadDAO;
import com.mycompany.inmobiliaria.modelo.dao.AgenteDAO;
import com.mycompany.inmobiliaria.modelo.TiposPropiedad;
import com.mycompany.inmobiliaria.modelo.Agente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/editarPropiedad")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class EditarPropiedadServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(EditarPropiedadServlet.class.getName());
    private static final String UPLOAD_DIR = "C:/Users/PC/Documents/NetBeansProjects/inmobiliaria/img_propiedades_externas/";
    private PropiedadesDAO propiedadesDAO = new PropiedadesDAO();
    private FotosPropiedadDAO fotosDAO = new FotosPropiedadDAO();
    private PropiedadesCaracteristicasDAO pcDAO = new PropiedadesCaracteristicasDAO();
    private TiposPropiedadDAO tiposPropiedadDAO = new TiposPropiedadDAO();
    private AgenteDAO agenteDAO = new AgenteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam != null && !idParam.trim().isEmpty()) {
            cargarPaginaEdicion(request, response, idParam.trim());
        } else {
            listarPropiedadesConFiltros(request, response,
                    request.getParameter("tipo"),
                    request.getParameter("modalidad"));
        }
    }

    private void cargarPaginaEdicion(HttpServletRequest request, HttpServletResponse response, String idParam)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(idParam);
            Propiedades propiedad = propiedadesDAO.obtenerPorId(id);

            if (propiedad == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Propiedad no encontrada");
                return;
            }

            List<PropiedadesCaracteristicas> caracteristicas = pcDAO.listarPorPropiedad(id);
            List<FotosPropiedad> fotos = fotosDAO.obtenerFotosPorPropiedad(id);
            List<TiposPropiedad> tipos = tiposPropiedadDAO.ListarTiposPropiedades();
            List<Agente> agentes = agenteDAO.listar();

            request.setAttribute("propiedad", propiedad);
            request.setAttribute("caracteristicas", caracteristicas);
            request.setAttribute("fotos", fotos);
            request.setAttribute("tipos", tipos);
            request.setAttribute("agentes", agentes);

            request.getRequestDispatcher("/WEB-INF/views/editarPropiedadForm.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido");
        } catch (SQLException e) {
            throw new ServletException("Error al cargar propiedad", e);
        }
    }

    private void listarPropiedadesConFiltros(HttpServletRequest request, HttpServletResponse response,
        String tipoParam, String modalidadParam) throws ServletException, IOException {
    try {
        List<Propiedades> propiedades;

        // Aplicar filtros si están presentes
        if ((tipoParam != null && !tipoParam.isEmpty()) || (modalidadParam != null && !modalidadParam.isEmpty())) {
            Integer idTipo = null;
            if (tipoParam != null && !tipoParam.isEmpty()) {
                try {
                    idTipo = Integer.parseInt(tipoParam);
                } catch (NumberFormatException e) {
                    logger.log(Level.WARNING, "Tipo de propiedad inválido: " + tipoParam);
                }
            }
            propiedades = propiedadesDAO.listarConFiltros(idTipo, modalidadParam);
        } else {
            propiedades = propiedadesDAO.obtenerTodasLasPropiedades();
        }

        // **AGREGAR ESTA PARTE - IGUAL QUE EN EL SERVLET PRINCIPAL**
        // Para cada propiedad, obtener lista de fotos y ponerlas en un mapa:
        Map<Integer, List<FotosPropiedad>> fotosMap = new HashMap<>();
        for (Propiedades p : propiedades) {
            List<FotosPropiedad> fotos = fotosDAO.obtenerFotosPorPropiedad(p.getId_propiedad());
            fotosMap.put(p.getId_propiedad(), fotos);
        }

        // También puedes agregar el mapa de características si lo necesitas
        Map<Integer, List<PropiedadesCaracteristicas>> detallesMap = new HashMap<>();
        for (Propiedades p : propiedades) {
            detallesMap.put(
                p.getId_propiedad(),
                pcDAO.listarPorPropiedad(p.getId_propiedad())
            );
        }

        List<TiposPropiedad> tipos = tiposPropiedadDAO.ListarTiposPropiedades();
        List<Agente> agentes = agenteDAO.listar();

        request.setAttribute("listaPropiedades", propiedades);
        request.setAttribute("tiposPropiedad", tipos);
        request.setAttribute("agentes", agentes);
        request.setAttribute("tipoSeleccionado", tipoParam);
        request.setAttribute("modalidadSeleccionada", modalidadParam);
        
        // **AGREGAR ESTOS ATRIBUTOS - IGUAL QUE EN EL SERVLET PRINCIPAL**
        request.setAttribute("fotosMap", fotosMap);
        request.setAttribute("detallesMap", detallesMap);

        request.getRequestDispatcher("WEB-INF/views/editarPropiedad.jsp").forward(request, response);

    } catch (SQLException e) {
        logger.log(Level.SEVERE, "Error al obtener propiedades", e);
        request.setAttribute("errorMessage", "Error al cargar las propiedades: " + e.getMessage());
        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Obtener y validar parámetros del formulario
            int id = Integer.parseInt(request.getParameter("id"));
            String direccion = request.getParameter("direccion");
            String descripcion = request.getParameter("descripcion");
            double precio = Double.parseDouble(request.getParameter("precio"));
            String estado = request.getParameter("estado");
            String modalidad = request.getParameter("modalidad");
            int idTipo = Integer.parseInt(request.getParameter("idTipo"));
            int idAgente = Integer.parseInt(request.getParameter("idAgente"));
            String caracteristicasGenerales = request.getParameter("caracteristicasGenerales");
            String[] fotosAEliminar = request.getParameterValues("fotosAEliminar");

            // Crear y llenar el objeto Propiedades
            Propiedades propiedad = new Propiedades();
            propiedad.setId_propiedad(id);
            propiedad.setDireccion(direccion);
            propiedad.setDescripcion(descripcion);
            propiedad.setPrecio(precio);
            propiedad.setEstado(estado);
            propiedad.setModalidad(modalidad);
            propiedad.setId_tipo(idTipo);
            propiedad.setId_agente(idAgente);
            propiedad.setCaracteristicasGenerales(caracteristicasGenerales);

            // Actualizar la propiedad en la base de datos
            int resultado = propiedadesDAO.actualizar(propiedad);
            if (resultado > 0) {
                logger.info("Propiedad actualizada exitosamente con ID: " + id);
            } else {
                logger.warning("No se pudo actualizar la propiedad con ID: " + id);
            }

            // Manejar eliminación de fotos específicas
            if (fotosAEliminar != null && fotosAEliminar.length > 0) {
                for (String idFoto : fotosAEliminar) {
                    try {
                        int fotoId = Integer.parseInt(idFoto);
                        fotosDAO.eliminarFotoPorId(fotoId);
                        logger.info("Foto eliminada: " + fotoId);
                    } catch (NumberFormatException | SQLException e) {
                        logger.log(Level.WARNING, "Error al eliminar foto con id: " + idFoto, e);
                    }
                }
            }

            // Procesar nuevas imágenes
            procesarNuevasImagenes(request, id);

            // Actualizar características
            actualizarCaracteristicas(request, id);

            // Redirigir con mensaje de éxito
            response.sendRedirect("editarPropiedad?success=1");

        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Error de formato en parámetros", e);
            request.setAttribute("errorMessage", "Error en los datos enviados: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar propiedad o características", e);
            request.setAttribute("errorMessage", "Error al actualizar la propiedad: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error inesperado al procesar la solicitud", e);
            request.setAttribute("errorMessage", "Error inesperado: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    private void procesarNuevasImagenes(HttpServletRequest request, int idPropiedad) throws ServletException, IOException {
        List<String> imagenesRutas = new ArrayList<>();
        String appPath = request.getServletContext().getRealPath("");
        String projectImagePath = appPath + File.separator + "imagenes"; // Asegúrate de que este sea el directorio correcto
        File projectImageDir = new File(projectImagePath);
        if (!projectImageDir.exists()) {
            projectImageDir.mkdirs();
            logger.info("Directorio de imágenes creado: " + projectImagePath);
        }

        boolean reemplazarTodas = "on".equals(request.getParameter("reemplazarTodas"));

        for (Part part : request.getParts()) {
            if ("imagen".equals(part.getName()) && part.getSize() > 0) {
                String fileName = extractFileName(part);
                if (fileName != null && !fileName.isEmpty()) {
                    String uniqueFileName = System.currentTimeMillis() + "_" + fileName.replaceAll("\\s+", "_");

                    String filePath = UPLOAD_DIR + File.separator + uniqueFileName;
                    part.write(filePath);
                    logger.info("Archivo guardado en: " + filePath);

                    String projectFilePath = projectImagePath + File.separator + uniqueFileName;
                    try {
                        Files.copy(Paths.get(filePath), Paths.get(projectFilePath));
                        logger.info("Archivo copiado al proyecto: " + projectFilePath);
                    } catch (Exception e) {
                        logger.log(Level.WARNING, "No se pudo copiar archivo al proyecto: " + projectFilePath, e);
                    }

                    String relativePath = "imagenes/" + uniqueFileName;
                    imagenesRutas.add(relativePath);
                }
            }
        }
// procesar nuevas imagenes si las hay
        if (!imagenesRutas.isEmpty()) {
            try {
                if (reemplazarTodas) {
                    fotosDAO.eliminarPorPropiedad(idPropiedad);
                }

                for (String ruta : imagenesRutas) {
                    FotosPropiedad foto = new FotosPropiedad();
                    foto.setId_propiedad(idPropiedad);
                    foto.setRuta_foto(ruta);
                    fotosDAO.insertar(foto);
                }

                // ACTUALIZAR SIEMPRE LA IMAGEN PRINCIPAL CON LA PRIMERA FOTO
                String primeraImagen = imagenesRutas.get(0);
                propiedadesDAO.actualizarImagen(idPropiedad, primeraImagen);
                //actualizar imagen principal con la primera 
                propiedadesDAO.actualizarImagen(idPropiedad, imagenesRutas.get(0));
                logger.info("Nuevas fotos agregadas: " + imagenesRutas.size());
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error al procesar imágenes", e);
                throw new ServletException("Error al procesar imágenes", e);
            }
        }
    }

    private void actualizarCaracteristicas(HttpServletRequest request, int idPropiedad) throws SQLException {
        Enumeration<String> parameterNames = request.getParameterNames();
        PropiedadesCaracteristicasDAO carDAO = new PropiedadesCaracteristicasDAO();

        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if (paramName.startsWith("detalle_")) {
                String idCaracteristicaStr = paramName.substring("detalle_".length());
                if (idCaracteristicaStr.isEmpty()) {
                    logger.warning("Ignorando parámetro detalle_ vacío: " + paramName);
                    continue;
                }
                try {
                    int idCaracteristica = Integer.parseInt(idCaracteristicaStr);
                    String detalle = request.getParameter(paramName);

                    if (detalle != null && !detalle.trim().isEmpty()) {
                        PropiedadesCaracteristicas pc = new PropiedadesCaracteristicas();
                        pc.setId_propiedad(idPropiedad);
                        pc.setId_caracteristica(idCaracteristica);
                        pc.setDetalle(detalle.trim());

                        if (carDAO.existe(idPropiedad, idCaracteristica)) {
                            carDAO.actualizar(pc);
                            logger.info("Característica actualizada: propiedad=" + idPropiedad + ", característica=" + idCaracteristica);
                        } else {
                            carDAO.insertar(pc);
                            logger.info("Característica agregada: propiedad=" + idPropiedad + ", característica=" + idCaracteristica);
                        }
                    } else {
                        logger.info("Ignorando característica con detalle vacío o nulo: propiedad=" + idPropiedad + ", característica=" + idCaracteristica);
                    }
                } catch (NumberFormatException e) {
                    logger.log(Level.WARNING, "Error al procesar característica, ID no válido: " + paramName, e);
                } catch (SQLException e) {
                    logger.log(Level.SEVERE, "Error en la base de datos al procesar característica: " + paramName, e);
                    throw e;
                }
            }
        }

    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        if (contentDisp == null) {
            return null;
        }

        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                String fileName = token.substring(token.indexOf("=") + 2, token.length() - 1);
                return fileName.replaceAll("[^a-zA-Z0-9\\.\\-_]", "_");
            }
        }
        return null;
    }

}
