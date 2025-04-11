package com.mycompany.inmobiliaria.controlador;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/imagenes/*")
public class ImagenServlet extends HttpServlet {
    private static final String RUTA_IMAGENES = "C:/Users/PC/Documents/NetBeansProjects/inmobiliaria/img_propiedades_externas/";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String imagen = request.getPathInfo(); // Obtiene el nombre del archivo
        if (imagen == null || imagen.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Nombre de imagen no especificado");
            return;
        }

        File archivo = new File(RUTA_IMAGENES, imagen);
        if (!archivo.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Imagen no encontrada");
            return;
        }

        response.setContentType(getServletContext().getMimeType(archivo.getName()));
        response.setContentLength((int) archivo.length());

        try (FileInputStream fis = new FileInputStream(archivo);
             OutputStream os = response.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        }
    }
}
