package view;

import dao.UserDAO;
import dao.ClienteDAO;
import java.util.ArrayList;
import java.util.Scanner;
import model.User;
import model.Cliente;

public class UserView {

    private Scanner sc = new Scanner(System.in);
    private ClienteDAO clienteDAO = new ClienteDAO();
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private UserDAO userDAO = new UserDAO();
    private ArrayList<User> users = new ArrayList<>();

    String usuario = "tienda";
    String passwd = "GestorTiendaRopa8743";
    String usuarioEntrada;
    String passwdEntrada;

    public UserView() {}

    //Metodo para establecer depencia de
    public void setPedidoView(PedidoView pedidos) {
        this.pedidos = pedidos;
    }

    public void gestionUser() {
        int opcion;

    }

    public User iniciarSesion() {
        User user = null;
        System.out.println("Iniciar sesion");
        System.out.println("------------------");
        System.out.println("Introduce el nombre de usuario: ");
        usuarioEntrada = sc.nextLine();
        System.out.println("Introduce la contraseña: ");
        passwdEntrada = sc.nextLine();
        userDAO.inicioSesion(usuarioEntrada, passwdEntrada);

        if (usuarioEntrada.equals(usuario) && passwdEntrada.equals(passwd) && user.isAdmin()) {
            System.out.println("Hola admin (º ͜ʖº)/ ");
        }
        
        return user;

    }

    public void registrarUser() {
        System.out.println("Registrase");
        System.out.println("------------------");
        System.out.println("Introduce el nombre de usuario: ");
        String username = sc.nextLine();
        System.out.println("Introduce la contraseña: ");
        String password = sc.nextLine();
        System.out.println("Datos personales");
        System.out.println("------------------");
        System.out.println("Introduce su nombre");
        String nombre = sc.nextLine();
        System.out.println("Introduce su apellido");
        String apellido = sc.nextLine();
        System.out.println("Introduce su email");
        String email = sc.nextLine();
        System.out.println("Introduce su nº de telefono");
        String telefono = sc.nextLine();
        User user = new User(username, password, false);
        userDAO.insertarUser(user);
        users.add(user);
        Cliente cliente = new Cliente(nombre, apellido, email, telefono);
        clienteDAO.insertarCliente(cliente);
        clientes.add(cliente);
    }

    public User getUserId() {
        System.out.println("Introduce el id del usuario: ");
        int id = sc.nextInt();
        sc.nextLine(); // Limpiar el buffer
        User user = userDAO.getUserId(id);
        return user;
    }

    public User getUsername() {
        System.out.println("Introduce el nombre de usuario: ");
        String username = sc.nextLine();
        sc.nextLine(); // Limpiar el buffer
        User user = userDAO.getUsername(username);
        return user;

    }

    public User getPassword() {
        System.out.println("Introduce la contraseña: ");
        String password = sc.nextLine();
        sc.nextLine(); // Limpiar el buffer
        User user = userDAO.getUsername(password);
        return user;
    }

    public void menuUser(User user) {
        int opcion;
        do {
            System.out.println("Menu de usuario");
            System.out.println("------------------");
            System.out.println("1. Actualizar nombre de usuario");
            System.out.println("2. Actualizar contraseña");
            System.out.println("3. Listar clientes");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> {
                    System.out.println("Introduce el nuevo nombre de usuario: ");
                    String username = sc.nextLine();
                    userDAO.actualizarUsernameUser(user.getUserId(), username);
                }
                case 2 -> {
                    System.out.println("Introduce la nueva contraseña: ");
                    String password = sc.nextLine();
                    userDAO.actualizarPasswordUser(user.getUserId(), password);
                }
                case 3 -> {
                    clientes = clienteDAO.listarClientes();
                    for (Cliente cliente : clientes) {
                        System.out.println(cliente.toString());
                    }
                }
                case 4 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (opcion != 4);
    }

    public void listarUsers() {
        ArrayList<User> users = userDAO.listarUsers();
        System.out.println("Lista de usuarios:");
        for (User user : users) {
            System.out.println(user.toString());
        }
    }

}
