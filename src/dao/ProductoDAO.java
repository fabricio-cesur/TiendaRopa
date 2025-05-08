package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Producto;

public class ProductoDAO {
    public void instarProducto(Producto producto) {
        String nombre = producto.getNombre();
        String descripcion = producto.getDescripcion();
        Double precio = producto.getPrecio();
        int stock = producto.getStock();
        String talla = producto.getTalla();
        String color = producto.getColor();
        String marca = producto.getMarca();
        String categoria = producto.getCategoria();

        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            String query = "INSERT INTO producto (nombre, descripcion, precio, stock, talla, color, marca, categoria) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, nombre);
                ps.setString(2, descripcion);
                ps.setDouble(3, precio);
                ps.setInt(4, stock);
                ps.setString(5, talla);
                ps.setString(6, color);
                ps.setString(7, marca);
                ps.setString(8, categoria);
                ps.executeUpdate();

            } catch (SQLException e) {
                System.err.println("Error al insertar el producto: " + e.getMessage());
            } 

        }
    }

    public void eliminarProducto(Producto producto) {
        int idProducto = producto.getIdProducto();

        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            String query = "DELETE FROM Productos WHERE idProducto = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setInt(1, idProducto);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al eliminar el producto: " + e.getMessage());
            } 
        }
    }

    public Producto getProductoId (int idProducto) {
        Connection conexion = ConexionDB.conectar();
        Producto producto = null;

        if (conexion != null) {
            String query = "SELECT * FROM Productos WHERE idProducto = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setInt(1, idProducto);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String nombre = rs.getString("nombre");
                        String descripcion = rs.getString("descripcion");
                        Double precio = rs.getDouble("precio");
                        int stock = rs.getInt("stock");
                        String talla = rs.getString("talla");
                        String color = rs.getString("color");
                        String marca = rs.getString("marca");
                        String categoria = rs.getString("categoria");

                        producto = new Producto(nombre, descripcion, precio, stock, talla, color, marca, categoria);
                        
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al obtener producto por Id: " + e.getMessage());
            }
        }
        return producto;
    }

    public void modificarIdProducto(int idProducto, Producto producto) {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            String query = "UPDATE Productos SET idProducto = ? WHERE idProducto = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setInt(1, producto.getIdProducto());
                ps.setInt(2, idProducto);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al modificar el id del producto: " + e.getMessage());
            }
        }
    }

    public void modificarNombreProducto (String nombre, int idProducto) {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            String query = "UPDATE Productos SET nombre = ? WHERE idProducto = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, nombre);
                ps.setInt(2, idProducto);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al modificar el nombre del producto: " + e.getMessage());
            }
        }
    }

    public void modificarDescripcionProducto (String descripcion, int idProducto) {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            String query = "UPDATE Productos SET descripcion = ? WHERE idProducto = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, descripcion);
                ps.setInt(2, idProducto);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al modificar la descripcion del producto: " + e.getMessage());
            }
        }
    }

    public void modificarPrecioProducto (Double precio, int idProducto) {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            String query = "UPDATE Productos SET precio = ? WHERE idProducto = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setDouble(1, precio);
                ps.setInt(2, idProducto);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al modificar el precio del producto: " + e.getMessage());
            }
        }
    }

    public void modificarStockProducto (int stock, int idProducto) {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            String query = "UPDATE Productos SET stock = ? WHERE idProducto = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setInt(1, stock);
                ps.setInt(2, idProducto);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al modificar el stock del producto: " + e.getMessage());
            }
        }
    }

    public void modificarTallaProducto (String talla, int idProducto) {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            String query = "UPDATE Productos SET talla = ? WHERE idProducto = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, talla);
                ps.setInt(2, idProducto);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al modificar la talla del producto: " + e.getMessage());
            }
        }
    }

    public void modificarColorProducto (String color, int idProducto) {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            String query = "UPDATE Productos SET color = ? WHERE idProducto = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, color);
                ps.setInt(2, idProducto);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al modificar el color del producto: " + e.getMessage());
            }
        }
    }

    public void modificarMarcaProducto (String marca, int idProducto) {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            String query = "UPDATE Productos SET stock = ? WHERE idProducto = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, marca);
                ps.setInt(2, idProducto);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al modificar la marca del producto: " + e.getMessage());
            }
        }
    }

    public void modificarCategoriaProducto (String categoria, int idProducto) {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            String query = "UPDATE Productos SET stock = ? WHERE idProducto = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, categoria);
                ps.setInt(2, idProducto);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al modificar la categoria del producto: " + e.getMessage());
            }
        }
    }

    public void modificarIdProducto (int idProducto, int idProductoNuevo) {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            String query = "UPDATE Productos SET idProducto = ? WHERE idProducto = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setInt(1, idProductoNuevo);
                ps.setInt(2, idProducto);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al modificar el id del producto: " + e.getMessage());
            }
        }
    }

    public ArrayList<Producto> listarProductos() {
        Connection conexion = ConexionDB.conectar();
        ArrayList<Producto> productos = new ArrayList<>();

        if (conexion != null) {
            String query = "SELECT * FROM Productos";

            try (Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    int idProducto = rs.getInt("idProducto");
                    String nombre = rs.getString("nombre");
                    String descripcion = rs.getString("descripcion");
                    Double precio = rs.getDouble("precio");
                    int stock = rs.getInt("stock");
                    String talla = rs.getString("talla");
                    String color = rs.getString("color");
                    String marca = rs.getString("marca");
                    String categoria = rs.getString("categoria");

                    Producto producto = new Producto(nombre, descripcion, precio, stock, talla, color, marca, categoria);
                    producto.setIdProducto(idProducto);
                    productos.add(producto);
                }
            } catch (SQLException e) {
                System.err.println("Error al listar productos: " + e.getMessage());
            }
            return productos;
        }
            return null;
    }
}


