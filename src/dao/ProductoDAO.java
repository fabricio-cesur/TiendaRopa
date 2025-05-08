package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Producto;

public class ProductoDAO {

    //Método para insertar un producto en la BDD
    public boolean insertar(Producto producto) {
        Connection conexion = ConexionDB.conectar(); 
        if (conexion != null) {
            String query = "INSERT INTO Productos (tipo, nombre, marca, talla, color, precio) VALUES (?, ?, ?, ?, ?, ?)";
    
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, producto.getTipo());
                stmt.setString(2, producto.getNombre());
                stmt.setString(3, producto.getMarca());
                stmt.setString(4, producto.getTalla());
                stmt.setString(5, producto.getColor());
                stmt.setDouble(6, producto.getPrecio());
    
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.err.println("Error al agregar el Producto: " + e.getMessage());
                return false;
            }
        } else {
            System.err.println("Error al conectarse con la base de datos");
            return false;
        }
    }
    
    //Método para actualizar un producto en la BDD
    //En lugar de actualizar todo el objeto, sólo actualiza la columna que se requiera
    public boolean actualizar(String columna, String valor, int id) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "UPDATE Productos SET ? = ? WHERE id = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, columna);
                stmt.setString(2, valor);
                stmt.setInt(3, id);
                
                return stmt.executeUpdate() == 1;
            } catch (SQLException e) {
                System.err.println("Error al actualizar el producto: " + e.getMessage());
                return false;
            }
        } else {
            System.err.println("Error al conectarse con la base de datos");
            return false;
        }
    }

    //Método para obtener el objeto por id
    public Producto obtenerPorId(int id) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            Producto producto = null;
            String query = "SELECT * FROM Productos WHERE idProducto = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    producto = new Producto(
                        rs.getString("tipo"),
                        rs.getString("nombre"),
                        rs.getString("marca"),
                        rs.getString("talla"),
                        rs.getString("color"),
                        rs.getDouble("precio")
                    );
                    producto.setId(rs.getInt("idProducto"));
                    producto.setStock(rs.getInt("stock"));
                }
            } catch (SQLException e) {
                System.err.println("Error al obtener el producto: " + e.getMessage());
            }
            return producto;
        } else {
            System.err.println("Error al conectarse con la base de datos");
        }
        return null;
    }

    //Método para obtener una lista de todos los objetos
    public ArrayList<Producto> obtenerTodos() {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            ArrayList<Producto> productos = new ArrayList<>();
            String query = "SELECT * FROM Productos";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    Producto producto = new Producto(
                        rs.getString("tipo"),
                        rs.getString("nombre"),
                        rs.getString("marca"),
                        rs.getString("talla"),
                        rs.getString("color"),
                        rs.getDouble("precio")
                    );
                    producto.setId(rs.getInt("idProducto"));
                    producto.setStock(rs.getInt("stock"));
                    productos.add(producto);
                }
            } catch (SQLException e) {
                System.err.println("Error al obtener los productos: " + e.getMessage());
                return null;
            }
            return productos;
        } else {
            System.err.println("Error al conectarse con la base de datos");
            return null;
        }
    }

    //Método para eliminar un producto
    public boolean eliminar(int id) {
        Connection conexion = ConexionDB.conectar(); 
        if (conexion != null) {
            String query = "DELETE FROM Productos WHERE idProducto = ?";
    
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setInt(1, id);
    
                return stmt.executeUpdate() == 1;
            } catch (SQLException e) {
                System.err.println("Error al agregar el Producto: " + e.getMessage());
                return false;
            }
        } else {
            System.err.println("Error al conectarse con la base de datos");
            return false;
        }
    }

    //TODO: Métodos de verificación para asegurarse que algún objeto exista en la base de datos

}