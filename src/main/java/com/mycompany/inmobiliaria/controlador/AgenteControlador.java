package com.mycompany.inmobiliaria.controlador;

import com.mycompany.inmobiliaria.modelo.dao.AgenteDAO;
import com.mycompany.inmobiliaria.modelo.Agente;
import java.util.ArrayList;

public class AgenteControlador {

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
    }
}