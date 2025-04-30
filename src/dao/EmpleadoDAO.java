package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Empleado;


public class EmpleadoDAO {

    // Método para insertar un nuevo empleado (CREATE)
    public static void agregarEmpleado(Empleado empleado) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "INSERT INTO empleados (nombre, apellido, dni, cargo, salario, telefono, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, empleado.getNombre());
                stmt.setString(2, empleado.getApellido());
                stmt.setString(3, empleado.getDni());
                stmt.setString(4, empleado.getCargo());
                stmt.setDouble(5, empleado.getSaldo());
                stmt.setString(6, empleado.getTelefono());
                stmt.setString(7, empleado.getEmail());

                int filasInsertadas = stmt.executeUpdate();
                if (filasInsertadas > 0) {
                    ResultSet generatedKeys = stmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        empleado.setId(generatedKeys.getInt(1));
                    }
                    System.out.println("Empleado agregado con éxito. ID asignado: " + empleado.getId());
                }
            } catch (SQLException e) {
                System.out.println("Error al agregar empleado: " + e.getMessage());
            } 
        }
    }

    // Método para obtener todos los empleados (READ - ALL)
    public static List<Empleado> obtenerTodosEmpleados() {
        List<Empleado> listaEmpleados = new ArrayList<>();
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "SELECT id, nombre, apellido, dni, puesto, salario, telefono, email FROM empleados";
            try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    Empleado empleado = new Empleado(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("dni"),
                            rs.getString("puesto"),
                            rs.getDouble("salario"),
                            rs.getString("telefono"),
                            rs.getString("email")
                    );
                    listaEmpleados.add(empleado);
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener todos los empleados: " + e.getMessage());
            }
        }
        return listaEmpleados;
    }

    // Método para obtener un empleado por su ID (READ - ONE)
    public static Empleado obtenerEmpleadoPorId(int id) {
        Empleado empleado = null;
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "SELECT id, nombre, apellido, dni, puesto, salario, telefono, email FROM empleados WHERE id = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query);) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        empleado = new Empleado(
                                rs.getInt("id"),
                                rs.getString("nombre"),
                                rs.getString("apellido"),
                                rs.getString("dni"),
                                rs.getString("puesto"),
                                rs.getDouble("salario"),
                                rs.getString("telefono"),
                                rs.getString("email")
                        );
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener empleado por ID: " + e.getMessage());
            } 
        }
        return empleado;
    }

    // Método para actualizar la información de un empleado (UPDATE)
    public static void actualizarEmpleado(Empleado empleado) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "UPDATE empleados SET nombre = ?, apellido = ?, dni = ?, puesto = ?, salario = ?, telefono = ?, email = ? WHERE id = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, empleado.getNombre());
                stmt.setString(2, empleado.getApellido());
                stmt.setString(3, empleado.getDni());
                stmt.setString(4, empleado.getCargo());
                stmt.setDouble(5, empleado.getSaldo());
                stmt.setString(6, empleado.getTelefono());
                stmt.setString(7, empleado.getEmail());
                stmt.setInt(8, empleado.getId());

                int filasActualizadas = stmt.executeUpdate();
                if (filasActualizadas > 0) {
                    System.out.println("Empleado actualizado con éxito.");
                }
            } catch (SQLException e) {
                System.out.println("Error al actualizar empleado: " + e.getMessage());
            } 
        }
    }

    // Método para eliminar un empleado por su ID (DELETE)
    public static void eliminarEmpleado(int id) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "DELETE FROM empleados WHERE id = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setInt(1, id);
                int filasEliminadas = stmt.executeUpdate();
                if (filasEliminadas > 0) {
                    System.out.println("Empleado eliminado con éxito.");
                }
            } catch (SQLException e) {
                System.out.println("Error al eliminar empleado: " + e.getMessage());
            }
        }
    }
}
