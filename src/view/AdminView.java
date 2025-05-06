package view;

import model.User;
import java.util.Scanner;
import dao.UserDAO;
import java.util.ArrayList;

public class AdminView {
    Scanner sc = new Scanner(System.in);
    UserDAO userDAO = new UserDAO();

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
                case 2 -> gestionarInventario();
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
                case 1 -> listarUsuarios(userDAO);
                case 2 -> eliminarUsuario(userDAO);
                case 3 -> addUsuario(userDAO);
                case 4 -> infoUsuario(userDAO);
                case 5 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (opcion != 5);
    }

    public void eliminarUsuario() {
        User user = getUsuarioId();
        userDAO.eliminarUser(user);
        System.out.println("Usuario eliminado correctamente.");

        
    }

    public void listarUsuarios() {
        ArrayList<User> users = userDAO.listarUsers();
        System.out.println("Lista de usuarios:");
        for (User user : users) {
            System.out.println(user.toString());
        }
    }

    public User getUsuarioId() {
        System.out.print("Ingrese el ID del usuario a eliminar: ");
        int userId = sc.nextInt();
        sc.nextLine(); // Limpiar el buffer
        User user = userDAO.getUserId(userId);
        return user;
    }

}
