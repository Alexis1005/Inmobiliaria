package com.mycompany.inmobiliaria.controlador;

import com.mycompany.inmobiliaria.resources.config.EmailService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/enviarContacto")
public class ContactoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recuperar los datos del formulario
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("tel");
        String correo = request.getParameter("correo");
        String mensaje = request.getParameter("mensaje");
        String idPropiedadStr = request.getParameter("id_propiedad");

        //convertir el id a entero
        int idPropiedad;
        try {
            idPropiedad = Integer.parseInt(idPropiedadStr);
        } catch (NumberFormatException e) {
            //en caso de que IdPropiedadStr no sea un número valido
            idPropiedad = -1;
        }

        //generar URL para acceder a la propiedad enviada
        String urlPropiedad = "http://localhost:8080/inmobiliaria/detallePropiedad?id=" + idPropiedad;

        // Llamar al método para enviar el correo
        EmailService.enviarCorreo(nombre, telefono, correo, mensaje, idPropiedad, urlPropiedad);

        // Redirigir a la página de detalle con mensaje de éxito como parámetro
        String mensajeFinal = "Gracias por contactarnos, " + nombre + ". Te responderemos pronto.";
        response.sendRedirect("detallePropiedad?id=" + idPropiedad + "&mensaje=" + java.net.URLEncoder.encode(mensajeFinal, "UTF-8"));

    }
}