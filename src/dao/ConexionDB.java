package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import utils.EnvLoader;

public class ConexionDB {
    // Cargar las variables de entorno desde el archivo .env
    private static final String URL = EnvLoader.get("DB_URL");
    private static final String USUARIO = EnvLoader.get("DB_USER");
    private static final String CONTRASENA = EnvLoader.get("DB_PASSWORD");

    // Método para establecer la conexión con la base de datos
    public static Connection conectar() {
        try {
            // Establecer la conexión con la base de datos
            return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            return null;
        }
    }
}