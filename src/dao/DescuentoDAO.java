package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Descuento;

public class DescuentoDAO {

    // Método para insertar un nuevo descuento
    public void insertar(Descuento descuento) {
        String query = "INSERT INTO Descuentos (nombre, descripcion, porcentaje, fechaInicio, fechaFin, activo) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, descuento.getNombre());
            stmt.setString(2, descuento.getDescripcion());
            stmt.setDouble(3, descuento.getPorcentajeDescuento());
            stmt.setDate(4, Date.valueOf(descuento.getFechaInicio()));
            stmt.setDate(5, Date.valueOf(descuento.getFechaFin()));
            stmt.setBoolean(6, descuento.isActivo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al agregar el descuento: " + e.getMessage());
        }
    }

    // Método para actualizar un descuento existente
    public void actualizar(Descuento descuento) {
        String query = "UPDATE Descuentos SET descripcion = ?, porcentaje = ?, fechaInicio = ?, fechaFin = ?, activo = ? WHERE nombre = ?";
        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, descuento.getDescripcion());
            stmt.setDouble(2, descuento.getPorcentajeDescuento());
            stmt.setDate(3, Date.valueOf(descuento.getFechaInicio()));
            stmt.setDate(4, Date.valueOf(descuento.getFechaFin()));
            stmt.setBoolean(5, descuento.isActivo());
            stmt.setString(6, descuento.getNombre());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar el descuento: " + e.getMessage());
        }
    }

    // Método para eliminar un descuento por nombre
    public void eliminar(String nombre) {
        String query = "DELETE FROM Descuentos WHERE nombre = ?";
        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar el descuento: " + e.getMessage());
        }
    }

    // Método para buscar un descuento por nombre
    public Descuento buscarPorNombre(String nombre) {
        String query = "SELECT * FROM Descuentos WHERE nombre = ?";
        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, nombre);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Descuento(
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("porcentaje"),
                        rs.getDate("fechaInicio").toLocalDate(),
                        rs.getDate("fechaFin").toLocalDate(),
                        rs.getBoolean("activo")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar el descuento: " + e.getMessage());
        }
        return null;
    }

    // Método para obtener todos los descuentos activos
    public List<Descuento> obtenerActivos() {
        List<Descuento> descuentos = new ArrayList<>();
        String query = "SELECT * FROM Descuentos WHERE activo = true";
        try (Connection conexion = ConexionDB.conectar();
             Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                descuentos.add(new Descuento(
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getDouble("porcentaje"),
                    rs.getDate("fechaInicio").toLocalDate(),
                    rs.getDate("fechaFin").toLocalDate(),
                    rs.getBoolean("activo")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener descuentos activos: " + e.getMessage());
        }
        return descuentos;
    }

    // Método para activar o desactivar un descuento
    public void activarDescuento(int idDescuento, boolean activo) {
        String query = "UPDATE Descuentos SET activo = ? WHERE idDescuento = ?";
        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setBoolean(1, activo);
            stmt.setInt(2, idDescuento);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar el estado del descuento: " + e.getMessage());
        }
    }
}