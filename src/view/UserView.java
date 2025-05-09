package view;

import dao.UserDAO;
import dao.ClienteDAO;
import java.util.ArrayList;
import java.util.Scanner;
import model.User;
import model.Cliente;
import model.Pedido;
import model.Producto;

public class UserView {

    private Scanner sc = new Scanner(System.in);
    private ClienteDAO clienteDAO = new ClienteDAO();
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private UserDAO userDAO = new UserDAO();
    private ArrayList<User> users = new ArrayList<>();
    private AdminView admin = new AdminView();
    private PedidoView pedido = new PedidoView();
    private ProductoView producto = new ProductoView();

    String usuario = "tienda";
    String passwd = "GestorTiendaRopa8743";
    String userEntrada;
    String passwordEntrada;

    public UserView() {}

    //Metodo para establecer depencia de
    public void setPedidoView(PedidoView pedidos) {
        this.pedidos = pedidos;
    }

    public void gestionUser() {
        int opcion;

    }

    public void iniciarSesion() {
        boolean sesionIniciada = false;
        do { 
            System.out.println("Iniciar sesión");
        System.out.println("------------------");
        System.out.print("Introduce el nombre de usuario: ");
        userEntrada = sc.nextLine();
        System.out.print("Introduce la contraseña: ");
        passwordEntrada = sc.nextLine();

        User user = userDAO.inicioSesion(userEntrada, passwordEntrada);

        if (user != null) {
            if (user.getRole().equals("admin")) {
                System.out.println("Inicio de sesión exitoso. Bienvenido, " + user.getUsername() + "!");
                admin.menuAdmin(user); // Llama al menú del administrador
                sesionIniciada = true; // Cambia el estado de la sesión a iniciada
            }
            else {
                System.out.println("Inicio de sesión exitoso. Bienvenido, " + user.getUsername() + "!");
                menuUser(user); // Llama al menú del usuario
                sesionIniciada = true; // Cambia el estado de la sesión a iniciada
            }
            
        } else {
            System.out.println("Usuario o contraseña incorrectos. Intente de nuevo.");
        }
        } while (!sesionIniciada);
        
    }

    public void registrarUser() {
        System.out.println("Registrarse");
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
        User user = new User(username, password, "user");
        userDAO.insertarUser(user);
        users.add(user);
        Cliente cliente = new Cliente(nombre, apellido, email, telefono);
        clienteDAO.insertarCliente(cliente);
        clientes.add(cliente);
    }


    public void menuUser(User user) {
        int opcion;
        do {
            System.out.println("Bienvenido, " + user.getUsername() + "!");
            System.out.println("------------------");
            System.out.println("1. Pedidos");
            System.out.println("2. Ver productos");
            System.out.println("3. Modificar usuario");
            System.out.println("4. Darse de baja");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> pedido.gestionPedidos();
                case 2 -> producto.verProductos();
                case 3 -> modificarUser();
                case 4 -> eliminarUser();
                
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
    
    public User getUserId() {
        System.out.println("Introduce el id del usuario: ");
        int id = sc.nextInt();
        sc.nextLine(); // Limpiar el buffer
        User user = userDAO.getUserId(id);
        return user;
    }

    public void modificarUser() {
        User user = this.getUserId();
        int idUser = user.getUserId();
        int opcion;
        do { 
            System.out.println("Modificar usuario");
            System.out.println("------------------");
            System.out.println("1. Modificar nombre de usuario");
            System.out.println("2. Modificar contraseña");
            System.out.println("3. Modificar nombre");
            System.out.println("4. Modificar apellido");
            System.out.println("5. Modificar email");
            System.out.println("6. Modificar dirección");
            System.out.println("7. Modificar teléfono");
            System.out.println("8. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> {
                    System.out.println("Introduce el nuevo nombre de usuario: ");
                    String nuevoNombre = sc.nextLine();
                    userDAO.modificarNombre(id, nuevoNombre);
                }
                case 2 -> {
                    System.out.println("Introduce la nueva contraseña: ");
                    String nuevaPass = sc.nextLine();
                    userDAO.modificarPass(id, nuevaPass);
                }
                case 3 -> {
                    System.out.println("Introduce el nuevo nombre: ");
                    String nuevoNombre = sc.nextLine();
                    clienteDAO.modificarNombre(cliente, nuevoNombre);
                }
                case 4 -> {
                    System.out.println("Introduce el nuevo apellido: ");
                    String nuevoApellido = sc.nextLine();
                    clienteDAO.modificarApellido(cliente, nuevoApellido);
                }
                case 5 -> {
                    System.out.println("Introduce el nuevo email: ");
                    String nuevoEmail = sc.nextLine();
                    clienteDAO.modificarEmail(cliente, nuevoEmail);
                }
                case 6 -> {
                    System.out.println("Introduce la nueva dirección: ");
                    String nuevaDireccion = sc.nextLine();
                    clienteDAO.modificarDireccion(cliente, nuevaDireccion);
                }
                case 7 -> {
                    System.out.println("Introduce el nuevo teléfono: ");
                    String nuevoTelefono = sc.nextLine();
                    clienteDAO.modificarTelefono(cliente, nuevoTelefono);
                }
            }
        } while (opcion != 8);
        
    
    }

    

}
