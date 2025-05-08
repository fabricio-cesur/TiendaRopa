package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Descuento;


public class DescuentoDAO {
    public void insertar(Descuento descuento) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "INSERT INTO descuento (nombre, descripcion, porcentajeDescuento, fechaInicio, fechaFin) VALUES (" + descuento.getNombre() + ", " + descuento.getDescripcion() + ", " + descuento.getPorcentajeDescuento() + ", " + descuento.getFechaInicio() + ", " + descuento.getFechaFin() + ")";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, descuento.getNombre()); 
                stmt.setString(2, descuento.getDescripcion()); 
                stmt.setDouble(3, descuento.getPorcentajeDescuento());
                stmt.setDate(4, Date.valueOf(descuento.getFechaInicio()));
                stmt.setDate(5, Date.valueOf(descuento.getFechaFin()));
                stmt.executeUpdate(); 
                System.out.println("Descuento agregado correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al agregar el descuento: " + e.getMessage());
            }
        }
    }

    public void actualizar(Descuento descuento) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "UPDATE descuento SET descripcion = ?, porcentajeDescuento = ?, fechaInicio = ?, fechaFin = ? WHERE nombre = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, descuento.getDescripcion());
                stmt.setDouble(2, descuento.getPorcentajeDescuento());
                stmt.setDate(4, Date.valueOf(descuento.getFechaInicio()));
                stmt.setDate(5, Date.valueOf(descuento.getFechaFin()));

                System.out.println("Datos actualizados para el descuento llamado: " + descuento.getNombre());
            } catch (SQLException e) {
                System.out.println("Error al actualizar el descuento con nombre: " + e.getMessage());
            }

        }
    }

   
    public void eliminar(String nombre) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "DELETE FROM descuento WHERE nombre = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, nombre); 
                stmt.executeUpdate(); 
                System.out.println("Descuento eliminado.");
            } catch (SQLException e) {
                System.out.println("Error al eliminar el descuento: " + 
                e.getMessage());
            }
        }

    }

    
    public Descuento buscarPorNombre(String nombre) {
        
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "SELECT * FROM descuento WHERE nombre = ?"; 
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, nombre);  
                ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                
                         
                        Descuento descuento = new Descuento(
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getDouble("porcentajeDescuento"),
                            rs.getDate("fechaInicio"),
                            rs.getDate("fechaFin")
                        );                       
                        return descuento;
                    } 
                                                            
            } catch (SQLException e) {
                System.out.println("Error al buscar cliente: " + e.getMessage());
            }
    
        }
        return null;
    }

    
    public List<Descuento> obtenerTodos() {
        
         Connection conexion = ConexionDB.conectar();
         List<Descuento> descuentos = new ArrayList<>();
         
        if (conexion != null) {
            String query = "SELECT * FROM descuento"; 
            try (Statement stmt = conexion.createStatement(); 
                ResultSet rs = stmt.executeQuery(query)) {
            
                while (rs.next()) {
                    Descuento descuento = new Descuento();
                    descuento.setIdDescuento(rs.getInt("idDescuento"));
                    descuento.setNombre(rs.getString("nombre"));
                    descuento.setDescripcion(rs.getString("descripcion"));
                    descuento.setPorcentajeDescuento(rs.getDouble("porcentajeDescuento"));
                    descuento.setFechaInicio(rs.getDate("fechaInicio"));
                    descuento.setFechaFin(rs.getDate("fechaFin"));
                    descuento.add(descuento);
                    
                }
            } catch (SQLException e) {
            System.out.println("Error al realizar la consulta: " + e.getMessage());
            }finally {
                try {
                    conexion.close(); 
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
        return descuentos;
                
    }
}
