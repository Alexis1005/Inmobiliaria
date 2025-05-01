package com.mycompany.inmobiliaria.controlador;

import com.google.gson.Gson;
import com.mycompany.inmobiliaria.modelo.Caracteristicas;
import com.mycompany.inmobiliaria.modelo.dao.CaracteristicasDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/caracteristicas")
public class CaracteristicasServlet extends HttpServlet {

    private CaracteristicasDAO caracteristicasDAO = new CaracteristicasDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tipoId = request.getParameter("tipoId");
        List<Caracteristicas> caracteristicas = null;
        try {
            caracteristicas = caracteristicasDAO.listarPorTipo(Integer.parseInt(tipoId));
        } catch (SQLException ex) {
            Logger.getLogger(CaracteristicasServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(caracteristicas)); // Usa Gson o similar para convertir a JSON
    }
}
