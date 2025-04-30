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
            String query = "{CALL InsertarEmpleado(?, ?, ?, ?, ?, ?, ?)}";
            try (CallableStatement stmt = conexion.prepareCall(query)) {
                stmt.setString(1, empleado.getNombre());
                stmt.setString(2, empleado.getApellido());
                stmt.setString(3, empleado.getDni());
                stmt.setString(4, empleado.getCargo());
                stmt.setDouble(5, empleado.getSaldo());
                stmt.setString(6, empleado.getTelefono());
                stmt.setString(7, empleado.getEmail());

                stmt.executeUpdate();
                // Obtener el ID generado por el procedimiento almacenado
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    empleado.setId(generatedKeys.getInt(1));
                }
                System.out.println("Empleado agregado con éxito. ID asignado: " + empleado.getId());
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
            String query = "{CALL ObtenerTodosEmpleados()}";
            try (CallableStatement stmt = conexion.prepareCall(query);
                 ResultSet rs = stmt.executeQuery()) {
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
            String query = "{CALL ObtenerEmpleadoPorId(?)}";
            try (CallableStatement stmt = conexion.prepareCall(query)) {
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
            String query = "{CALL ActualizarEmpleado(?, ?, ?, ?, ?, ?, ?, ?)}";
            try (CallableStatement stmt = conexion.prepareCall(query)) {
                stmt.setString(1, empleado.getNombre());
                stmt.setString(2, empleado.getApellido());
                stmt.setString(3, empleado.getDni());
                stmt.setString(4, empleado.getCargo());
                stmt.setDouble(5, empleado.getSaldo());
                stmt.setString(6, empleado.getTelefono());
                stmt.setString(7, empleado.getEmail());
                stmt.setInt(8, empleado.getId());
                stmt.executeUpdate();
                System.out.println("Empleado actualizado con éxito.");
            } catch (SQLException e) {
                System.out.println("Error al actualizar empleado: " + e.getMessage());
            } 
        }
    }

    // Método para eliminar un empleado por su ID (DELETE)
    public static void eliminarEmpleado(int id) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "{CALL EliminarEmpleado(?)}";
            try (CallableStatement stmt = conexion.prepareCall(query)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
                System.out.println("Empleado eliminado con éxito.");
            } catch (SQLException e) {
                System.out.println("Error al eliminar empleado: " + e.getMessage());
            }
        }
    }
}

