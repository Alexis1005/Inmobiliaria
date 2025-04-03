package com.mycompany.inmobiliaria.controladores;

import com.mycompany.inmobiliaria.modelo.Caracteristicas;
import com.mycompany.inmobiliaria.modelo.dao.CaracteristicasDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CaracteristicasController extends HttpServlet {

    private CaracteristicasDAO caractDAO = new CaracteristicasDAO();
    private final String pagListar = "/vista/listar.jsp";
    private final String pagNuevo = "/vista/nuevo.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");

        switch (accion) {
            case "listar":
                listar(request, response);

                break;
            case "nuevo":
                nuevo(request, response);

                break;

            case "guardar":
                guardar(request, response);
                break;
            case "editar":
                editar(request, response);
                break;
            case "eliminar":
                eliminar(request, response);
                break;
            default:
                throw new AssertionError();

        }
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id_caracteristica"));

        int result = caractDAO.eliminar(id);

        if (result > 0) {
            request.getSession().setAttribute("success", "Caracteristica con id " + id + " eliminado");
        } else {
            request.getSession().setAttribute("error", "No se pudo eliminar la caracteristica");
        }
        response.sendRedirect("CaracteristicasController?accion=listar");
    }

    private void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Caracteristicas obj = new Caracteristicas();

        String idStr = request.getParameter("id_caracteristica");
        if (idStr != null && !idStr.isEmpty()) {
            obj.setId_caracteristica(Integer.parseInt(idStr));
        }

        // Obtener valores desde el formulario
        String nombre = request.getParameter("nombre");
        String nombreManual = request.getParameter("nombre_manual");

        // se usa en vez del select, si el usuario ingreso un valor manual
        if (nombreManual != null && !nombreManual.trim().isEmpty()) {
            nombre = nombreManual;
        }

        String detalle = request.getParameter("detalle");
        String detalleManual = request.getParameter("detalle_manual");

        if (detalleManual != null && !detalleManual.trim().isEmpty()) {
            detalle = detalleManual;
        }

        obj.setNombre(nombre);
        obj.setDetalle(detalle);

        int result;

        if (obj.getId_caracteristica() == 0) {
            result = caractDAO.registrar(obj);
        } else {
            result = caractDAO.editar(obj);
        }

        if (result > 0) {
            request.getSession().setAttribute("success", "Datos guardados correctamente");
            response.sendRedirect("CaracteristicasController?accion=listar");
        } else {
            request.getSession().setAttribute("error", "No se pudo guardar datos");
            request.setAttribute("caracteristica", obj);
            request.getRequestDispatcher(pagNuevo).forward(request, response);
        }
    }

    private void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id_caracteristica"));

        Caracteristicas obj = caractDAO.buscarPorId(id);

        if (obj != null) {
            request.setAttribute("caracteristica", obj);
            request.setAttribute("listaCaracteristicas", caractDAO.listarNombres());
            request.setAttribute("listaDetalles", caractDAO.listarDetalles());
            request.getRequestDispatcher(pagNuevo).forward(request, response);
        } else {
            request.getSession().setAttribute("error", "No se encontró la característica con el ID " + id);
            response.sendRedirect("CaracteristicasController?accion=listar");
        }
    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("caracteristica", new Caracteristicas());
        request.setAttribute("listaCaracteristicas", caractDAO.listarNombres());
        request.setAttribute("listaDetalles", caractDAO.listarDetalles());

        request.getRequestDispatcher(pagNuevo).forward(request, response);
    }

    protected void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");

        request.setAttribute("caracteristicas", caractDAO.listar());
        request.getRequestDispatcher(pagListar).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
