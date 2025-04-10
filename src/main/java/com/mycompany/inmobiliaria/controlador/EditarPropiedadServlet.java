package com.mycompany.inmobiliaria.controlador;

import com.mycompany.inmobiliaria.modelo.Propiedades;
import com.mycompany.inmobiliaria.modelo.dao.PropiedadesDAO;
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
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/editarPropiedad")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class EditarPropiedadServlet extends HttpServlet {
}//    private static final String UPLOAD_DIR = "D:/Usuarios/Ruben/Desktop/inmobiliaria BACK - copia/Inmobiliaria/img_propiedades_externas/";
//    private static final String TARGET_DIR = "D:/Usuarios/Ruben/Desktop/inmobiliaria BACK - copia/Inmobiliaria/target/inmobiliaria-1.0-SNAPSHOT/imagenes/";

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Obtener el ID de la propiedad desde los parámetros
//        int id = Integer.parseInt(request.getParameter("id_propiedad"));
//
//        
////        try {
////            // Obtener la propiedad por ID
////            Propiedades propiedad = propiedadesDAO.obtenerPropiedadPorId(id_propiedad);
////            if (propiedad == null) {
////                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Propiedad no encontrada");
////                return;
////            }
//
//            // Pasar la propiedad al JSP
//            request.setAttribute("propiedad", propiedad);
//            request.getRequestDispatcher("/editarPropiedad.jsp").forward(request, response);
//        } catch (SQLException e) {
//            throw new ServletException("Error al obtener la propiedad", e);
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Obtener los datos del formulario
//        int id = Integer.parseInt(request.getParameter("id"));
//        String direccion = request.getParameter("direccion");
//        double precio = Double.parseDouble(request.getParameter("precio"));
//        String estado = request.getParameter("estado");
//        String caracteristicasGenerales = request.getParameter("caracteristicasGenerales");
//
//        // Obtener la conexión a la base de datos
//        Connection connection = (Connection) getServletContext().getAttribute("dbConnection");
//        PropiedadesDAO propiedadDAO = new PropiedadesDAO(connection);
//
//        try {
//            // Obtener la propiedad existente
//            Propiedades propiedad = propiedadDAO.obtenerPropiedadPorId(id);
//            if (propiedad == null) {
//                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Propiedad no encontrada");
//                return;
//            }
//
//            // Manejar la imagen (si se subió una nueva)
//            Part filePart = request.getPart("imagen");
//            String nombreArchivo = propiedad.getImagen(); // Mantener la imagen actual por defecto
//
//            if (filePart != null && filePart.getSize() > 0) {
//                // Obtener el nombre del archivo
//                nombreArchivo = filePart.getSubmittedFileName();
//
//                // Guardar el archivo en img_propiedades_externas
//                File directorio = new File(UPLOAD_DIR);
//                if (!directorio.exists()) {
//                    directorio.mkdirs();
//                }
//                File archivoGuardado = new File(directorio, nombreArchivo);
//                filePart.write(archivoGuardado.getAbsolutePath());
//
//                // Copiar el archivo a target/inmobiliaria-1.0-SNAPSHOT/imagenes
//                File directorioDestino = new File(TARGET_DIR);
//                if (!directorioDestino.exists()) {
//                    directorioDestino.mkdirs();
//                }
//                File destino = new File(directorioDestino, nombreArchivo);
//                if (destino.exists()) {
//                    destino.delete(); // Sobrescribir si ya existe
//                }
//                Files.copy(archivoGuardado.toPath(), destino.toPath());
//            }
//
//            // Actualizar los datos de la propiedad
//            propiedad.setDireccion(direccion);
//            propiedad.setPrecio(precio);
//            propiedad.setEstado(estado);
//            propiedad.setCaracteristicasGenerales(caracteristicasGenerales);
//            propiedad.setImagen(nombreArchivo);
//
//            // Guardar los cambios en la base de datos
//            propiedadesDAO.actualizar(propiedad);
//
//            // Redirigir a la página principal
//            response.sendRedirect(request.getContextPath() + "/principal");
//        } catch (SQLException e) {
//            throw new ServletException("Error al actualizar la propiedad", e);
//        }
//    }
//}
