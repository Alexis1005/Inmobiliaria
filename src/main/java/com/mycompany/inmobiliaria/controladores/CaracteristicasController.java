package com.mycompany.inmobiliaria.controladores;

import com.mycompany.inmobiliaria.modelo.Caracteristicas;
import com.mycompany.inmobiliaria.modelo.dao.CaracteristicasDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

        if (accion == null || accion.trim().isEmpty()) {
            request.getSession().setAttribute("error", "Acción no especificada.");
            response.sendRedirect(pagListar);
            return;
        }

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
                request.getSession().setAttribute("error", "Acción inválida: " + accion);
                response.sendRedirect(pagListar);
        }
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id_caracteristica"));

            int result = caractDAO.eliminar(id);

            if (result > 0) {
                request.getSession().setAttribute("success", "Característica con ID " + id + " eliminada correctamente.");
            } else {
                request.getSession().setAttribute("error", "No se pudo eliminar la característica.");
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("error", "ID inválido.");
        }
        response.sendRedirect(pagListar);
    }

    private void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Caracteristicas obj = new Caracteristicas();

            String idStr = request.getParameter("id_caracteristica");
            if (idStr != null && !idStr.isEmpty()) {
                obj.setId_caracteristica(Integer.parseInt(idStr));
            }

            // Obtener valores desde el formulario
            String nombre = request.getParameter("nombre");
            String nombreManual = request.getParameter("nombre_manual");

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
                request.getSession().setAttribute("success", "Datos guardados correctamente.");
                response.sendRedirect(pagListar);
            } else {
                request.getSession().setAttribute("error", "No se pudieron guardar los datos.");
                request.setAttribute("caracteristica", obj);
                request.getRequestDispatcher(pagNuevo).forward(request, response);
            }
        } catch (Exception e) {
            request.getSession().setAttribute("error", "Error al guardar los datos: " + e.getMessage());
            response.sendRedirect(pagListar);
        }
    }

    private void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id_caracteristica"));

            Caracteristicas obj = caractDAO.buscarPorId(id);

            if (obj != null) {
                request.setAttribute("caracteristica", obj);
                request.setAttribute("listaCaracteristicas", caractDAO.listarNombres());
                request.setAttribute("listaDetalles", caractDAO.listarDetalles());
                request.getRequestDispatcher(pagNuevo).forward(request, response);
            } else {
                request.getSession().setAttribute("error", "No se encontró la característica con ID " + id);
                response.sendRedirect(pagListar);
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("error", "ID inválido.");
            response.sendRedirect(pagListar);
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
        try {
            List<Caracteristicas> listaCaracteristicas = caractDAO.listar();
            if (listaCaracteristicas == null) {
                listaCaracteristicas = new ArrayList<>();
            }

            request.setAttribute("caracteristicas", listaCaracteristicas);
            request.getRequestDispatcher(pagListar).forward(request, response);
        } catch (Exception e) {
            request.getSession().setAttribute("error", "Error al listar características: " + e.getMessage());
            response.sendRedirect(pagListar);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controlador de Características";
    }
}
