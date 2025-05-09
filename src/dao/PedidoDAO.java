package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Cliente;
import model.Pedido;
import model.Producto;
import model.User;

public class PedidoDAO {
    Cliente cliente = null;
    ProductoDAO productoDAO = new ProductoDAO();

    public void insertarPedido(Pedido pedido, User user) {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            String sqlPedido = "INSERT INTO Pedidos (idCliente, fechaPedido, estado, total, direccion) VALUES (?, NOW(), ?, ?, ?)";
            String sqlDetalle = "INSERT INTO DetallePedido (idPedido, idProducto, cantidad, precioUnitario, subtotal) VALUES (?, ?, ?, ?, ?)";

            try {
                conexion.setAutoCommit(false); // Iniciar transacción

                // Insertar el pedido
                try (PreparedStatement psPedido = conexion.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS)) {
                    psPedido.setInt(1, user.getUserId());
                    psPedido.setBoolean(2, pedido.getEstado());
                    psPedido.setDouble(3, pedido.getTotal());
                    psPedido.setString(4, pedido.getDireccion());
                    psPedido.executeUpdate();

                    // Obtener el ID del pedido generado
                    ResultSet rs = psPedido.getGeneratedKeys();
                    if (rs.next()) {
                        pedido.setIdPedido(rs.getInt(1));
                    }
                }

                // Insertar los detalles del pedido
                try (PreparedStatement psDetalle = conexion.prepareStatement(sqlDetalle)) {
                    for (Producto producto : pedido.getProductos()) {
                        psDetalle.setInt(1, pedido.getIdPedido());
                        psDetalle.setInt(2, producto.getIdProducto());
                        psDetalle.setInt(3, producto.getStock());
                        psDetalle.setDouble(4, producto.getPrecio());
                        psDetalle.setDouble(5, producto.getPrecio() * producto.getStock());
                        psDetalle.addBatch();

                        // Reducir el stock del producto
                        productoDAO.reducirStock(producto.getIdProducto(), producto.getStock());
                    }
                    psDetalle.executeBatch();
                }

                conexion.commit(); // Confirmar transacción
            } catch (SQLException e) {
                try {
                    conexion.rollback(); // Revertir transacción en caso de error
                } catch (SQLException rollbackEx) {
                    System.err.println("Error al revertir la transacción: " + rollbackEx.getMessage());
                }
                System.err.println("Error al insertar el pedido: " + e.getMessage());
            } finally {
                try {
                    conexion.setAutoCommit(true); // Restaurar el modo de confirmación automática
                } catch (SQLException ex) {
                    System.err.println("Error al restaurar el modo de confirmación automática: " + ex.getMessage());
                }
            }
        }
    }

    public void eliminarPedido(Pedido pedido) {
        int idPedido = pedido.getIdPedido();

        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            String query = "DELETE FROM pedidos WHERE idPedido = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setInt(1, idPedido);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error al eliminar el pedido: " + e.getMessage());
            }
        }
    }

    public ArrayList<Pedido> listarPedidos() {
        Connection conexion = ConexionDB.conectar();
        ArrayList<Pedido> pedidos = new ArrayList<>();

        if (conexion != null) {
            String query = "SELECT p.*, c.email FROM Pedidos p JOIN Clientes c ON p.idCliente = c.idCliente";

            try (Statement stmt = conexion.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    String email = rs.getString("email");
                    int idPedido = rs.getInt("idPedido");
                    String fechaPedido = rs.getString("fechaPedido");
                    String fechaEntrega = rs.getString("fechaEntrega");
                    Boolean estado = rs.getBoolean("estado");
                    Double total = rs.getDouble("total");
                    String direccion = rs.getString("direccion");

                    Cliente cliente = new Cliente("", "", email, ""); // Crear un objeto Cliente con el email

                    Pedido pedido = new Pedido(cliente, fechaPedido, fechaEntrega, estado, total, direccion);
                    pedido.setIdPedido(idPedido); // Establecer el ID del pedido
                    pedidos.add(pedido);
                }
            } catch (SQLException e) {
                System.out.println("Error al listar los pedidos: " + e.getMessage());
            }
        }
        return pedidos;
    }

    public Pedido getPedidoId (int idPedido) {
        Connection conexion = ConexionDB.conectar();
        Pedido pedido = null;

        if (conexion != null) {
            String query = "SELECT * FROM Pedidos WHERE idPedido = ?";
            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setInt(1, idPedido);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String email = rs.getString("email"); // Obtener el nombre de usuario
                        String fechaPedido = rs.getString("fechaPedido");
                        String fechaEntrega = rs.getString("fechaEntrega");
                        Boolean estado = rs.getBoolean("estado");
                        Double total = rs.getDouble("total");
                        String direccion = rs.getString("direccion");
                        Cliente cliente = new Cliente("", "", email,""); // Crear un objeto Cliente con el email

                        pedido = new Pedido(cliente, fechaPedido, fechaEntrega, estado, total, direccion);
                        
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al obtener el pedido por ID: " + e.getMessage());
            }
            return pedido;
        }
        return null;
    }

    public void modificarClientePedido(Pedido pedido, Cliente nuevoCliente) { // Modifica el cliente que hizo el pedido
        int idPedido = pedido.getIdPedido();
        int idCliente = nuevoCliente.getIdCliente().getUserId(); // Obtener el ID del usuario asociado al cliente
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            String query = "UPDATE Pedidos SET idCliente = ? WHERE idPedido = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setInt(1, idCliente); // Usar el ID del nuevo cliente
                ps.setInt(2, idPedido);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error al modificar el cliente del pedido: " + e.getMessage());
            }
        }
    }

    public void modificarFechaPedido(Pedido pedido, String nuevaFecha) { //modifica la fecha de cuando se hizo el pedido
        Connection conexion = ConexionDB.conectar();
        
        int idPedido = pedido.getIdPedido();
        if (conexion != null) {
            String query = "UPDATE Pedidos SET fechaPedido = ? WHERE idPedido = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, nuevaFecha);
                ps.setInt(2, idPedido);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error al modificar la fecha de pedido: " + e.getMessage());
            }
        }
    }

    public void modificarFechaEntrega(Pedido pedido, String nuevaFecha) {
        Connection conexion = ConexionDB.conectar();
        
        int idPedido = pedido.getIdPedido();
        if (conexion != null) {
            String query = "UPDATE Pedidos SET fechaEntrega = ? WHERE idPedido = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, nuevaFecha);
                ps.setInt(2, idPedido);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error al modificar la fecha de entrega: " + e.getMessage());
            }
        }
    }

    public void modificarEstado(Pedido pedido, Boolean nuevoEstado) {
        Connection conexion = ConexionDB.conectar();
        
        int idPedido = pedido.getIdPedido();
        if (conexion != null) {
            String query = "UPDATE Pedidos SET estado = ? WHERE idPedido = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setBoolean(1, nuevoEstado);
                ps.setInt(2, idPedido);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error al modificar el estado del pedido: " + e.getMessage());
            }
        }
    }

    public void modificarTotal(Pedido pedido, Double nuevoTotal) {
        Connection conexion = ConexionDB.conectar();
        
        int idPedido = pedido.getIdPedido();
        if (conexion != null) {
            String query = "UPDATE Pedidos SET total = ? WHERE idPedido = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setDouble(1, nuevoTotal);
                ps.setInt(2, idPedido);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error al modificar el total del pedido: " + e.getMessage());
            }
        }
    }

    public void modificarDireccion(Pedido pedido, String nuevaDireccion) {
        Connection conexion = ConexionDB.conectar();
        
        int idPedido = pedido.getIdPedido();
        if (conexion != null) {
            String query = "UPDATE Pedidos SET direccion = ? WHERE idPedido = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, nuevaDireccion);
                ps.setInt(2, idPedido);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error al modificar la dirección del pedido: " + e.getMessage());
            }
        }
    }
}
