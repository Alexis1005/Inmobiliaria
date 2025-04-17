package com.mycompany.inmobiliaria.controlador;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/sobreNosotros")
public class SobreNosotrosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirecciona al archivo JSP ubicado dentro de WEB-INF
        request.getRequestDispatcher("/WEB-INF/views/sobreNosotros.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Si alguien envía POST, lo tratamos igual que GET (opcional)
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet que redirige a la página sobreNosotros.jsp";
    }
}
