package view;

import dao.ClienteDAO;
import dao.PedidoDAO;
import dao.UserDAO;
import java.util.ArrayList;
import java.util.Scanner;
import model.Banca;
import model.Cliente;
import model.Pedido;
import model.User;

public class AdminView {
    private Scanner sc = new Scanner(System.in);
    private UserDAO userDAO = new UserDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();
    private PedidoDAO pedidoDAO = new PedidoDAO(); // Cambiar de PedidoView a PedidoDAO
    private ProductoView producto = new ProductoView();
    private Banca banca = new Banca();

    public void menuAdmin(User user) {
        int opcion;
        do {
            System.out.println("Bienvenido al menú de administración, " + user.getUsername() + "!");
            System.out.println("1. Gestionar usuarios");
            System.out.println("2. Gestionar inventario");
            System.out.println("3. Gestionar pedidos");
            System.out.println("4. Gestionar descuentos");
            System.out.println("5. Ver balance de la empresa");
            System.out.println("6. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> gestionarUsuarios();
                case 2 -> producto.gestionarInventario();
                case 3 -> gestionarPedidos();
                case 4 -> new DescuentoView().gestionarDescuentosAdmin();
                case 5 -> System.out.println("Balance actual de la empresa: " + banca.getBalance());
                case 6 -> System.out.println("Saliendo del menú de administración...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 6);
    }

    public void gestionarUsuarios() {
        int opcion;
        do {
            System.out.println("Gestión de usuarios");
            System.out.println("1. Listar usuarios");
            System.out.println("2. Eliminar usuario");
            System.out.println("3. Modificar rol de usuario");
            System.out.println("4. Ver información detallada de un usuario");
            System.out.println("5. Volver al menú principal");
            System.out.print("Selecciona una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> listarUsuarios();
                case 2 -> eliminarUsuario();
                case 3 -> modRol();
                case 4 -> infoUsuario();
                case 5 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (opcion != 5);
    }

    public void listarUsuarios() {
        ArrayList<User> users = userDAO.listarUsers();
        System.out.println("Lista de usuarios:");
        for (User user : users) {
            System.out.println(user.toString());
        }
    }
    
    public void eliminarUsuario() {
        User user = getUsuarioId();
        userDAO.eliminarUser(user);
        System.out.println("Usuario eliminado correctamente.");

        
    }

    public void modRol() {
        User user = getUsuarioId();
        userDAO.modificarRol(user);
        System.out.println("Rol de usuario modificado correctamente.");
        
    }

    public User getUsuarioId() {
        System.out.print("Ingrese el ID del usuario a eliminar: ");
        int userId = sc.nextInt();
        sc.nextLine(); // Limpiar el buffer
        User user = userDAO.getUserId(userId);
        return user;
    }

    public void infoUsuario() {
        User user = getUsuarioId(); // Obtiene el usuario por ID
        if (user != null) {
            Cliente cliente = clienteDAO.getClienteId(user.getUserId()); // Busca el cliente con el mismo ID
            System.out.println("Información del usuario:");
            System.out.println(user.toString());
            if (cliente != null) {
                System.out.println(cliente.toString());
            } else {
                System.out.println("No se encontró un cliente asociado con este usuario.");
            }
        } else {
            System.out.println("No se encontró un usuario con el ID proporcionado.");
        }
    }

    public void gestionarPedidos() {
        int opcion;
        do {
            System.out.println("Gestión de pedidos");
            System.out.println("1. Listar todos los pedidos");
            System.out.println("2. Modificar un pedido");
            System.out.println("3. Eliminar un pedido");
            System.out.println("4. Volver al menú principal");
            System.out.print("Selecciona una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> listarTodosLosPedidos();
                case 2 -> modificarPedido();
                case 3 -> eliminarPedido();
                case 4 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 4);
    }

    private void listarTodosLosPedidos() {
        ArrayList<Pedido> pedidos = pedidoDAO.listarPedidos(); // Cambiado a pedidoDAO
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
            return;
        }

        System.out.println("Lista de todos los pedidos:");
        for (Pedido pedido : pedidos) {
            System.out.println(pedido.toString());
        }
    }

    private void modificarPedido() {
        System.out.println("Introduce el ID del pedido que deseas modificar: ");
        int idPedido = sc.nextInt();
        sc.nextLine(); // Limpiar el buffer

        Pedido pedido = pedidoDAO.getPedidoId(idPedido); // Cambiado a pedidoDAO
        if (pedido == null) {
            System.out.println("No se encontró un pedido con el ID proporcionado.");
            return;
        }

        int opcion;
        do {
            System.out.println("Modificar pedido");
            System.out.println("1. Modificar cliente");
            System.out.println("2. Modificar fecha de pedido");
            System.out.println("3. Modificar fecha de entrega");
            System.out.println("4. Modificar estado");
            System.out.println("5. Modificar total");
            System.out.println("6. Modificar dirección");
            System.out.println("7. Volver al menú de gestión de pedidos");
            System.out.print("Selecciona una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> {
                    System.out.println("Introduce el ID del nuevo cliente: ");
                    int idCliente = sc.nextInt();
                    sc.nextLine(); // Limpiar el buffer
                    Cliente nuevoCliente = clienteDAO.getClienteId(idCliente);
                    if (nuevoCliente != null) {
                        pedidoDAO.modificarClientePedido(pedido, nuevoCliente);
                        System.out.println("Cliente modificado con éxito.");
                    } else {
                        System.out.println("No se encontró un cliente con el ID proporcionado.");
                    }
                }
                case 2 -> {
                    System.out.println("Introduce la nueva fecha de pedido (YYYY-MM-DD): ");
                    String nuevaFecha = sc.nextLine();
                    pedidoDAO.modificarFechaPedido(pedido, nuevaFecha);
                    System.out.println("Fecha de pedido modificada con éxito.");
                }
                case 3 -> {
                    System.out.println("Introduce la nueva fecha de entrega (YYYY-MM-DD): ");
                    String nuevaFecha = sc.nextLine();
                    pedidoDAO.modificarFechaEntrega(pedido, nuevaFecha);
                    System.out.println("Fecha de entrega modificada con éxito.");
                }
                case 4 -> {
                    System.out.println("Introduce el nuevo estado del pedido (true para entregado, false para pendiente): ");
                    boolean nuevoEstado = sc.nextBoolean();
                    sc.nextLine(); // Limpiar el buffer
                    pedidoDAO.modificarEstado(pedido, nuevoEstado);
                    System.out.println("Estado del pedido modificado con éxito.");
                }
                case 5 -> {
                    System.out.println("Introduce el nuevo total del pedido: ");
                    double nuevoTotal = sc.nextDouble();
                    sc.nextLine(); // Limpiar el buffer
                    pedidoDAO.modificarTotal(pedido, nuevoTotal);
                    System.out.println("Total del pedido modificado con éxito.");
                }
                case 6 -> {
                    System.out.println("Introduce la nueva dirección del pedido: ");
                    String nuevaDireccion = sc.nextLine();
                    pedidoDAO.modificarDireccion(pedido, nuevaDireccion);
                    System.out.println("Dirección del pedido modificada con éxito.");
                }
                case 7 -> System.out.println("Volviendo al menú de gestión de pedidos...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 7);
    }

    private void eliminarPedido() {
        System.out.println("Introduce el ID del pedido que deseas eliminar: ");
        int idPedido = sc.nextInt();
        sc.nextLine(); // Limpiar el buffer

        Pedido pedido = pedidoDAO.getPedidoId(idPedido); // Cambiado a pedidoDAO
        if (pedido == null) {
            System.out.println("No se encontró un pedido con el ID proporcionado.");
            return;
        }

        System.out.println("¿Estás seguro de que deseas eliminar el siguiente pedido?");
        System.out.println(pedido.toString());
        System.out.println("Escribe 'S' para confirmar o cualquier otra tecla para cancelar la operación.");
        String confirmacion = sc.nextLine();

        if (confirmacion.equalsIgnoreCase("S")) {
            pedidoDAO.eliminarPedido(pedido);
            System.out.println("El pedido ha sido eliminado con éxito.");
        } else {
            System.out.println("Operación cancelada. El pedido no ha sido eliminado.");
        }
    }
}
