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

        Connection conexion = ConexionDB.conectar();

        if(conexion != null) {
            String query = "INSERT INTO User (username, password) VALUES (?, ?)";
            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, username);
                ps.setString(2, password);
                ps.executeUpdate();

            } catch (SQLException e) {
                System.err.println("Error al insertar el usuario: " + e.getMessage());
                } 
                
        }
    }

    public void eliminarUser(User user) {
        int id = user.getUserId();
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            String query = "DELETE FROM User WHERE id = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setInt(1, id);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al eliminar el usuario: " + e.getMessage());
            }
        }
    }

    public ArrayList<User> listarUsers() {
        Connection conexion = ConexionDB.conectar();
        ArrayList<User> users = new ArrayList<>();

        if (conexion != null) {
            String query = "SELECT * FROM User";

            try (Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String role = rs.getString("role");

                    User user = new User(username, password, role);
                    user.setId(id);
                    users.add(user);
                }
            } catch (SQLException e) {
                System.err.println("Error al listar los usuarios: " + e.getMessage());
            }
            return users;
        }
        return null;
    }

    public User getUserId(int id) {
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
                        
                        user = new User(username, password, "user");
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al obtener el usuario por ID: " + e.getMessage());
            }
            return user;
        }
        return null;
    }

    public User getUsername(String username) {
        Connection conexion = ConexionDB.conectar();
        User user = null;

        if (conexion != null) {
            String query = "SELECT * FROM User WHERE username = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, username);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String username1 = rs.getString("username");
                        String password = rs.getString("password");
                        
                        user = new User(username1, password, "user");
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al obtener el usuario por Username: " + e.getMessage());
            }
            return user;
        }
        return null;
    }

    public void actualizarUsernameUser (int id, String username) {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            String query = "UPDATE User SET username = ? WHERE id = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, username);
                ps.setInt(2, id);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al actualizar el nombre de usuario: " + e.getMessage());
            }
        }
    }

    public void actualizarPasswordUser (int id, String password) {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            String query = "UPDATE User SET password = ? WHERE id = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, password);
                ps.setInt(2, id);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al actualizar la contraseña: " + e.getMessage());
            }
        }
    }

    public boolean existeId(int id) {
        Connection conexion = ConexionDB.conectar();
        boolean existe = false;

        if (conexion != null) {
            String query = "SELECT * FROM User WHERE id = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    existe = rs.next();

                }
            } catch (SQLException e) {
                System.err.println("Error al verificar la existencia del ID: " + e.getMessage());
            }
        }
        return existe;
    }

    public boolean existeUsername(String username) {
        Connection conexion = ConexionDB.conectar();
        boolean existe = false;

        if (conexion != null) {
            String query = "SELECT * FROM User WHERE username = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, username);
                try (ResultSet rs = ps.executeQuery()) {
                    existe = rs.next();
                }
            } catch (SQLException e) {
                System.err.println("Error al verificar la existencia del nombre de usuario: " + e.getMessage());
            }
        }
        return existe;
    }

    public User inicioSesion(String usuarioEntrada, String passwdEntrada) {
        Connection conexion = ConexionDB.conectar();
        User user = null;

        if (conexion != null) {
            String query = "SELECT * FROM User WHERE username = ? AND password = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, usuarioEntrada);
                ps.setString(2, passwdEntrada);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String username = rs.getString("username");
                        String password = rs.getString("password");
                        user = new User(username, password, "user");
                        return user;
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al iniciar sesión: " + e.getMessage());
            }
        }
        return null;
    }

    public void modificarRol(User user) {
        int id = user.getUserId();
        String rol = user.getRole();
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            String query = "UPDATE User SET role = ? WHERE id = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, rol);
                ps.setInt(2, id);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al modificar el rol del usuario: " + e.getMessage());
            }
        }
    }


    
}

