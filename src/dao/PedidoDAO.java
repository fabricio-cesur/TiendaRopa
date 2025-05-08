package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Cliente;
import model.Pedido;
import model.User;


public class PedidoDAO {
    Cliente cliente = null;

    public void insertarPedido(Pedido pedido, User user) {
        int idPedido = pedido.getIdPedido();
        int idCliente = user.getUserId(); // Obtener el ID del usuario
        String fechaPedido = pedido.getFechaPedido();
        String fechaEntrega = pedido.getFechaEntrega();
        Boolean estado = pedido.getEstado();
        Double total = pedido.getTotal();
        String direccion = pedido.getDireccion();

        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            String sql = "INSERT INTO pedidos (idPedido, idCliente, fechaPedido, fechaEntrega, estado, total, direccion) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                ps.setInt(1, idPedido);
                ps.setInt(2, idCliente); // Usar el ID del usuario
                ps.setString(3, fechaPedido);
                ps.setString(4, fechaEntrega);
                ps.setBoolean(5, estado);
                ps.setDouble(6, total);
                ps.setString(7, direccion);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error al insertar el pedido: " + e.getMessage());
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
            String query = "SELECT * FROM Pedidos";

            try (Statement stmt = conexion.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    String email = rs.getString("email"); // Obtener el nombre de usuario
                    int idPedido = rs.getInt("idPedido");
                    String fechaPedido = rs.getString("fechaPedido");
                    String fechaEntrega = rs.getString("fechaEntrega");
                    Boolean estado = rs.getBoolean("estado");
                    Double total = rs.getDouble("total");
                    String direccion = rs.getString("direccion");

                    Cliente cliente = new Cliente("", "", email,""); // Crear un objeto Cliente con el email

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
                        Cliente cliente = ClienteDAO.getClienteEmail(rs.getString("email")); // Obtener el cliente por su email

                        pedido = new Pedido(cliente, fechaPedido, fechaEntrega, estado, total, direccion);
                        pedido.setIdPedido(idPedido); // Establecer el ID del pedido
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al obtener el pedido por ID: " + e.getMessage());
            }
        }
        return pedido;
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
