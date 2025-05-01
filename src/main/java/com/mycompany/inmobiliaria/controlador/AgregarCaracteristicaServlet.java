package com.mycompany.inmobiliaria.controlador;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.mycompany.inmobiliaria.modelo.Caracteristicas;
import com.mycompany.inmobiliaria.modelo.dao.CaracteristicasDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.Collectors;

@WebServlet("/agregarCaracteristica")
public class AgregarCaracteristicaServlet extends HttpServlet {
    private CaracteristicasDAO caracteristicasDAO;

    public AgregarCaracteristicaServlet() {
        try {
            this.caracteristicasDAO = new CaracteristicasDAO();
        } catch (Exception e) {
            throw new RuntimeException("Error al inicializar CaracteristicasDAO", e);
        }
    }

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    try {
        String body = request.getReader().lines().collect(Collectors.joining());
        System.out.println("Cuerpo de la solicitud: " + body); // Depuración
        JsonObject json = JsonParser.parseString(body).getAsJsonObject();
        String nombre = json.get("nombre").getAsString();
        String detalle = json.has("detalle") ? json.get("detalle").getAsString() : null;
        int tipoId = json.get("tipoId").getAsInt();

        System.out.println("Datos recibidos - Nombre: " + nombre + ", Detalle: " + detalle + ", TipoId: " + tipoId); // Depuración
        Caracteristicas caracteristica = new Caracteristicas(nombre, detalle, tipoId);
        boolean success = caracteristicasDAO.agregar(caracteristica);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"success\": " + success + "}");
    } catch (JsonSyntaxException | IOException | SQLException e) { // Imprimir la traza completa para depuración
        // Imprimir la traza completa para depuración
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al agregar característica: " + e.getMessage());
    }
}
}