package com.mycompany.inmobiliaria.controlador;

import com.mycompany.inmobiliaria.modelo.dao.AgenteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private AgenteDAO agenteDAO = new AgenteDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // se capturan los datos que vienen del form en login.jsp
        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");

        try {
            // valida si usuario y contraseña existen y son correctos
            if (agenteDAO.validarAgente(usuario, clave)) { 
                // si es valido crea una sesion
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario);
                response.sendRedirect("index.jsp"); // si el login es exitoso va al menu principal
            } else {
                response.sendRedirect("login.jsp?error=1"); // si es incorrecto redirige al login y muestra "datos incorrectos"
            }
        } catch (SQLException e) {
            throw new ServletException("Error validando el login", e);
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
