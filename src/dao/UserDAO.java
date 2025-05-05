package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.User;

public class UserDAO {
    public void insertarUser(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        boolean admin = user.isAdmin();

        Connection conexion = ConexionDB.conectar();

        if(conexion != null) {
            String query = "INSERT INTO User (username, password, admin) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setBoolean(3, admin);
                ps.executeUpdate();

            } catch (SQLException e) {
                System.err.println("Error al insertar el usuario: " + e.getMessage());
                } 
                
        }
    }

    public User getId(int id) {
        Connection conexion = ConexionDB.conectar();
        User user = null;

        if (conexion != null) {
            String query = "SELECT * FROM User WHERE id = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String username = rs.getString("username");
                        String password = rs.getString("password");
                        boolean admin = rs.getBoolean("admin");
                        
                        user = new User(id, username, password, admin);
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al obtener el usuario por ID: " + e.getMessage());
            }
            return user;
        }
        return null;
    }

    
}

