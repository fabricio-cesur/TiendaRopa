package view;

import model.User;
import model.Cliente;
import java.util.Scanner;
import java.util.ArrayList;
import dao.UserDAO;
import dao.ClienteDAO;
import model.Producto;


public class AdminView {
    private Scanner sc = new Scanner(System.in);
    private UserDAO userDAO = new UserDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();
    private ProductoView producto = new ProductoView();
    private PedidoView pedidos = new PedidoView();

    public void menuAdmin(User user) {
        int opcion;
        do {
            System.out.println("Bienvenido al menú de administración, " + user.getUsername() + "!");
            System.out.println("1. Gestionar usuarios");
            System.out.println("2. Gestionar inventario");
            System.out.println("3. Gestionar pedidos");
            System.out.println("4. Gestionar descuentos");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> gestionarUsuarios();
                case 2 -> producto.gestionarInventario();
                case 3 -> gestionarPedidos();
                case 4 -> gestionarDescuentos();
                case 5 -> System.out.println("Saliendo del menú de administración...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (opcion != 5);
    }

    public void gestionarUsuarios() {
        int opcion;
        do {
            System.out.println("Gestión de usuarios");
            System.out.println("1. Listar usuarios");
            System.out.println("2. Eliminar usuario");
            System.out.println("3. Modificar rol de usuario");
            System.out.println("4. Información de usuario");
            System.out.println("4. Volver al menú principal");
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

    

}
