package view;

import dao.PedidoDAO;
import dao.ProductoDAO;
import java.util.ArrayList;
import java.util.Scanner;
import model.Pedido;
import model.Producto;

public class PedidoView {
    private Scanner sc = new Scanner(System.in);
    private PedidoDAO pedidoDAO = new PedidoDAO();
    private ProductoDAO productoDAO = new ProductoDAO();
    private Producto producto = null;
    private UserView userView = new UserView();

    public PedidoView() {}
    
    //Metodo para establecer la dependencia de UserView
    public void setUserView(UserView userView) {
        this.userView = userView;
    }

    public void gestionPedidos() {
        int opcion;
        do { 
            System.out.println("Pedidos");
            System.out.println("------------------");
            System.out.println("1. Ver pedidos realizados");
            System.out.println("2. Realizar un nuevo pedido");
            System.out.println("3. Cancelar un pedido");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> verPedidosRealizados();
                case 2 -> realizarNuevoPedido();
                case 3 -> cancelarPedido();
                case 4 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 4);
        
    }

    public void verPedidosRealizados() {
        System.out.println("Introduce el ID del usuario para ver sus pedidos: ");
        int userId = sc.nextInt();
        sc.nextLine(); // Limpiar el buffer

        ArrayList<Pedido> pedidos = pedidoDAO.listarPedidos(); // Obtener todos los pedidos
        boolean pedidosEncontrados = false;

        System.out.println("Pedidos realizados por el usuario con ID: " + userId);
        System.out.println("--------------------------------------------------");

        for (Pedido pedido : pedidos) {
            if (pedido.getCliente().getIdCliente().getUserId() == userId) {
                System.out.println(pedido.toString());
                pedidosEncontrados = true;
            }
        }

        if (!pedidosEncontrados) {
            System.out.println("No se encontraron pedidos para el usuario con ID: " + userId);
        }
    }

    public void realizarNuevoPedido() {
        System.out.println("Realizar un nuevo pedido");
        System.out.println("-------------------------");

        ArrayList<Producto> carrito = new ArrayList<>();
        double total = 0.0;

        while (true) {
            System.out.println("Introduce el ID del producto que deseas agregar al carrito (0 para finalizar): ");
            int idProducto = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            if (idProducto == 0) break;

            Producto producto = productoDAO.getProductoId(idProducto);
            if (producto == null) {
                System.out.println("Producto no encontrado. Intente de nuevo.");
                continue;
            }

            System.out.println("Introduce la cantidad: ");
            int cantidad = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            if (!productoDAO.verificarStock(idProducto, cantidad)) {
                System.out.println("No hay suficiente stock disponible. Intente con una cantidad menor.");
                continue;
            }

            System.out.println("Introduce el color: ");
            String color = sc.nextLine();

            System.out.println("Introduce la talla: ");
            String talla = sc.nextLine();

            // Agregar producto al carrito
            producto.setColor(color);
            producto.setTalla(talla);
            carrito.add(producto);

            // Calcular subtotal
            double subtotal = producto.getPrecio() * cantidad;
            total += subtotal;

            System.out.println("Producto agregado al carrito. Subtotal: " + subtotal);
        }

        if (carrito.isEmpty()) {
            System.out.println("No se agregó ningún producto al carrito. Pedido cancelado.");
            return;
        }

        System.out.println("Introduce la dirección de envío: ");
        String direccion = sc.nextLine();

        // Crear el pedido
        Pedido pedido = new Pedido(null, null, null, false, total, direccion);
        pedido.setProductos(carrito);

        // Insertar el pedido en la base de datos
        pedidoDAO.insertarPedido(pedido, userView.getUserId());

        System.out.println("Pedido realizado con éxito. Total: " + total);
    }

    public void cancelarPedido() {
        System.out.println("Cancelar un pedido");
        System.out.println("-------------------");

        System.out.println("Introduce el ID del pedido que deseas cancelar: ");
        int idPedido = sc.nextInt();
        sc.nextLine(); // Limpiar el buffer

        // Obtener el pedido por ID
        Pedido pedido = pedidoDAO.getPedidoId(idPedido);

        if (pedido == null) {
            System.out.println("No se encontró un pedido con el ID proporcionado.");
            return;
        }

        System.out.println("¿Estás seguro de que deseas cancelar el siguiente pedido?");
        System.out.println(pedido.toString());
        System.out.println("Escribe 'S' para confirmar o cualquier otra tecla para cancelar la operación.");
        String confirmacion = sc.nextLine();

        if (confirmacion.equalsIgnoreCase("S")) {
            pedidoDAO.eliminarPedido(pedido);
            System.out.println("El pedido ha sido cancelado con éxito.");
        } else {
            System.out.println("Operación cancelada. El pedido no ha sido eliminado.");
        }
    }
}
