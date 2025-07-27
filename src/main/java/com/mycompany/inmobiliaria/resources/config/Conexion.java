package com.mycompany.inmobiliaria.resources.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static final String username = "jmoren_morenogaleano";
    public static final String password = "H4$#0H$5rnXTCc6s";
    public static final String database = "jmoren_inmobiliaria";
    public static final String url = "jdbc:mariadb://localhost:3306/" + database;
    
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Conectando a: " + url + " con usuario: " + username);
            Connection cn = DriverManager.getConnection(url, username, password);
            System.out.println("¡Conexión exitosa!");
            return cn;
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MariaDB no encontrado: " + e.getMessage(), e);
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
            throw e;
        }
    }
}
