package com.mycompany.inmobiliaria.controladores;

import com.mycompany.inmobiliaria.modelo.Caracteristicas;
import com.mycompany.inmobiliaria.modelo.Propiedades;
import com.mycompany.inmobiliaria.modelo.PropiedadesCaracteristicas;
import com.mycompany.inmobiliaria.modelo.TiposPropiedad;
import com.mycompany.inmobiliaria.modelo.dao.CaracteristicasDAO;
import com.mycompany.inmobiliaria.modelo.dao.PropiedadesCaracteristicasDAO;
import com.mycompany.inmobiliaria.modelo.dao.PropiedadesDAO;
import com.mycompany.inmobiliaria.modelo.dao.TiposPropiedadDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexis
 */
public class PropiedadesController extends HttpServlet {

    private final PropiedadesDAO propiedadDAO = new PropiedadesDAO();
    private final TiposPropiedadDAO tiposPropiedadDAO = new TiposPropiedadDAO();
    private final CaracteristicasDAO caracteristicasDAO = new CaracteristicasDAO();
    private final PropiedadesCaracteristicasDAO propiedadesCaracteristicaDAO = new PropiedadesCaracteristicasDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");
        HttpSession session = request.getSession();
        List<Caracteristicas> caracteristicasTemp = (List<Caracteristicas>) session.getAttribute("caracteristicasTemp");
        if (caracteristicasTemp == null) {
            caracteristicasTemp = new ArrayList<>();
            session.setAttribute("caracteristicasTemp", caracteristicasTemp);
        }

        switch (accion) {
            case "listar":
                listar(request, response);
                break;
            case "insertar":
                insertar(request, response, caracteristicasTemp);
                break;
            case "mostrarFormulario":
                mostrarFormulario(request, response, caracteristicasTemp);
                break;
            case "agregarCaracteristica":
                agregarCaracteristica(request, response, caracteristicasTemp);
                break;
            case "verDetalle":
                verDetalle(request, response);
                break;
            default:
                throw new AssertionError("Acción no válida: " + accion);
        }
    }

    private void cargarListas(HttpServletRequest request) {
        // Cargar tipos de propiedades desde la base de datos
        ArrayList<TiposPropiedad> tiposPropiedades = tiposPropiedadDAO.ListarTiposPropiedades();
        System.out.println("Tipos de propiedades cargados: " + tiposPropiedades.size());

        // Lista estática de estados
        ArrayList<String> estados = new ArrayList<>(Arrays.asList("Disponible", "Reservado", "Vendido"));
        System.out.println("Estados cargados: " + estados.size());

        // Lista estática de modalidades
        ArrayList<String> modalidades = new ArrayList<>(Arrays.asList("Venta", "Alquiler", "Arrendamiento"));
        System.out.println("Modalidades cargadas: " + modalidades.size());

        // Agregar al request para que estén disponibles en el JSP
        request.setAttribute("tiposPropiedades", tiposPropiedades);
        request.setAttribute("estados", estados);
        request.setAttribute("modalidades", modalidades);
    }

    protected void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Propiedades> propiedades = propiedadDAO.listar(null, null);
        System.out.println("Propiedades en controlador: " + propiedades);
        request.setAttribute("propiedades", propiedades);
        request.getRequestDispatcher("/vistas/home.jsp").forward(request, response);
    }

    protected void mostrarFormulario(HttpServletRequest request, HttpServletResponse response, List<Caracteristicas> caracteristicasTemp)
            throws ServletException, IOException {
        request.setAttribute("caracteristicas", caracteristicasTemp);
        cargarListas(request);  // Cargar listas para los selects
        System.out.println("Redirigiendo a adminAgregar.jsp...");
        request.getRequestDispatcher("/vistas/adminAgregar.jsp").forward(request, response);
    }

    protected void agregarCaracteristica(HttpServletRequest request, HttpServletResponse response, List<Caracteristicas> caracteristicasTemp)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String detalle = request.getParameter("detalle");
        if (nombre != null && !nombre.isEmpty()) {
            Caracteristicas caracteristica = new Caracteristicas(nombre, detalle);
            caracteristicasTemp.add(caracteristica);
        }

        // Verificar si la solicitud es AJAX
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            // Se establece la lista actualizada en el request
            request.setAttribute("caracteristicas", caracteristicasTemp);
            // Enviar únicamente el fragmento de la lista (listar.jsp)
            request.getRequestDispatcher("/vista/listar.jsp").forward(request, response);
        } else {
            // Comportamiento tradicional: redirigir a mostrarFormulario
            response.sendRedirect("propiedadesController?accion=mostrarFormulario");
        }
    }



protected void insertar(HttpServletRequest request, HttpServletResponse response, List<Caracteristicas> caracteristicasTemp)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        try {
            // Obtener parámetros del formulario
            int idTipo = Integer.parseInt(request.getParameter("id_tipo"));
            int idAgente = Integer.parseInt(request.getParameter("id_agente"));
            String direccion = request.getParameter("direccion");
            double precio = Double.parseDouble(request.getParameter("precio"));
            String descripcion = request.getParameter("descripcion");
            String estado = request.getParameter("estado");
            String modalidad = request.getParameter("modalidad");
            String descripcion_general = request.getParameter("descripcion_general");

            // Crear objeto Propiedades
            Propiedades nuevaPropiedad = new Propiedades();
            nuevaPropiedad.setId_tipo(idTipo);
            nuevaPropiedad.setId_agente(idAgente);
            nuevaPropiedad.setDireccion(direccion);
            nuevaPropiedad.setPrecio(precio);
            nuevaPropiedad.setDescripcion(descripcion);
            nuevaPropiedad.setEstado(estado);
            nuevaPropiedad.setModalidad(modalidad);
            nuevaPropiedad.setDescripcion_general(descripcion_general);

            // Insertar propiedad y obtener su ID
            int id_propiedad = propiedadDAO.insertar(nuevaPropiedad);
            if (id_propiedad <= 0) {
                throw new ServletException("Error al insertar la propiedad.");
            }

            // Insertar características y relacionarlas con la propiedad
            for (Caracteristicas c : caracteristicasTemp) {
                int id_caracteristica = caracteristicasDAO.insertar(c);  // Asumiendo que insertar devuelve int
                if (id_caracteristica <= 0) {
                    throw new ServletException("Error al insertar la característica.");
                }

                // Crear relación propiedad-característica
                PropiedadesCaracteristicas pc = new PropiedadesCaracteristicas(id_propiedad, id_caracteristica);
                propiedadesCaracteristicaDAO.crear(pc);  // Insertar relación
            }

            // Limpiar la lista temporal
            caracteristicasTemp.clear();
            session.setAttribute("caracteristicasTemp", caracteristicasTemp);

            // Redirigir después de éxito
            response.sendRedirect("propiedadesController?accion=listar");

        } catch (NumberFormatException e) {
            System.out.println("Error de formato en los datos numéricos: " + e.getMessage());
            cargarListas(request);
            request.setAttribute("mensaje", "Error: Los datos numéricos (tipo, agente, precio) no tienen el formato correcto.");
            request.getRequestDispatcher("/vistas/adminAgregar.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            System.out.println("Error al insertar: " + e.getMessage());
            cargarListas(request);
            request.setAttribute("mensaje", "Error inesperado al procesar la solicitud.");
            request.getRequestDispatcher("/vistas/adminAgregar.jsp").forward(request, response);
        }
    }

    protected void verDetalle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idPropiedad = Integer.parseInt(request.getParameter("id"));
        Propiedades propiedad = propiedadDAO.obtenerPorId(idPropiedad);
        request.setAttribute("propiedad", propiedad);
        request.getRequestDispatcher("/vistas/propiedad.jsp").forward(request, response);
    }

    @Override
public String getServletInfo() {
        return "Controlador de Propiedades";
    }

    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

} catch (SQLException ex) {
            Logger.getLogger(PropiedadesController.class  

.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

} catch (SQLException ex) {
            Logger.getLogger(PropiedadesController.class  

.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
