package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Cliente;
import model.User;

public class ClienteDAO {
    public void insertarCliente(Cliente cliente) {
        String nombre = cliente.getNombre();
        String apellido = cliente.getApellido();
        String email = cliente.getEmail();
        String telefono = cliente.getTelefono();
        String direccion = cliente.getDireccion();

        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            String query ="INSERT INTO Cliente (nombre, apellido, telefono, email, direccion) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, nombre);
                ps.setString(2, apellido);
                ps.setString(3, telefono);
                ps.setString(4, email);
                ps.setString(5, direccion);
                ps.executeUpdate();

            } catch (SQLException e) {
                System.err.println("Error al insertar el cliente: " + e.getMessage());
            }
        }
    }

    public void eliminarCliente(User user) {
        int id = user.getUserId();
        
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            String query = "DELETE FROM Cliente WHERE idCliente = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setInt(1, id);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al eliminar el cliente: " + e.getMessage());
            }
        }
        
    }

    public ArrayList<Cliente> listarClientes() {
        Connection conexion = ConexionDB.conectar();
        ArrayList<Cliente> clientes = new ArrayList<>();

        if (conexion != null) {
            String query = "SELECT * FROM Cliente";

            try (Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                    String email = rs.getString("email");
                    String telefono = rs.getString("telefono");

                    Cliente cliente = new Cliente(nombre, apellido, email, telefono);
                    clientes.add(cliente);
                }
            } catch (SQLException e) {
                System.err.println("Error al listar los clientes: " + e.getMessage());
            }
            return clientes;
        }
        return null;
    }

    public Cliente getClienteId(int id) {
        Connection conexion = ConexionDB.conectar();
        Cliente cliente = null;

        if (conexion != null) {
            String query = "SELECT * FROM Cliente WHERE idCliente = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String nombre = rs.getString("nombre");
                        String apellido = rs.getString("apellido");
                        String email = rs.getString("email");
                        String telefono = rs.getString("telefono");

                        cliente = new Cliente(nombre, apellido, email, telefono);
                        
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al obtener el cliente por ID: " + e.getMessage());
            }
            return cliente;
        }
        return null;
    }

    public void actualizarNombre(int id, String nombre) {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            String query = "UPDATE Cliente SET nombre = ? WHERE id = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, nombre);
                ps.setInt(2, id);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al actualizar el nombre del cliente: " + e.getMessage());
            }
        }
    }

    public void actualizarApellido(int id, String apellido) {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            String query = "UPDATE Cliente SET apellido = ? WHERE id = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, apellido);
                ps.setInt(2, id);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al actualizar el apellido del cliente: " + e.getMessage());
            }
        }
    }

    public void actualizarEmail(int id, String email) {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            String query = "UPDATE Cliente SET email = ? WHERE id = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, email);
                ps.setInt(2, id);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al actualizar el email del cliente: " + e.getMessage());
            }
        }
    }

    public void actualizarTelefono(int id, String telefono) {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            String query = "UPDATE Cliente SET telefono = ? WHERE id = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, telefono);
                ps.setInt(2, id);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al actualizar el teléfono del cliente: " + e.getMessage());
            }
        }
    }

    public void actualizarDireccion(int id, String direccion) {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            String query = "UPDATE Cliente SET direccion = ? WHERE idCliente = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, direccion);
                ps.setInt(2, id);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al actualizar la dirección del cliente: " + e.getMessage());
            }
        }
    }

    public Cliente getClienteEmail(String email) {
        Connection conexion = ConexionDB.conectar();
        Cliente cliente = null;

        if (conexion != null) {
            String query = "SELECT * FROM Cliente WHERE email =  ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, email);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String nombre = rs.getString("nombre");
                        String apellido = rs.getString("apellido");
                        String telefono = rs.getString("telefono");

                        cliente = new Cliente(nombre, apellido, email, telefono);
                        
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al obtener el cliente por ID: " + e.getMessage());
            }
            return cliente;
        }
        return null;
    }




}
