/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inmobiliaria.controlador;

import com.mycompany.inmobiliaria.modelo.Agente;
import com.mycompany.inmobiliaria.modelo.Propiedades;
import com.mycompany.inmobiliaria.modelo.Propiedades.ModalidadPropiedad;
import com.mycompany.inmobiliaria.modelo.dao.AgenteDAO;
import com.mycompany.inmobiliaria.modelo.dao.PropiedadesDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rubén
 */
public class mainclass {
    public static void main(String[] args) {
        System.out.println("Iniciando la aplicación..."); // Mensaje de depuración

        // Crear una instancia de AgenteDAO
        AgenteDAO agenteDAO = new AgenteDAO();
        

        // Obtener la lista de agentes
        ArrayList<Agente> lista = agenteDAO.ListarTodos();
        

        // Verificar si la lista no está vacía
        if (lista != null && !lista.isEmpty()) {
            System.out.println("=== Lista de Agentes ===");
            for (Agente agente : lista) {
                System.out.println("ID: " + agente.getId_agente());
                System.out.println("Nombre: " + agente.getNombre());
                System.out.println("Teléfono: " + agente.getTelefono());
                System.out.println("Email: " + agente.getEmail());
                System.out.println("-----------------------------");
            }
        } else {
            System.out.println("No se encontraron agentes en la base de datos.");
        }

        System.out.println("Aplicación finalizada."); // Mensaje de depuración
          // Listar todas las propiedades
        PropiedadesDAO propiedadesDAO = new PropiedadesDAO();

        // Obtener la lista de propiedades
        List<Propiedades> propiedades = propiedadesDAO.listarTodas();

        // Verificar si la lista de propiedades no está vacía
        if (propiedades != null && !propiedades.isEmpty()) {
            System.out.println("=== Lista de Propiedades ===");
            for (Propiedades propiedad : propiedades) {
                System.out.println(propiedad); // Utiliza el método toString() de Propiedades
            }
        } else {
            System.out.println("No se encontraron propiedades en la base de datos.");
        }

        System.out.println("Aplicación finalizada."); // Mensaje de depuración
    
    // Filtrar propiedades por modalidad (por ejemplo, VENTA)
        List<Propiedades> propiedadesEnVenta = propiedadesDAO.filtrarPorModalidad(ModalidadPropiedad.venta);

        // Verificar si la lista no está vacía
        if (propiedadesEnVenta != null && !propiedadesEnVenta.isEmpty()) {
            System.out.println("=== Propiedades en Venta ===");
            for (Propiedades propiedad : propiedadesEnVenta) {
                System.out.println(propiedad); // Utiliza el método toString() de Propiedades
            }
        } else {
            System.out.println("No se encontraron propiedades en venta.");
        }

        System.out.println("Aplicación finalizada.");
}}
    
     
    
    
