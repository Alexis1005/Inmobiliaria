package com.mycompany.inmobiliaria.controlador;

import com.mycompany.inmobiliaria.modelo.Agente;
import com.mycompany.inmobiliaria.modelo.FotosPropiedad;
import com.mycompany.inmobiliaria.modelo.Propiedades;
import com.mycompany.inmobiliaria.modelo.PropiedadesCaracteristicas;
import com.mycompany.inmobiliaria.modelo.TiposPropiedad;
import com.mycompany.inmobiliaria.modelo.dao.AgenteDAO;
import com.mycompany.inmobiliaria.modelo.dao.FotosPropiedadDAO;
import com.mycompany.inmobiliaria.modelo.dao.PropiedadesCaracteristicasDAO;
import com.mycompany.inmobiliaria.modelo.dao.PropiedadesDAO;
import com.mycompany.inmobiliaria.modelo.dao.TiposPropiedadDAO;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/subirPropiedad")
@MultipartConfig(fileSizeThreshold = 0, // Forzar escritura a disco
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class SubirPropiedadServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(SubirPropiedadServlet.class.getName());
    private static final String UPLOAD_DIR = "C:\\Users\\jx\\Documents\\NetBeansProjects\\Inmobiliaria\\img_propiedades_externas";
    private PropiedadesDAO propiedadesDAO = new PropiedadesDAO();
    private FotosPropiedadDAO fotosDAO = new FotosPropiedadDAO();
    private TiposPropiedadDAO tiposPropiedadDAO = new TiposPropiedadDAO();
    private AgenteDAO agentesDAO = new AgenteDAO();

    @Override
    public void init() throws ServletException {
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        logger.info("Directorio externo inicializado en: " + UPLOAD_DIR);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Obtener datos del formulario
            String direccion = request.getParameter("direccion");
            double precio = Double.parseDouble(request.getParameter("precio"));
            String descripcion = request.getParameter("descripcion");
            String estado = request.getParameter("estado");
            String modalidad = request.getParameter("modalidad");
            int idTipo = Integer.parseInt(request.getParameter("idTipo"));
            int idAgente = Integer.parseInt(request.getParameter("idAgente"));
            String caracteristicasGenerales = request.getParameter("caracteristicasGenerales");

            // Crear objeto Propiedades
            Propiedades propiedad = new Propiedades();
            propiedad.setDireccion(direccion);
            propiedad.setPrecio(precio);
            propiedad.setDescripcion(descripcion);
            propiedad.setEstado(estado);
            propiedad.setModalidad(modalidad);
            propiedad.setId_tipo(idTipo);
            propiedad.setId_agente(idAgente);
            propiedad.setCaracteristicasGenerales(caracteristicasGenerales);

            // Insertar la propiedad en la base de datos y obtener el ID generado
            int idPropiedad = propiedadesDAO.insertar(propiedad);
            logger.log(Level.INFO, "Propiedad insertada con ID: {0}", idPropiedad);

            // Manejar las imágenes subidas
            List<String> imagenesRutas = new ArrayList<>();
            String appPath = request.getServletContext().getRealPath("");
            String projectImagePath = appPath + File.separator + "imagenes";
            File projectImageDir = new File(projectImagePath);
            if (!projectImageDir.exists()) {
                projectImageDir.mkdirs();
            }

            for (Part part : request.getParts()) {
                if ("imagenes".equals(part.getName())) {
                    String fileName = extractFileName(part);
                    if (fileName != null && !fileName.isEmpty() && part.getSize() > 0) {
                        String filePath = UPLOAD_DIR + File.separator + fileName;
                        part.write(filePath);
                        logger.log(Level.INFO, "Archivo guardado en: {0}", filePath);

                        String projectFilePath = projectImagePath + File.separator + fileName;
                        Files.copy(Paths.get(filePath), Paths.get(projectFilePath));
                        logger.log(Level.INFO, "Archivo copiado al proyecto: {0}", projectFilePath);

                        String relativePath = "imagenes/" + fileName;
                        imagenesRutas.add(relativePath);
                    } else {
                        logger.log(Level.WARNING, "Parte ignorada: {0}", fileName == null ? "Nombre nulo" : "Archivo vacío");
                    }
                }
            }

            // Asignar y actualizar la primera imagen como principal (si existe)
            if (!imagenesRutas.isEmpty()) {
                propiedad.setImagen(imagenesRutas.get(0));
                propiedadesDAO.actualizarImagen(idPropiedad, imagenesRutas.get(0));
            }

            // Guardar rutas de imágenes en la base de datos
            for (String ruta : imagenesRutas) {
                FotosPropiedad foto = new FotosPropiedad();
                foto.setId_propiedad(idPropiedad);
                foto.setRuta_foto(ruta);
                fotosDAO.insertar(foto);
            }
            PropiedadesCaracteristicasDAO pcDAO = new PropiedadesCaracteristicasDAO();
            Enumeration<String> parameterNames = request.getParameterNames();
            logger.info("Parámetros recibidos:");
            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                logger.log(Level.INFO, "Par\u00e1metro: {0} = {1}", new Object[]{paramName, request.getParameter(paramName)});
                if (paramName.startsWith("detalle_")) {
                    String idCaracteristicaStr = paramName.substring("detalle_".length());
                    int idCaracteristica = Integer.parseInt(idCaracteristicaStr);
                    String detalle = request.getParameter(paramName);
                    logger.log(Level.INFO, "Procesando caracter\u00edstica: idCaracteristica={0}, detalle={1}", new Object[]{idCaracteristica, detalle});
                    if (detalle != null && !detalle.trim().isEmpty()) {
                        PropiedadesCaracteristicas pc = new PropiedadesCaracteristicas(idPropiedad, idCaracteristica, detalle);
                        pcDAO.agregar(pc);
                        logger.log(Level.INFO, "Caracter\u00edstica guardada: {0}", pc);
                    }
                }
            }

            // Redirigir solo una vez al finalizar con éxito
            response.sendRedirect(request.getContextPath() + "/propiedades");
        } catch (ServletException | IOException | NumberFormatException | SQLException e) {
            logger.log(Level.SEVERE, "Error al subir propiedad: " + e.getMessage(), e);
            // Establecer mensaje de error y reenviar a una página de error
            request.setAttribute("errorMessage", "Error al subir propiedad: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
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
                return fileName.replaceAll("\\s+", "_");
            }
        }
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<TiposPropiedad> tipos = tiposPropiedadDAO.ListarTiposPropiedades();
        List<Agente> agentes = null;
        try {
            agentes = agentesDAO.listar();
        } catch (SQLException ex) {
            Logger.getLogger(SubirPropiedadServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("tiposPropiedad", tipos);
        request.setAttribute("agentes", agentes);
        request.getRequestDispatcher("/subirPropiedad.jsp").forward(request, response);
    }
}
