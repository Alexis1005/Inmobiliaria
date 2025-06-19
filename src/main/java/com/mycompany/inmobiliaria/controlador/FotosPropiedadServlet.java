package com.mycompany.inmobiliaria.controlador;

import com.google.gson.Gson;
import com.mycompany.inmobiliaria.modelo.FotosPropiedad;
import com.mycompany.inmobiliaria.modelo.dao.FotosPropiedadDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/fotosPropiedad")
public class FotosPropiedadServlet extends HttpServlet {
    private FotosPropiedadDAO fotosDAO = new FotosPropiedadDAO();
    private static final Logger logger = Logger.getLogger(FotosPropiedadServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idPropiedadStr = request.getParameter("idPropiedad");
            if (idPropiedadStr == null || idPropiedadStr.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El parámetro idPropiedad es requerido");
                return;
            }

            int idPropiedad = Integer.parseInt(idPropiedadStr);
            ArrayList<FotosPropiedad> fotos = fotosDAO.obtenerFotosPorPropiedad(idPropiedad);
            response.setContentType("application/json");
            response.getWriter().write(new Gson().toJson(fotos));
        } catch (SQLException | NumberFormatException e) {
            logger.log(Level.SEVERE, "Error al obtener fotos", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener fotos");
        }
    }
}