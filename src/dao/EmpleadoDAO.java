package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Empleado;

public class EmpleadoDAO {

    // Método para insertar un nuevo empleado (CREATE)
    public static void agregarEmpleado(Empleado empleado) {
        try {
            Connection conexion = ConexionDB.conectar();
            if (conexion != null) {
                String query = "INSERT INTO Empleados (nombre, apellido, dni, cargo, salario, telefono, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stmt = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, empleado.getNombre());
                stmt.setString(2, empleado.getApellido());
                stmt.setString(3, empleado.getDni());
                stmt.setString(4, empleado.getCargo());
                stmt.setDouble(5, empleado.getSalario());
                stmt.setString(6, empleado.getTelefono());
                stmt.setString(7, empleado.getEmail());
    
                int filasInsertadas = stmt.executeUpdate();
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (filasInsertadas > 0) {
                    if (generatedKeys.next()) {
                        empleado.setId(generatedKeys.getInt(1));
                    }
                    System.out.println("Empleado agregado con éxito. ID asignado: " + empleado.getId());
                } else {
                    System.out.println("No se pudo agregar el empleado.");
                }
            } else {
                System.err.println("Error al conectar a la base de datos.");
            }
        } catch (SQLException e) {
            System.err.println("Error al agregar empleado: " + e.getMessage());
        } 
    }
    public static List<Empleado> obtenerTodosEmpleados() {
        try {
            List<Empleado> listaEmpleados = new ArrayList<>();
            Connection conexion = ConexionDB.conectar();
            if (conexion != null) {
                String query = "SELECT idEmpleado, nombre, apellido, dni, cargo, salario, telefono, email FROM Empleados";
                Statement stmt = conexion.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Empleado empleado = new Empleado(
                            rs.getInt("id"),
                            rs.getString("Nombre"),
                            rs.getString("apellido"),
                            rs.getString("dni"),
                            rs.getString("cargo"),
                            rs.getDouble("salario"),
                            rs.getString("telefono"),
                            rs.getString("email")
                    );
                    listaEmpleados.add(empleado);
                }
                return listaEmpleados;
            } else {
                System.err.println("Error al conectar a la base de datos.");
                return listaEmpleados; // Devuelve la lista vacía en caso de error
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los empleados: " + e.getMessage());
            return new ArrayList<>(); // Devuelve una nueva lista vacía en caso de error
        }
    }
    
    public static Empleado obtenerEmpleadoPorId(int id) {
        try {
            Connection conexion = ConexionDB.conectar();
            if (conexion != null) {
                String query = "SELECT id, nombre, apellido, dni, cargo, salario, telefono, email FROM Empleado WHERE id = ?";
                PreparedStatement stmt = conexion.prepareStatement(query);
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    Empleado empleado = new Empleado(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("dni"),
                            rs.getString("cargo"),
                            rs.getDouble("salario"),
                            rs.getString("telefono"),
                            rs.getString("email")
                    );
                    return empleado;
                }
            } else {
                System.err.println("Error al conectar a la base de datos.");
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener empleado por ID: " + e.getMessage());
            return null;
        }
        return null;
    }
    
    public static void actualizarEmpleado(Empleado empleado) {
        try {
            Connection conexion = ConexionDB.conectar();
            if (conexion != null) {
                String query = "UPDATE Empleado SET Nombre = ?, apellido = ?, dni = ?, cargo = ?, salario = ?, telefono = ?, email = ? WHERE id = ?";
                PreparedStatement stmt = conexion.prepareStatement(query);
                stmt.setString(1, empleado.getNombre());
                stmt.setString(2, empleado.getApellido());
                stmt.setString(3, empleado.getDni());
                stmt.setString(4, empleado.getCargo());
                stmt.setDouble(5, empleado.getSalario());
                stmt.setString(6, empleado.getTelefono());
                stmt.setString(7, empleado.getEmail());
                stmt.setInt(8, empleado.getId());
    
                int filasActualizadas = stmt.executeUpdate();
                if (filasActualizadas > 0) {
                    System.out.println("Empleado actualizado con éxito.");
                } else {
                    System.out.println("No se encontró el empleado para actualizar.");
                }
            } else {
                System.err.println("Error al conectar a la base de datos.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar empleado: " + e.getMessage());
        }
    }
    
    public static void eliminarEmpleado(int id) {
        try {
            Connection conexion = ConexionDB.conectar();
            if (conexion != null) {
                String query = "DELETE FROM Empleado WHERE id = ?";
                PreparedStatement stmt = conexion.prepareStatement(query);
                stmt.setInt(1, id);
                int filasEliminadas = stmt.executeUpdate();
                if (filasEliminadas > 0) {
                    System.out.println("Empleado eliminado con éxito.");
                } else {
                    System.out.println("No se encontró el empleado para eliminar.");
                }
            } else {
                System.err.println("Error al conectar a la base de datos.");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar empleado: " + e.getMessage());
        }
    }
}  

