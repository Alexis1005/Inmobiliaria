<<<<<<< OURS
package com.mycompany.inmobiliaria.resources.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static final String username = "root";
    public static final String password = "rocco0505";
    public static final String database = "inmobiliariagrok";
    public static final String url = "jdbc:mysql://localhost:3306/" + database;

    public static Connection getConnection() {
        Connection cn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(url, username, password);
            System.out.println("Conexión exitosa!");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Driver no encontrado - " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error: Fallo en la conexión a la base de datos - " + e.getMessage());
            e.printStackTrace();
        }
        return cn;
    }
}
=======

package com.mycompany.inmobiliaria.resources.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
        public static final String username = "root";
        public static final String password = "rocco0505";
        public static final String database = "inmobiliaria";
        public static final String url = "jdbc:mysql://localhost:3306/"+database;
        
        public static Connection getConnection(){
            Connection cn = null;
            
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                cn = DriverManager.getConnection(url,username,password);
                System.out.println("Conexion exitosa!");
            } catch (ClassNotFoundException | SQLException ex) {
            }
            return cn;
        }    
}
>>>>>>> THEIRS
